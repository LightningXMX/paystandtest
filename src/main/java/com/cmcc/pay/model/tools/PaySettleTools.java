package com.cmcc.pay.model.tools;

import com.cmcc.pay.mapper.PaySettleMapper;
import com.cmcc.pay.model.biz.PaySettle;
import com.cmcc.pay.model.compareresult.PaySettleTestResult;
import com.cmcc.pay.util.ExcelUtil;
import com.cmcc.pay.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by LIGHTNING on 2016/7/17.
 */
public class PaySettleTools {
    public static List<PaySettle> build(List<XSSFRow> xssfRowList){
        List<PaySettle> paySettleList = new ArrayList<PaySettle>();
        for (XSSFRow xssfRow : xssfRowList) {
            PaySettle paySettle = new PaySettle();
            Iterator<Cell> iterator = xssfRow.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
            paySettle.setId(Long.parseLong(ExcelUtil.getValue(xssfRow.getCell(0))));
            paySettle.setSettleid(ExcelUtil.getValue(xssfRow.getCell(1)));
            paySettle.setOrderid(ExcelUtil.getValue(xssfRow.getCell(2)));
            paySettle.setMerchantId(Long.parseLong(ExcelUtil.getValue(xssfRow.getCell(3))));
            paySettle.setProductId(Long.parseLong(ExcelUtil.getValue(xssfRow.getCell(4))));

            paySettleList.add(paySettle);
            //// TODO: 2016/7/17  
        }

        return paySettleList;
    }

    public static List<PaySettleTestResult> verificate(List<PaySettle> paySettleList) {

        List<PaySettleTestResult> paySettleTestResultList = new ArrayList<PaySettleTestResult>();
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();

        for (PaySettle xlsPaySettle : paySettleList) {

            SqlSession session = sqlSessionFactory.openSession();
            try {
                PaySettleMapper paySettleMapper = session.getMapper(PaySettleMapper.class);
                PaySettle dbPaySettle = paySettleMapper.selectByOrderId(xlsPaySettle.getOrderid());
                boolean result = compareValue(xlsPaySettle,dbPaySettle);
                //// TODO: 2016/7/17 记录db查询到的数据到xls

                PaySettleTestResult paySettleTestResult = new PaySettleTestResult();
                paySettleTestResult.setTestResult(result);
                paySettleTestResult.setDbResult(dbPaySettle);
//                fillExcelWithResult(result,dbPaySettle);
                paySettleTestResultList.add(paySettleTestResult);

            }catch  (Throwable e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        }

        return paySettleTestResultList;
    }

    public static boolean compareValue(PaySettle xlsPaySettle,PaySettle dbPaySettle){
        boolean result = false;
        if (dbPaySettle == null) return result;//db未找到数据
        if (!xlsPaySettle.getSettleid().equals(dbPaySettle.getSettleid())) return false;
        if (!xlsPaySettle.getSettleid().equals(dbPaySettle.getSettleid())) return false;
        //// TODO: 2016/7/17 ...


        return true;
    }

    public static void fillExcelWithResult(String filePath ,List<PaySettleTestResult> paySettleTestResultList){

        String fileName = "testdata/payaccount-testdata.xlsx";
        XSSFWorkbook xssfWorkbook = ExcelUtil.readTestData(filePath);
        XSSFSheet xssfSheet = xssfWorkbook.getSheet("pay_settle_result");
        for (int i = 0; i < paySettleTestResultList.size(); i++) {
            PaySettleTestResult paySettleTestResult = paySettleTestResultList.get(0);
            PaySettle dbPaySettle = paySettleTestResult.getDbResult();
            XSSFRow xssfRow = xssfSheet.createRow(i + 1);
            xssfRow.createCell(0).setCellValue(paySettleTestResult.isTestResult());
            xssfRow.createCell(1).setCellValue(dbPaySettle.getId());
            xssfRow.createCell(2).setCellValue(dbPaySettle.getSettleid());
            xssfRow.createCell(3).setCellValue(dbPaySettle.getOrderid());
            xssfRow.createCell(4).setCellValue(dbPaySettle.getMerchantId());
            xssfRow.createCell(5).setCellValue(dbPaySettle.getProductId());


        }
        ExcelUtil.writeTestData(filePath,xssfWorkbook);
    }
}
