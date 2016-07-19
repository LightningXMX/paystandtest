package com.cmcc.pay.testcase;

import com.cmcc.pay.mapper.PayOrderMapper;
import com.cmcc.pay.model.biz.PayOrder;
import com.cmcc.pay.model.biz.PaySettleSummary;
import com.cmcc.pay.model.compareresult.PaySettleSummaryTestResult;
import com.cmcc.pay.model.compareresult.PaySettleTestResult;
import com.cmcc.pay.model.tools.PayOrderTools;
import com.cmcc.pay.model.biz.PaySettle;
import com.cmcc.pay.model.tools.PaySettleSummaryTools;
import com.cmcc.pay.model.tools.PaySettleTools;
import com.cmcc.pay.util.ExcelUtil;
import com.cmcc.pay.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.util.calendar.LocalGregorianCalendar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by LIGHTNING on 2016/7/16.
 */
public class Test {
    public static void main(String[] args) throws  Exception{
        String fileName = "payaccount-testdata.xlsx";
        String projectPath = System.getProperty("user.dir");
        StringBuilder filePath = new StringBuilder().
        append(projectPath).append(File.separator)
                .append("src").append(File.separator)
                .append("main").append(File.separator)
                .append("resources").append(File.separator)
//                .append("poi").append(File.separator)
                .append("testdata").append(File.separator)
                .append(fileName);

        XSSFWorkbook xssfWorkbook = ExcelUtil.readTestData(filePath.toString());
        List<XSSFRow> payOrderRowList = ExcelUtil.convertXLS(xssfWorkbook,"pay_order");
//        payOrderRowList.remove(0);
        List<PayOrder> payOrderList = PayOrderTools.build(payOrderRowList);
        SqlSessionFactory sqlSessionFactory =  MybatisUtil.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            PayOrderMapper payOrderMapper = session.getMapper(PayOrderMapper.class);
            int i =payOrderMapper.batchInsert(payOrderList,new SimpleDateFormat("yyMM").format(new Date()));
            //// TODO: 2016/7/19
//            PayOrder payOrder = payOrderMapper.selectByTradeNo("OP201607011702131991000");
//            System.out.println(payOrder);
//            System.out.println(i);


            System.out.println(i);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        //step2:
        //// TODO: 2016/7/17 call the interface

        //step3:

        List<XSSFRow> PaySettleRowList = ExcelUtil.convertXLS(xssfWorkbook,"pay_settle");//pay_settle
        List<PaySettle> paySettleList = PaySettleTools.build(PaySettleRowList);
        //遍历paySettleList拿每条记录查数据库

        List<PaySettleTestResult> paySettleTestResultList = PaySettleTools.verificate(paySettleList);
        PaySettleTools.fillExcelWithResult(filePath.toString(),paySettleTestResultList);

        //step4:

        List<XSSFRow> paySettleSummaryRowList = ExcelUtil.convertXLS(xssfWorkbook,"pay_settle_summary");//pay_settle_summary
        List<PaySettleSummary> paySettleSummaryList = PaySettleSummaryTools.build(paySettleSummaryRowList);
        //遍历paySettleList拿每条记录查数据库

        List<PaySettleSummaryTestResult> paySettleSummaryTestResultList = PaySettleSummaryTools.verificate(paySettleSummaryList);
        PaySettleSummaryTools.fillExcelWithResult(filePath.toString(),paySettleSummaryTestResultList);
    }
}
