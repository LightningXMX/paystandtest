package com.cmcc.pay.xml.module.refund;


import com.cmcc.pay.model.tools.ExcelFieldEnum;
import com.cmcc.pay.util.ExcelUtil;
import com.cmcc.pay.util.MD5Generator;
import com.cmcc.pay.xml.module.AdvPay;
import com.cmcc.pay.xml.module.BusiData;
import com.cmcc.pay.xml.module.PubInfo;
import com.cmcc.pay.xml.module.TestData;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.*;

/**
 * Created by echo on 16/7/25.
 */
public class RefundTestData extends TestData {


    public RefundTestData(){}
    public RefundTestData(boolean init){
        if (init==true){
            AdvPay advPay = new AdvPay();
            PubInfo pubInfo = new PubInfo();
            BusiData busiData = new RefundBusiData();
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
            xlsFieldMap.put(i, ExcelUtil.getValue(firstXssfRow.getCell(i-1)));
        }
        xssfRowList.remove(0);
        xssfRowList.remove(0);
        List<TestData> refundTestDataList = new ArrayList<TestData>();
        for (XSSFRow xssfRow : xssfRowList) {
            RefundTestData refundTestData = new RefundTestData(true);
            Map<String, String> refundTestDataMap = new HashMap<>();
            for (Map.Entry<Integer, String> integerStringEntry : xlsFieldMap.entrySet()) {
                int fieldNO = integerStringEntry.getKey();
                String fieldName = integerStringEntry.getValue();
                XSSFCell fieldValue = xssfRow.getCell(fieldNO - 1);
                setRefundTestDataFiled(refundTestData, fieldName, fieldValue,refundTestDataMap);
            }
            String finalVerifyCode = MD5Generator.sign(refundTestDataMap, MD5Generator.MD5_KEY);
            refundTestData.getAdvPay().getPubInfo().setVerifyCode(finalVerifyCode);

            refundTestDataList.add(refundTestData);
        }
        return refundTestDataList;


    }

    private void  setRefundTestDataFiled(RefundTestData refundTestData,String fieldName,XSSFCell fielValue,Map<String, String> refundTestDataMap) {
        if (ExcelFieldEnum.CommonExcelFieldEnum.version.equals(fieldName)) {
            refundTestDataMap.put(fieldName,ExcelUtil.getValue(fielValue));
            refundTestData.getAdvPay().getPubInfo().setVersion((ExcelUtil.getValue(fielValue)));
        } else if (ExcelFieldEnum.CommonExcelFieldEnum.transactionId.equals(fieldName)){
            refundTestDataMap.put(fieldName,ExcelUtil.getValue(fielValue));
            refundTestData.getAdvPay().getPubInfo().setTransactionId((ExcelUtil.getValue(fielValue)));
        }else if (ExcelFieldEnum.CommonExcelFieldEnum.transactionDate.equals(fieldName)){
            refundTestDataMap.put(fieldName,ExcelUtil.getValue(fielValue));
            refundTestData.getAdvPay().getPubInfo().setTransactionDate((ExcelUtil.getValue(fielValue)));
        }else if (ExcelFieldEnum.CommonExcelFieldEnum.originId.equals(fieldName)){
            refundTestDataMap.put(fieldName,ExcelUtil.getValue(fielValue));
            refundTestData.getAdvPay().getPubInfo().setOriginId((ExcelUtil.getValue(fielValue)));
        }else if (ExcelFieldEnum.CommonExcelFieldEnum.digestAlg.equals(fieldName)){
            refundTestDataMap.put(fieldName,ExcelUtil.getValue(fielValue));
            refundTestData.getAdvPay().getPubInfo().setDigestAlg((ExcelUtil.getValue(fielValue)));
        }else if (ExcelFieldEnum.CommonExcelFieldEnum.verifyCode.equals(fieldName)){

            refundTestData.getAdvPay().getPubInfo().setVerifyCode((ExcelUtil.getValue(fielValue)));
        }else if (ExcelFieldEnum.CommonExcelFieldEnum.expectedResults.equals(fieldName)){
            String expectedResults =ExcelUtil.getValue(fielValue);
            List<String> expectedResultList =  Arrays.asList(expectedResults.split(TestData.expectedResultSplit));
            refundTestData.setExpectedResults(expectedResultList);

        }else if (ExcelFieldEnum.CommonExcelFieldEnum.desc.equals(fieldName)){
            refundTestData.setDesc((ExcelUtil.getValue(fielValue)));
        }else if (ExcelFieldEnum.RefundExcelFieldEnum.orderId.equals(fieldName)){
            refundTestDataMap.put(fieldName,ExcelUtil.getValue(fielValue));
            ((RefundBusiData)refundTestData.getAdvPay().getBusiData()).setOrderId((ExcelUtil.getValue(fielValue)));
        }else if (ExcelFieldEnum.RefundExcelFieldEnum.refundNotifyURL.equals(fieldName)){
            refundTestDataMap.put(fieldName,ExcelUtil.getValue(fielValue));
            ((RefundBusiData)refundTestData.getAdvPay().getBusiData()).setRefundNotifyURL((ExcelUtil.getValue(fielValue)));
        }else if (ExcelFieldEnum.RefundExcelFieldEnum.refundReason.equals(fieldName)){
            refundTestDataMap.put(fieldName,ExcelUtil.getValue(fielValue));
            ((RefundBusiData)refundTestData.getAdvPay().getBusiData()).setRefundReason((ExcelUtil.getValue(fielValue)));
        }


    }
}
