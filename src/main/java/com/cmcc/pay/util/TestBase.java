package com.cmcc.pay.util;

//import com.cmcc.pay.paystand.test.xml.module.AdvPay;
//import com.cmcc.pay.paystand.test.xml.module.AdvPayBuilder;
//import com.cmcc.pay.paystand.test.xml.module.AdvPayEnum;
import com.cmcc.pay.model.tools.ExcelInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ech0 on 2016/3/13.
 */
public class  TestBase {

    protected static final String EXPECTED_RESULT = "EXPECTED_RESULT";
    protected static final String DESC = "DESC";


    private static ApplicationContext context ;
    protected static HttpClientUtil httpClientUtil;
    protected static SqlSessionFactory sqlSessionFactory;

    static {
         context = new ClassPathXmlApplicationContext("applicationContext.xml");

//        initHttpClient();
//        initMybatis();
    }

    private static void initHttpClient(){
        httpClientUtil = (HttpClientUtil) context.getBean("httpClientUtil");

    }

    private static void initMybatis() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        TestBase.sqlSessionFactory = sqlSessionFactory;
    }

    //    protected Map<String,String> createDefaultInput(){
//        Map<String,String> defaultInput = new HashMap();
//
//        defaultInput.put(AdvPayEnum.Version,"1.00");
//        defaultInput.put(AdvPayEnum.TransactionId,"11335725725655");
//        defaultInput.put(AdvPayEnum.TransactionDate,"20130815162325");
//        defaultInput.put(AdvPayEnum.OriginId,"1000");
//        defaultInput.put(AdvPayEnum.DigestAlg,"MD5");
//        defaultInput.put(AdvPayEnum.VerifyCode,"245085b06819d930639a15f12fd4ba98");
//
//
//        defaultInput.put(AdvPayEnum.AccountType,"1");
//        defaultInput.put(AdvPayEnum.AccountCode,"18867103681");
//        defaultInput.put(AdvPayEnum.PayInfo,"InterfaceTest_YYY0624");
//        defaultInput.put(AdvPayEnum.OrderId,"InterfaceTest_663681");
//        defaultInput.put(AdvPayEnum.MerchantId,"3");
//        defaultInput.put(AdvPayEnum.ProductId,"32");
//        defaultInput.put(AdvPayEnum.ProCount,"1");
//        defaultInput.put(AdvPayEnum.PayAmount,"0.01");
//        defaultInput.put(AdvPayEnum.PayPeriod,"1h");
//        defaultInput.put(AdvPayEnum.PayNotifyIntURL,"http://www.qq.com");
//        defaultInput.put(AdvPayEnum.PayNotifyPageURL,"http://www.baidu.com");
//        defaultInput.put(AdvPayEnum.RoyaltyFlag,"0");
//        defaultInput.put(AdvPayEnum.MerchantUrl,"http://www.qq.com");
//
//
//        return defaultInput;
//    }

    protected List createExpectedResult() {
        return new ArrayList<String>();
    }
    protected AdvTestResponse test(Map<String,String> input) {

//        AdvPay advPay = AdvPayBuilder.build(input);
////
//        String xml = XmlUtil.convertToXml(advPay);
//
//        String url = URLbuilder.buildRequestUrl(xml);
//
//        AdvTestResponse response = HttpClientUtil.get(url);
//
//        return response;
return null;
    }



    protected String getFilePath(String filename) {
        String filePath = new StringBuilder().
                append(System.getProperty("user.dir")).append(File.separator)
                .append("src").append(File.separator)
                .append("main").append(File.separator)
                .append("resources").append(File.separator)
                .append("testdata").append(File.separator)
                .append(filename).toString();
        return filePath;
    }
}
