package com.cmcc.pay.testcase;

import com.cmcc.pay.mapper.PayOrderMapper;
import com.cmcc.pay.mapper.PaySettleSummaryMapper;
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
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by echo on 2016/7/16.
 */
public class Account_automationTest extends TestBase {

    private Logger logger = LoggerFactory.getLogger(Account_automationTest.class);

    private void clearData() {
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            PayOrderMapper payOrderMapper = session.getMapper(PayOrderMapper.class);
            payOrderMapper.deleteAllPay(new SimpleDateFormat("yyMM").format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
    @BeforeTest( groups = "Account")
    public void PrepareData() throws Exception{

        clearData();

        //插入excel中的支付记录
        String filePath = getFilePath(ExcelInfo.ExcelAccountFileName);
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
//        AdvTestResponse response = HttpClientUtil.get(url);
//        if (response.getStatusCode() != 200) {//接口调用失败
//            logger.error("call the interface fail.");
//            return;
//        }
       //判断结算是否结束,如果summary表中还没有数据表明结算未完成
        long count = countPaySettleSummary();
        if(count==0) {
            logger.error("PaySettleSummary table is empty.");
            return ;
        }

    }

    @DataProvider(name = "testData_Account")
    public Object[][] testData_Account_automationTest() throws Exception {

        String filePath = getFilePath(ExcelInfo.ExcelAccountFileName);
        XSSFWorkbook xssfWorkbook = ExcelUtil.readTestData(filePath);

        List<XSSFRow> paySettleSummaryRowList = ExcelUtil.convertXLS(xssfWorkbook, ExcelInfo.ExcelPaySettleSummarySheetName);//pay_settle_summary
        List<PaySettleSummary> paySettleSummaryList = PaySettleSummaryTools.build(paySettleSummaryRowList);
        //遍历paySettleList拿每条记录查数据库

        List<PaySettleSummaryTestResult> paySettleSummaryTestResultList = PaySettleSummaryTools.verificate(paySettleSummaryList);
//        PaySettleSummaryTools.fillExcelWithResult(filePath.toString(), paySettleSummaryTestResultList);

        Object[][] testdata = new Object[paySettleSummaryTestResultList.size()][1];
        for (int i = 0; i < paySettleSummaryTestResultList.size(); i++) {

            testdata[i][0] = paySettleSummaryTestResultList.get(i);
        }
        return testdata;

    }

    private long countPaySettleSummary() {
        long i = 0;
        try {
            SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();

            SqlSession session = sqlSessionFactory.openSession();
            PaySettleSummaryMapper paySettleSummaryMapper = session.getMapper(PaySettleSummaryMapper.class);
            i = paySettleSummaryMapper.countAll();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Test(dataProvider = "testData_Account", groups = "Account")
    public void Account_automationTest(PaySettleSummaryTestResult paySettleSummaryTestResult){
        System.out.println("---------------");
        Assert.assertTrue(paySettleSummaryTestResult.getMsg(),paySettleSummaryTestResult.isTestResult()==true);
    }




}
