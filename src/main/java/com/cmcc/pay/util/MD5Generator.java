package com.cmcc.pay.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

/**
 * Created by ech0 on 2016/3/15.
 */
public class MD5Generator {


    public static String MD5_KEY = "";
//    import org.apache.commons.codec.digest.DigestUtils;
//    import java.util.Date;
//
//    Date date=new java.util.Date();
//    Long  test = date.getTime();
//
//    //MD5加密方法
//    String  generateMD5(String str) {
//        return DigestUtils.md5Hex(str.getBytes());
//    };
//
//    String str ="11335725725655<BusiData> <AccountType>1</AccountType> <AccountCode>18867103681</AccountCode> <PayInfo>ceshi</PayInfo> <OrderId>"+test +"</OrderId> <PayAmount>400</PayAmount> <PayNotifyPageURL>http://www.baidu.com</PayNotifyPageURL> <PayNotifyIntURL>http://www.qq.com</PayNotifyIntURL> </BusiData>r8rw4d1kjwqgqqto9dwsq3ew0ip2np1b";
//
//    String VerifyCode = generateMD5(str);

    public static String generateMD5(Map input){
        //TODO 根据字典生成要加密的字符串

        String Verfiycode = "input";


        return Verfiycode;
    }


    public static String sign(String text, String key, String input_charset) {
        text = text + key; //将秘钥拼接到加密字符串的最后。
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (Exception e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    public static String sign(Map<String,String> map, String key) {
        //剔除掉节点值为空的节点，及业务平台传过来的VerifyCode节点
        Map mapforSign = paraFilter(map);
        //将节点拼接成符合格式key1=value1&Key2=value2的字符串
        String str = createLinkString(mapforSign);
        //判断是RSA还是MD5---------------------------SDK是用RSA加密，WEB&WAP使用MD5加密------------------
        String digestAlg = (String) map.get("DigestAlg");
        if (StringUtils.isEmpty(digestAlg)) {
            digestAlg = "";
        }
        if (digestAlg.equalsIgnoreCase("RSA")) {
            try {
                //RSA加密
                return sign_SHA1WithRSA(str, key);
            } catch (Exception e) {
                return "";
            }
        } else if (digestAlg.equalsIgnoreCase("MD5")) {
            return sign(str, key, "utf-8");//MD5加密
        } else {
            return "";
        }
    }

    private static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    private static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (Map.Entry<String, String> stringStringEntry : sArray.entrySet()) {
            
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("VerifyCode")
                    || key.equalsIgnoreCase("digestAlg")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }


    public static String sign_SHA1WithRSA(String data,String strHexPrivateKey) throws Exception {
        try{
            //��ȡ˽Կ
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodeHexString2Byte(strHexPrivateKey)));
            //˽Կǩ��
            Signature sig = Signature.getInstance("SHA1WithRSA");
            sig.initSign(rsaPrivateKey);
            sig.update(data.getBytes("GBK"));
            return encodeByte2HexString(sig.sign());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encodeByte2HexString(byte[] bytes){
        if (bytes ==null||bytes.length<=0) {
            return null;
        }
        try {
            return new String(Hex.encodeHex(bytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decodeHexString2Byte(String hexStr){
        if (hexStr ==null||hexStr.length()<=0) {
            return null;
        }
        try {
            return Hex.decodeHex(hexStr.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
