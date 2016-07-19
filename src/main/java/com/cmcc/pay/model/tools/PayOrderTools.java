package com.cmcc.pay.model.tools;

import com.cmcc.pay.model.biz.PayOrder;
import com.cmcc.pay.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by LIGHTNING on 2016/7/17.
 */
public class PayOrderTools {
    public static List<PayOrder> build(List<XSSFRow> xssfRowList) {
        XSSFRow firstXssfRow = xssfRowList.get(0);//获取第一行字段
        Map<Integer, String> xlsFieldMap = new HashMap<Integer, String>();
        for (int i = 1; i <= firstXssfRow.getLastCellNum(); i++) {
            xlsFieldMap.put(i, ExcelUtil.getValue(firstXssfRow.getCell(i-1)));
        }
        xssfRowList.remove(0);
//        Iterator<Cell> iterator = firstXssfRow.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

        List<PayOrder> payOrderList = new ArrayList<PayOrder>();
        for (XSSFRow xssfRow : xssfRowList) {
            PayOrder payOrder = new PayOrder();


            for (Map.Entry<Integer, String> integerStringEntry : xlsFieldMap.entrySet()) {
                int fieldNO = integerStringEntry.getKey();
                String fieldName = integerStringEntry.getValue();
                XSSFCell fieldValue = xssfRow.getCell(fieldNO - 1);
                setPayOrderFiled(payOrder, fieldName, fieldValue);
            }

//            Iterator<Cell> iterator1 = xssfRow.iterator();
//            while (iterator1.hasNext()){
//                System.out.println(iterator1.next());
//            }
//            payOrder.setId(Long.parseLong(ExcelUtil.getValue(xssfRow.getCell(0))));
//            payOrder.setOrderid(ExcelUtil.getValue(xssfRow.getCell(1)));
//            payOrder.setTransactionid(ExcelUtil.getValue(xssfRow.getCell(2)));
//            payOrder.setTransactionDate(ExcelUtil.getValue(xssfRow.getCell(3)));
            payOrderList.add(payOrder);
            //// TODO: 2016/7/17  
        }

        return payOrderList;
    }

    //把Excel中cell的值填入对象中
    public static void setPayOrderFiled(PayOrder payOrder, String fieldName, XSSFCell fieldValue) {
        if (ExcelFieldEnum.id.equals(fieldName)) {

            payOrder.setId(Long.parseLong(ExcelUtil.getValue(fieldValue)));
        } else if (ExcelFieldEnum.orderId.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }
        else if (ExcelFieldEnum.transactionId.equals(fieldName)) {
            payOrder.setTransactionid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.transactionDate.equals(fieldName)) {
            payOrder.setTransactionDate(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.orderType.equals(fieldName)) {
            payOrder.setOrdertype(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.orderInfo.equals(fieldName)) {
            payOrder.setOrderinfo(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.accountCode.equals(fieldName)) {
            payOrder.setAccountCode(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.accountType.equals(fieldName)) {
            payOrder.setOrdertype(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.payPeriod.equals(fieldName)) {
            payOrder.setPayPeriod(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.tradeNo.equals(fieldName)) {
            payOrder.setTradeNo(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.thirdOrderId.equals(fieldName)) {
            payOrder.setThirdOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.failureReasons.equals(fieldName)) {
            payOrder.setFailurereasons(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.platformId.equals(fieldName)) {
            payOrder.setPlatformId(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.price.equals(fieldName)) {
            payOrder.setPrice(new BigDecimal(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.type.equals(fieldName)) {
            payOrder.setType(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.payTime.equals(fieldName)) {
            payOrder.setPayTime(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.createTime.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.updateTime.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.returnUrl.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.notifyUrl.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.deleteFlag.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.merchantId.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.productId.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.royaltyFlag.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.digestAlg.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.proCount.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.order_Type.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.merchantUrl.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.status.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }

        // TODO: 2016/7/18
        //...

    }
}
