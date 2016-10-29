package com.cmcc.pay.model.tools;

import com.cmcc.pay.mapper.PaySettleSummaryMapper;
import com.cmcc.pay.model.biz.PaySettleSummary;
import com.cmcc.pay.model.compareresult.PaySettleSummaryTestResult;
import com.cmcc.pay.util.ExcelUtil;
import com.cmcc.pay.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by echo on 2016/7/17.
 */
public class PaySettleSummaryTools {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static List<PaySettleSummary> build(List<XSSFRow> xssfRowList) throws Exception {
        XSSFRow firstXssfRow = xssfRowList.get(0);//获取第一行字段
        Map<Integer, String> xlsFieldMap = new HashMap<Integer, String>();
        for (int i = 1; i <= firstXssfRow.getLastCellNum(); i++) {
            xlsFieldMap.put(i, ExcelUtil.getValue(firstXssfRow.getCell(i - 1)));
        }
        xssfRowList.remove(0);
        List<PaySettleSummary> paySettleSummaryList = new ArrayList<PaySettleSummary>();
        for (XSSFRow xssfRow : xssfRowList) {
            PaySettleSummary paySettleSummary = new PaySettleSummary();

            for (Map.Entry<Integer, String> integerStringEntry : xlsFieldMap.entrySet()) {
                int fieldNO = integerStringEntry.getKey();
                String fieldName = integerStringEntry.getValue();
                XSSFCell fieldValue = xssfRow.getCell(fieldNO - 1);
                setPaySettleSummaryFiled(paySettleSummary, fieldName, fieldValue);
            }

            paySettleSummaryList.add(paySettleSummary);
        }
        return paySettleSummaryList;

    }

    public static void setPaySettleSummaryFiled(PaySettleSummary paySettleSummary, String fieldName, XSSFCell fieldValue) throws Exception {
        if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.id.equals(fieldName)) {
            if (!StringUtils.isEmpty(ExcelUtil.getValue(fieldValue))) {
                paySettleSummary.setId(Long.parseLong(ExcelUtil.getValue(fieldValue)));
            }
        } else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.platformId.equals(fieldName)) {
            paySettleSummary.setPlatformId(ExcelUtil.getValue(fieldValue));
        } else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.settleId.equals(fieldName)) {
            paySettleSummary.setSettleid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.merchantId.equals(fieldName)) {
            paySettleSummary.setMerchantId(Long.parseLong(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.productType.equals(fieldName)) {
            paySettleSummary.setProductType(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.transferType.equals(fieldName)) {
            paySettleSummary.setTransferType(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.settleChannel.equals(fieldName)) {
            paySettleSummary.setSettleChannel(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.totalFee.equals(fieldName)) {
            paySettleSummary.setTotalFee(Long.parseLong(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.incomeFee.equals(fieldName)) {
            paySettleSummary.setIncomeFee(Long.parseLong(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.merchantFee.equals(fieldName)) {
            paySettleSummary.setMerchantFee(Long.parseLong(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.serviceFee.equals(fieldName)) {
            paySettleSummary.setServiceFee(Long.parseLong(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.type.equals(fieldName)) {
            paySettleSummary.setType(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }

        else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.settlePeriod.equals(fieldName)) {
            paySettleSummary.setSettlePeriod(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.status.equals(fieldName)) {
            paySettleSummary.setStatus(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.auditDate.equals(fieldName)) {
            paySettleSummary.setAuditDate(Date.valueOf(ExcelUtil.getValue(fieldValue)));//////此处需要仔细验证
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.deleteFlag.equals(fieldName)) {
            paySettleSummary.setDeleteFlag(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }

        else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.createTime.equals(fieldName)) {
            paySettleSummary.setCreateTime(Date.valueOf(ExcelUtil.getValue(fieldValue)));//////此处需要仔细验证
        }else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.updateTime.equals(fieldName)) {
            paySettleSummary.setUpdateTime(simpleDateFormat.parse(ExcelUtil.getValue(fieldValue)));
        }


    }

    public static List<PaySettleSummaryTestResult> verificate(List<PaySettleSummary> paySettleSummaryList) {

        List<PaySettleSummaryTestResult> paySettleSummaryTestResultList = new ArrayList<PaySettleSummaryTestResult>();
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();

        SqlSession session = sqlSessionFactory.openSession();
        PaySettleSummaryMapper paySettleSummaryMapper = session.getMapper(PaySettleSummaryMapper.class);
        try {
            for (PaySettleSummary xlsPaySettleSummary : paySettleSummaryList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("platformId", xlsPaySettleSummary.getPlatformId());
                map.put("settleChannel", xlsPaySettleSummary.getSettleChannel());
                map.put("merchantId", xlsPaySettleSummary.getMerchantId());

                PaySettleSummary dbPaySettleSummary = paySettleSummaryMapper.selectByMultiCondition(map);
                PaySettleSummaryTestResult paySettleSummaryTestResult = new PaySettleSummaryTestResult();

                boolean result = false;
                if (dbPaySettleSummary == null) {

                    paySettleSummaryTestResult.setMsg("There is no recode in database.");
                }
                result = compareValue(xlsPaySettleSummary, dbPaySettleSummary);
                //// TODO: 2016/7/17 记录db查询到的数据到xls

                paySettleSummaryTestResult.setTestResult(result);
                paySettleSummaryTestResult.setDbResult(dbPaySettleSummary);
                paySettleSummaryTestResultList.add(paySettleSummaryTestResult);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


        return paySettleSummaryTestResultList;
    }

    public static boolean compareValue(PaySettleSummary xlsPaySettleSummary, PaySettleSummary dbPaySettleSummary) {
        boolean result = false;
        if (dbPaySettleSummary == null) return result;//db未找到数据

        if (!xlsPaySettleSummary.getPlatformId().equals(dbPaySettleSummary.getPlatformId())) return false;

        if (!xlsPaySettleSummary.getSettleid().equals(dbPaySettleSummary.getSettleid())) return false;

        if (!xlsPaySettleSummary.getMerchantId().equals(dbPaySettleSummary.getMerchantId())) return false;

        if (!xlsPaySettleSummary.getProductType().equals(dbPaySettleSummary.getProductType())) return false;

        if (!xlsPaySettleSummary.getTransferType().equals(dbPaySettleSummary.getTransferType())) return false;

        if (!xlsPaySettleSummary.getSettleChannel().equals(dbPaySettleSummary.getSettleChannel())) return false;

        if (!xlsPaySettleSummary.getTotalFee().equals(dbPaySettleSummary.getTotalFee())) return false;

        if (!xlsPaySettleSummary.getIncomeFee().equals(dbPaySettleSummary.getIncomeFee())) return false;

        if (!xlsPaySettleSummary.getMerchantFee().equals(dbPaySettleSummary.getMerchantFee())) return false;

        if (!xlsPaySettleSummary.getServiceFee().equals(dbPaySettleSummary.getServiceFee())) return false;

        if (!xlsPaySettleSummary.getType().equals(dbPaySettleSummary.getType())) return false;

        if (!xlsPaySettleSummary.getSettlePeriod().equals(dbPaySettleSummary.getSettlePeriod())) return false;

        if (!xlsPaySettleSummary.getStatus().equals(dbPaySettleSummary.getStatus())) return false;

        if (!xlsPaySettleSummary.getAuditDate().equals(dbPaySettleSummary.getAuditDate())) return false;

        if (!xlsPaySettleSummary.getDeleteFlag().equals(dbPaySettleSummary.getDeleteFlag())) return false;

        if (!xlsPaySettleSummary.getDeleteFlag().equals(dbPaySettleSummary.getDeleteFlag())) return false;

        return true;
    }

    public static void fillExcelWithResult(String filePath, List<PaySettleSummaryTestResult> paySettleSummaryTestResultList) {

        XSSFWorkbook xssfWorkbook = ExcelUtil.readTestData(filePath);
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(ExcelInfo.ExcelPaySettleSummaryResultSheetName);
        List<XSSFRow> xssfRowList = ExcelUtil.convertXLS(xssfWorkbook, ExcelInfo.ExcelPaySettleSummaryResultSheetName);
        XSSFRow firstXssfRow = xssfRowList.get(0);//获取第一行字段
        Map<Integer, String> xlsFieldMap = new HashMap<Integer, String>();
        for (int i = 1; i <= firstXssfRow.getLastCellNum(); i++) {
            xlsFieldMap.put(i, ExcelUtil.getValue(firstXssfRow.getCell(i - 1)));
        }

        for (int i = 0; i < paySettleSummaryTestResultList.size(); i++) {
            PaySettleSummaryTestResult paySettleSummaryTestResult = paySettleSummaryTestResultList.get(i);
//            PaySettleSummary dbPaySettleSummaryTestResult = paySettleSummaryTestResult.getDbResult();//dbPaySettleSummaryTestResult有可能为空
            XSSFRow xssfRow = xssfSheet.createRow(i + 1);

            for (Map.Entry<Integer, String> integerStringEntry : xlsFieldMap.entrySet()) {
                int fieldNo = integerStringEntry.getKey();
                String fieldName = integerStringEntry.getValue();
                xssfRow.createCell(fieldNo - 1).setCellValue(getCellValue(fieldName, paySettleSummaryTestResult));


            }

        }
        ExcelUtil.writeTestData(filePath, xssfWorkbook);
    }

    public static String getCellValue(String fieldName, PaySettleSummaryTestResult paySettleSummaryTestResult) {

        if (StringUtils.isEmpty(paySettleSummaryTestResult)) return "";

        if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.testResult.equals(fieldName)) {
            return String.valueOf(paySettleSummaryTestResult.isTestResult());
        } else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.message.equals(fieldName)) {
            return paySettleSummaryTestResult.getMsg();
        }

        if (paySettleSummaryTestResult.getDbResult() == null) {
            return "";
        }
        if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.id.equals(fieldName)) {
            return String.valueOf(paySettleSummaryTestResult.getDbResult().getId());
        } else if (ExcelFieldEnum.PaySettleSummaryExcelFieldEnum.platformId.equals(fieldName)) {
            return paySettleSummaryTestResult.getDbResult().getPlatformId();
        }
        //// TODO: 16/7/20
        return "";
    }
}
