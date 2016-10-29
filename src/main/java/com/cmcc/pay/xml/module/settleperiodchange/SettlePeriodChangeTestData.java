package com.cmcc.pay.xml.module.settleperiodchange;

import com.cmcc.pay.paystand.test.util.ExcelUtil;
import com.cmcc.pay.paystand.test.util.MD5Generator;
import com.cmcc.pay.paystand.test.util.biz.ExcelFieldEnum;
import com.cmcc.pay.paystand.test.xml.module.AdvPay;
import com.cmcc.pay.paystand.test.xml.module.BusiData;
import com.cmcc.pay.paystand.test.xml.module.PubInfo;
import com.cmcc.pay.paystand.test.xml.module.TestData;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.*;

/**
 * Created by ech0 on 2016/8/2.
 */

public class SettlePeriodChangeTestData extends TestData {


        public SettlePeriodChangeTestData() {}
        public SettlePeriodChangeTestData(boolean init) {
            if (init == true) {
                AdvPay advPay = new AdvPay();
                PubInfo pubInfo = new PubInfo();
                BusiData busiData = new SettlePeriodChangeBusiData();
                advPay.setPubInfo(pubInfo);
                advPay.setBusiData(busiData);
                this.setAdvPay(advPay);
            }
        }

        @Override
        public List<TestData> build(List<XSSFRow> xssfRowList) {

            XSSFRow firstXssfRow = xssfRowList.get(1);//��ȡ�ڶ����ֶ�
            Map<Integer, String> xlsFieldMap = new HashMap<Integer, String>();
            for (int i = 1; i <= firstXssfRow.getLastCellNum(); i++) {
                xlsFieldMap.put(i, ExcelUtil.getValue(firstXssfRow.getCell(i - 1)));
            }
            xssfRowList.remove(0);
            xssfRowList.remove(0);
            List<TestData> settleperiodchangeTestDataList = new ArrayList<TestData>();
            for (XSSFRow xssfRow : xssfRowList) {
                SettlePeriodChangeTestData settleperiodchangeTestData = new SettlePeriodChangeTestData(true);
                Map<String, String> settleperiodchangeTestDataMap = new HashMap<>();
                for (Map.Entry<Integer, String> integerStringEntry : xlsFieldMap.entrySet()) {
                    int fieldNO = integerStringEntry.getKey();
                    String fieldName = integerStringEntry.getValue();
                    XSSFCell fieldValue = xssfRow.getCell(fieldNO - 1);
                    setSettlePeriodChangeTestDataFiled(settleperiodchangeTestData, fieldName, fieldValue, settleperiodchangeTestDataMap);
                }
                String finalVerifyCode = MD5Generator.sign(settleperiodchangeTestDataMap, MD5Generator.MD5_KEY);
                settleperiodchangeTestData.getAdvPay().getPubInfo().setVerifyCode(finalVerifyCode);

                settleperiodchangeTestDataList.add(settleperiodchangeTestData);
            }
            return settleperiodchangeTestDataList;


        }

        private void setSettlePeriodChangeTestDataFiled(SettlePeriodChangeTestData settleperiodchangeTestData, String fieldName, XSSFCell fielValue, Map<String, String> settleperiodchangeTestDataMap) {
            if (ExcelFieldEnum.CommonExcelFieldEnum.version.equals(fieldName)) {
                settleperiodchangeTestDataMap.put(fieldName, ExcelUtil.getValue(fielValue));
                settleperiodchangeTestData.getAdvPay().getPubInfo().setVersion((ExcelUtil.getValue(fielValue)));
            } else if (ExcelFieldEnum.CommonExcelFieldEnum.transactionId.equals(fieldName)) {
                settleperiodchangeTestDataMap.put(fieldName, ExcelUtil.getValue(fielValue));
                settleperiodchangeTestData.getAdvPay().getPubInfo().setTransactionId((ExcelUtil.getValue(fielValue)));
            } else if (ExcelFieldEnum.CommonExcelFieldEnum.transactionDate.equals(fieldName)) {
                settleperiodchangeTestDataMap.put(fieldName, ExcelUtil.getValue(fielValue));
                settleperiodchangeTestData.getAdvPay().getPubInfo().setTransactionDate((ExcelUtil.getValue(fielValue)));
            } else if (ExcelFieldEnum.CommonExcelFieldEnum.originId.equals(fieldName)) {
                settleperiodchangeTestDataMap.put(fieldName, ExcelUtil.getValue(fielValue));
                settleperiodchangeTestData.getAdvPay().getPubInfo().setOriginId((ExcelUtil.getValue(fielValue)));
            } else if (ExcelFieldEnum.CommonExcelFieldEnum.digestAlg.equals(fieldName)) {
                settleperiodchangeTestDataMap.put(fieldName, ExcelUtil.getValue(fielValue));
                settleperiodchangeTestData.getAdvPay().getPubInfo().setDigestAlg((ExcelUtil.getValue(fielValue)));
            } else if (ExcelFieldEnum.CommonExcelFieldEnum.verifyCode.equals(fieldName)) {

                settleperiodchangeTestData.getAdvPay().getPubInfo().setVerifyCode((ExcelUtil.getValue(fielValue)));
            } else if (ExcelFieldEnum.CommonExcelFieldEnum.expectedResults.equals(fieldName)) {
                String expectedResults = ExcelUtil.getValue(fielValue);
                List<String> expectedResultList = Arrays.asList(expectedResults.split(TestData.expectedResultSplit));
                settleperiodchangeTestData.setExpectedResults(expectedResultList);

            } else if (ExcelFieldEnum.CommonExcelFieldEnum.desc.equals(fieldName)) {
                settleperiodchangeTestData.setDesc((ExcelUtil.getValue(fielValue)));
            } else if (ExcelFieldEnum.ChangeSettlePeriodExcelFieldEnum.orderId.equals(fieldName)) {
                settleperiodchangeTestDataMap.put(fieldName, ExcelUtil.getValue(fielValue));
                ((SettlePeriodChangeBusiData) settleperiodchangeTestData.getAdvPay().getBusiData()).setOrderId((ExcelUtil.getValue(fielValue)));
            } else if (ExcelFieldEnum.ChangeSettlePeriodExcelFieldEnum.settlePeriod.equals(fieldName)) {
                settleperiodchangeTestDataMap.put(fieldName, ExcelUtil.getValue(fielValue));
                ((SettlePeriodChangeBusiData) settleperiodchangeTestData.getAdvPay().getBusiData()).setSettlePeriod(ExcelUtil.getValue(fielValue));
            }


        }
    }

