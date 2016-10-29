package com.cmcc.pay.testcase;

import com.cmcc.pay.model.tools.ExcelInfo;
import com.cmcc.pay.util.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by echo on 2016/10/27.
 */
public class Pay_automationTest extends TestBase {

    private Logger logger = LoggerFactory.getLogger(Pay_automationTest.class);

    @org.junit.Test
    public void Pay_automationTest() throws Exception {
        //step1: 从excel中封装数据。
         String filePath = getFilePath(ExcelInfo.ExcelPayFilename);
         XSSFWorkbook xssfWorkbook = ExcelUtil.readTestData(filePath);


        //调用支付接口，接口的数据从excel中读取（模拟多种类型的支付数据，如代收实物，自收虚拟，购物车订单等）
        String URL = URLbuilder.buildRequestUrl("");
        AdvTestResponse response = HttpClientUtil.get(URL);


    }

    }
