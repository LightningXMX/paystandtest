package com.cmcc.pay.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by ech0 on 2016/3/13.
 */
public class HttpClientUtil {

//    public static void main(String[] args) {
//        String baseurl = "http://112.54.207.9:18080";
//        String payurl = "/PayPlatform/pay/ask-for.do";
//        String param = "?xml=";
//
//        new HttpClientUtil().get("");
//    }

    public static AdvTestResponse get(String url) {

//        url = url+"?xml="+xml;

        AdvTestResponse advTestResponse = new AdvTestResponse();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String responseString = "";
        try {
            // 创建http get.
            HttpGet httpget = new HttpGet(url);


//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            nvps.add(new BasicNameValuePair("xml", xml));
//            httpget.setEntity(new UrlEncodedFormEntity(nvps));



            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();

                System.out.println("--------------------------------------");
                // 打印响应状态
                System.out.println(response.getStatusLine());
                advTestResponse.setProtocolVersion(response.getStatusLine().getProtocolVersion().toString());
                advTestResponse.setReasonPhrase(response.getStatusLine().getReasonPhrase());
                advTestResponse.setStatusCode(response.getStatusLine().getStatusCode());


                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());

                    responseString = EntityUtils.toString(entity);
                    advTestResponse.setContent(responseString);
                    // 打印响应内容
                    System.out.println("Response content: " + responseString);
                }
                System.out.println("------------------------------------");
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return advTestResponse;
    }
}
