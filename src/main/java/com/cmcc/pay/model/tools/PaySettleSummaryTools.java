package com.cmcc.pay.model.tools;

import com.cmcc.pay.model.biz.PaySettle;
import com.cmcc.pay.model.biz.PaySettleSummary;
import com.cmcc.pay.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LIGHTNING on 2016/7/17.
 */
public class PaySettleSummaryTools {

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
        }

        //// TODO: 2016/7/19
    }
}
