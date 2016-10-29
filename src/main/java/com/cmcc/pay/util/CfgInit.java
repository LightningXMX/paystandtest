package com.cmcc.pay.util;

import com.cmcc.pay.model.tools.ExcelFieldEnum;
import com.cmcc.pay.model.tools.ExcelInfo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ech0 on 2016/3/17.
 */
@Component
public class CfgInit {

    public void init() {

        System.out.println("init");

        InputStream is = Object.class.getResourceAsStream("/config.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MD5Generator.MD5_KEY = (String) properties.get("MD5_KEY");
        URLbuilder.HOST =  (String) properties.get("HOST");
        URLbuilder.PORT =  (String) properties.get("PORT");
        URLbuilder.PATH =  (String) properties.get("PATH");
        DBHelper.url =  (String) properties.get("JDBC_URL");
        DBHelper.name =  (String) properties.get("JDBC_NAME");
        DBHelper.user =  (String) properties.get("JDBC_USER");
        DBHelper.password =  (String) properties.get("JDBC_PASSWORD");


        ExcelInfo.ExcelFileName = (String) properties.get("ExcelFileName");
        ExcelInfo.ExcelPayOrderSheetName = (String) properties.get("ExcelPayOrderSheetName");
        ExcelInfo.ExcelPaySettleSheetName = (String) properties.get("ExcelPaySettleSheetName");
        ExcelInfo.ExcelPaySettleSummarySheetName = (String) properties.get("ExcelPaySettleSummarySheetName");
        ExcelInfo.ExcelPaySettleResultSheetName = (String) properties.get("ExcelPaySettleResultSheetName");
        ExcelInfo.ExcelPaySettleSummaryResultSheetName = (String) properties.get("ExcelPaySettleSummaryResultSheetName");

        if (properties.get("payOrder_id")!=null) {
            ExcelFieldEnum.PayOrderExcelFieldEnum.id = (String) properties.get("payOrder_id");
        }
    }
}
