package com.cmcc.pay.model.tools;

import com.cmcc.pay.mapper.PaySettleMapper;
import com.cmcc.pay.model.biz.PayOrder;
import com.cmcc.pay.model.biz.PaySettle;
import com.cmcc.pay.model.compareresult.PaySettleTestResult;
import com.cmcc.pay.util.ExcelUtil;
import com.cmcc.pay.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by LIGHTNING on 2016/7/17.
 */
public class PaySettleTools {
    public static List<PaySettle> build(List<XSSFRow> xssfRowList) throws Exception {
        XSSFRow firstXssfRow = xssfRowList.get(0);//获取第一行字段
        Map<Integer, String> xlsFieldMap = new HashMap<Integer, String>();
        for (int i = 1; i <= firstXssfRow.getLastCellNum(); i++) {
            xlsFieldMap.put(i, ExcelUtil.getValue(firstXssfRow.getCell(i - 1)));
        }
        xssfRowList.remove(0);
        List<PaySettle> paySettleList = new ArrayList<PaySettle>();
        for (XSSFRow xssfRow : xssfRowList) {
            PaySettle paySettle = new PaySettle();

            for (Map.Entry<Integer, String> integerStringEntry : xlsFieldMap.entrySet()) {
                int fieldNO = integerStringEntry.getKey();
                String fieldName = integerStringEntry.getValue();
                XSSFCell fieldValue = xssfRow.getCell(fieldNO - 1);
                setPaySettleFiled(paySettle, fieldName, fieldValue);
            }

            paySettleList.add(paySettle);
        }
        return paySettleList;

    }

    public static void setPaySettleFiled(PaySettle paySettle, String fieldName, XSSFCell fieldValue) throws Exception {
        if (ExcelFieldEnum.PaySettleExcelFieldEnum.id.equals(fieldName)) {
            if (!StringUtils.isEmpty(ExcelUtil.getValue(fieldValue))) {
                paySettle.setId(Long.parseLong(ExcelUtil.getValue(fieldValue)));
            }
        } else if (ExcelFieldEnum.PaySettleExcelFieldEnum.settleId.equals(fieldName)) {
            paySettle.setSettleid(ExcelUtil.getValue(fieldValue));
        } else if (ExcelFieldEnum.PaySettleExcelFieldEnum.orderId.equals(fieldName)) {
            paySettle.setOrderid(ExcelUtil.getValue(fieldValue));
        }

        //// TODO: 2016/7/19
    }

    public static List<PaySettleTestResult> verificate(List<PaySettle> paySettleList) {

        List<PaySettleTestResult> paySettleTestResultList = new ArrayList<PaySettleTestResult>();
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();

        SqlSession session = sqlSessionFactory.openSession();
        PaySettleMapper paySettleMapper = session.getMapper(PaySettleMapper.class);
        try {
            for (PaySettle xlsPaySettle : paySettleList) {

                PaySettle dbPaySettle = paySettleMapper.selectByOrderId(xlsPaySettle.getOrderid());
                PaySettleTestResult paySettleTestResult = new PaySettleTestResult();

                boolean result = false;
                if (dbPaySettle==null){
                    
                    paySettleTestResult.setMsg("There is no recode in database.");
                }
                result = compareValue(xlsPaySettle, dbPaySettle);
                //// TODO: 2016/7/17 记录db查询到的数据到xls

                paySettleTestResult.setTestResult(result);
                paySettleTestResult.setDbResult(dbPaySettle);
                paySettleTestResultList.add(paySettleTestResult);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


        return paySettleTestResultList;
    }

    public static boolean compareValue(PaySettle xlsPaySettle, PaySettle dbPaySettle) {
        boolean result = false;
        if (dbPaySettle == null) return result;//db未找到数据
        if (!xlsPaySettle.getSettleid().equals(dbPaySettle.getSettleid())) return false;
        if (!xlsPaySettle.getOrderid().equals(dbPaySettle.getOrderid())) return false;
        //// TODO: 2016/7/17 ...


        return true;
    }

    public static void fillExcelWithResult(String filePath, List<PaySettleTestResult> paySettleTestResultList) {

//        String fileName = "testdata/payaccount-testdata.xlsx";
        XSSFWorkbook xssfWorkbook = ExcelUtil.readTestData(filePath);
        XSSFSheet xssfSheet = xssfWorkbook.getSheet("pay_settle_result");
        //// TODO: 2016/7/20 有待优化
        for (int i = 0; i < paySettleTestResultList.size(); i++) {
            PaySettleTestResult paySettleTestResult = paySettleTestResultList.get(0);
            PaySettle dbPaySettle = paySettleTestResult.getDbResult();
            XSSFRow xssfRow = xssfSheet.createRow(i + 1);
            xssfRow.createCell(0).setCellValue(paySettleTestResult.isTestResult());
            xssfRow.createCell(1).setCellValue(paySettleTestResult.getMsg());
            xssfRow.createCell(2).setCellValue(dbPaySettle.getId());
            xssfRow.createCell(3).setCellValue(dbPaySettle.getSettleid());
            xssfRow.createCell(4).setCellValue(dbPaySettle.getOrderid());
            xssfRow.createCell(5).setCellValue(dbPaySettle.getMerchantId());
            xssfRow.createCell(6).setCellValue(dbPaySettle.getProductId());


        }
        ExcelUtil.writeTestData(filePath, xssfWorkbook);
    }
}
