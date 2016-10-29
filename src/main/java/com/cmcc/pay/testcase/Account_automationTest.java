package com.cmcc.pay.testcase;

import com.cmcc.pay.model.biz.PayOrder;
import com.cmcc.pay.model.biz.PaySettle;
import com.cmcc.pay.model.biz.PaySettleSummary;
import com.cmcc.pay.model.compareresult.PaySettleSummaryTestResult;
import com.cmcc.pay.model.compareresult.PaySettleTestResult;
import com.cmcc.pay.model.tools.ExcelInfo;
import com.cmcc.pay.model.tools.PayOrderTools;
import com.cmcc.pay.model.tools.PaySettleSummaryTools;
import com.cmcc.pay.model.tools.PaySettleTools;
import com.cmcc.pay.util.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * Created by echo on 2016/7/16.
 */
public class Account_automationTest extends TestBase {

    private Logger logger = LoggerFactory.getLogger(Account_automationTest.class);

    @org.junit.Test
    public void Account_automationTest() throws Exception {


        //step0:删除测试数据

        String filePath = getFilePath(ExcelInfo.ExcelAccountFileName);

        //step1:插入payorder测试数据
        XSSFWorkbook xssfWorkbook = ExcelUtil.readTestData(filePath);
        List<XSSFRow> payOrderRowList = ExcelUtil.convertXLS(xssfWorkbook, ExcelInfo.ExcelPayOrderSheetName);
        List<PayOrder> payOrderList = PayOrderTools.build(payOrderRowList);

        boolean isSuccess = PayOrderTools.insertTestData(payOrderList);
        if (!isSuccess) {
            logger.error("insert testdata fail.");
            return;
        }

        //step2:call the interface
        String url = URLbuilder.buildRequestUrl("");
        AdvTestResponse response = HttpClientUtil.get(url);
        if (response.getStatusCode() != 200) {//接口调用失败
            logger.error("call the interface fail.");
            return;
        }

        //step3:校验pay_settle,填写结果
        List<XSSFRow> PaySettleRowList = ExcelUtil.convertXLS(xssfWorkbook, ExcelInfo.ExcelPaySettleSheetName);//pay_settle
        List<PaySettle> paySettleList = PaySettleTools.build(PaySettleRowList);
        //遍历paySettleList拿每条记录查数据库
        List<PaySettleTestResult> paySettleTestResultList = PaySettleTools.verificate(paySettleList);
        PaySettleTools.fillExcelWithResult(filePath, paySettleTestResultList);

        //step4:校验pay_settle_summary,填写结果
        List<XSSFRow> paySettleSummaryRowList = ExcelUtil.convertXLS(xssfWorkbook, ExcelInfo.ExcelPaySettleSummarySheetName);//pay_settle_summary
        List<PaySettleSummary> paySettleSummaryList = PaySettleSummaryTools.build(paySettleSummaryRowList);
        //遍历paySettleList拿每条记录查数据库

        List<PaySettleSummaryTestResult> paySettleSummaryTestResultList = PaySettleSummaryTools.verificate(paySettleSummaryList);
        PaySettleSummaryTools.fillExcelWithResult(filePath.toString(), paySettleSummaryTestResultList);


    }

}
