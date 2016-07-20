package com.cmcc.pay.testcase;

import com.cmcc.pay.mapper.PayOrderMapper;
import com.cmcc.pay.model.biz.PayOrder;
import com.cmcc.pay.model.biz.PaySettleSummary;
import com.cmcc.pay.model.compareresult.PaySettleSummaryTestResult;
import com.cmcc.pay.model.compareresult.PaySettleTestResult;
import com.cmcc.pay.model.tools.ExcelInfo;
import com.cmcc.pay.model.tools.PayOrderTools;
import com.cmcc.pay.model.biz.PaySettle;
import com.cmcc.pay.model.tools.PaySettleSummaryTools;
import com.cmcc.pay.model.tools.PaySettleTools;
import com.cmcc.pay.util.ExcelUtil;
import com.cmcc.pay.util.MybatisUtil;
import com.cmcc.pay.util.TestBase;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by echo on 2016/7/16.
 */
public class Test1 extends TestBase{

//    public static String ExcelFileName = "";
//    public static String ExcelPayOrderSheetName = "";
//    public static String ExcelPaySettleSheetName = "";
//    public static String ExcelPaySettleSummarySheetName = "";


    @org.junit.Test
    public void test1() throws  Exception{

        String filePath = new StringBuilder().
        append(System.getProperty("user.dir")).append(File.separator)
                .append("src").append(File.separator)
                .append("main").append(File.separator)
                .append("resources").append(File.separator)
                .append("testdata").append(File.separator)
                .append(ExcelInfo.ExcelFileName).toString();

        XSSFWorkbook xssfWorkbook = ExcelUtil.readTestData(filePath);
        List<XSSFRow> payOrderRowList = ExcelUtil.convertXLS(xssfWorkbook,ExcelInfo.ExcelPayOrderSheetName);
        List<PayOrder> payOrderList = PayOrderTools.build(payOrderRowList);

//        List<String> tradeNO = getDeleteTradeNO(payOrderList)

        SqlSessionFactory sqlSessionFactory =  MybatisUtil.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            PayOrderMapper payOrderMapper = session.getMapper(PayOrderMapper.class);
            int i =payOrderMapper.batchInsert(payOrderList,new SimpleDateFormat("yyMM").format(new Date()));
            System.out.println(i);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        //step2:
        //// TODO: 2016/7/17 call the interface

        //step3:校验pay_settle,填写结果

        List<XSSFRow> PaySettleRowList = ExcelUtil.convertXLS(xssfWorkbook,ExcelInfo.ExcelPaySettleSheetName);//pay_settle
        List<PaySettle> paySettleList = PaySettleTools.build(PaySettleRowList);
        //遍历paySettleList拿每条记录查数据库

        List<PaySettleTestResult> paySettleTestResultList = PaySettleTools.verificate(paySettleList);
        PaySettleTools.fillExcelWithResult(filePath,paySettleTestResultList);

        //step4:校验pay_settle_summary,填写结果

        List<XSSFRow> paySettleSummaryRowList = ExcelUtil.convertXLS(xssfWorkbook,ExcelInfo.ExcelPaySettleSummarySheetName);//pay_settle_summary
        List<PaySettleSummary> paySettleSummaryList = PaySettleSummaryTools.build(paySettleSummaryRowList);
        //遍历paySettleList拿每条记录查数据库

        List<PaySettleSummaryTestResult> paySettleSummaryTestResultList = PaySettleSummaryTools.verificate(paySettleSummaryList);
        PaySettleSummaryTools.fillExcelWithResult(filePath.toString(),paySettleSummaryTestResultList);


        //step5删除测试数据
    }
}
