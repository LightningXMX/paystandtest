package com.cmcc.pay.testcase;

import com.cmcc.pay.mapper.PayOrderMapper;
import com.cmcc.pay.model.biz.PayOrder;
import com.cmcc.pay.model.compareresult.PaySettleTestResult;
import com.cmcc.pay.model.tools.PayOrderTools;
import com.cmcc.pay.model.biz.PaySettle;
import com.cmcc.pay.model.tools.PaySettleTools;
import com.cmcc.pay.util.ExcelUtil;
import com.cmcc.pay.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.List;

/**
 * Created by LIGHTNING on 2016/7/16.
 */
public class Test {
    public static void main(String[] args) {
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
        SqlSession session = sqlSessionFactory.openSession();
        try {
            PayOrderMapper payOrderMapper = session.getMapper(PayOrderMapper.class);
            int i =payOrderMapper.batchInsert(payOrderList);
//            PayOrder payOrder = payOrderMapper.selectByTradeNo("OP201607011702131991000");
//            System.out.println(payOrder);
//            System.out.println(i);



        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        //// TODO: 2016/7/17 call the interface


        List<XSSFRow> PaySettleRowList = ExcelUtil.convertXLS(xssfWorkbook,"pay_settle");//pay_settle_summary
        PaySettleRowList.remove(0);
        List<PaySettle> paySettleList = PaySettleTools.build(PaySettleRowList);
        //遍历paySettleList拿每条记录查数据库

//        List<XSSFRow> PaySettleRowList = ExcelUtil.convertXLS(xssfWorkbook,"pay_settle");//pay_settle_summary
//        PaySettleRowList.remove(0);
//        List<PaySettle> paySettleList = PaySettleTools.build(PaySettleRowList);
        //遍历paySettleList拿每条记录查数据库
        //verificate
//        PaySettleTools.verificate(paySettleList);
        //verificate
        List<PaySettleTestResult> paySettleTestResultList = PaySettleTools.verificate(paySettleList);
        PaySettleTools.fillExcelWithResult(filePath.toString(),paySettleTestResultList);
    }
}
