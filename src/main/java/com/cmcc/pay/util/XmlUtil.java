package com.cmcc.pay.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by ech0 on 2016/3/13.
 */
public class XmlUtil {

    public static String xmlGenerator(Object object, Class<?>... types) {
        try {
            String encoding = "utf-8";
            JAXBContext jaxbContext = JAXBContext.newInstance(types);

            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            if (encoding != null && encoding.isEmpty()) {
                marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            }
            StringWriter writer = new StringWriter();

            marshaller.marshal(object, writer);
            String xmlResponseWithStandalone = writer.toString();
            String xmlResponse = xmlResponseWithStandalone.replace("standalone=\"yes\"", "");
            return xmlResponse;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        String oldxml=sw.toString();
        oldxml = oldxml.replaceAll("\r", "");
        oldxml = oldxml.replaceAll("\n", "");
        oldxml = oldxml.replaceAll(" ", "");
        String xml = "<?xml version=\"1.0\" encoding=\"UTF_8\" ?>"+oldxml;


        return xml;
    }
}
