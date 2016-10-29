package com.cmcc.pay.util;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by ech0 on 2016/3/13.
 */
public class URLbuilder {

    public static String HOST = "";
    public static String PORT = "";
    public static String PATH = "";


    public static String buildRequestUrl(String xml) {


        String url = null;
        try {
            if (StringUtils.isEmpty(xml)){
                xml = "";
            }
            String encodedXML = URLEncoder.encode(xml, "UTF-8");
            url = "http://" + HOST + ":" + PORT +PATH + encodedXML;
            System.out.println(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return url;
    }


}
