package com.cmcc.pay.model.tools;

import com.cmcc.pay.mapper.PayOrderMapper;
import com.cmcc.pay.model.biz.PayOrder;
import com.cmcc.pay.util.ExcelUtil;
import com.cmcc.pay.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by echo on 2016/7/17.
 */
public class PayOrderTools {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Logger logger = LoggerFactory.getLogger(PayOrderTools.class);

    public static boolean  insertTestData(List<PayOrder> payOrderList) {
        SqlSessionFactory sqlSessionFactory =  MybatisUtil.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            PayOrderMapper payOrderMapper = session.getMapper(PayOrderMapper.class);
            int i =payOrderMapper.batchInsert(payOrderList,new SimpleDateFormat("yyMM").format(new Date()));
            logger.info("success insert "+i+" record.");
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            logger.error("insert testcase fail.");
            return false;
        } finally {
            session.close();
        }
    }


    public static List<PayOrder> build(List<XSSFRow> xssfRowList) throws Exception {
        XSSFRow firstXssfRow = xssfRowList.get(0);//获取第一行字段
        Map<Integer, String> xlsFieldMap = new HashMap<Integer, String>();
        for (int i = 1; i <= firstXssfRow.getLastCellNum(); i++) {
            xlsFieldMap.put(i, ExcelUtil.getValue(firstXssfRow.getCell(i-1)));
        }
        xssfRowList.remove(0);
        List<PayOrder> payOrderList = new ArrayList<PayOrder>();
        for (XSSFRow xssfRow : xssfRowList) {
            PayOrder payOrder = new PayOrder();

            for (Map.Entry<Integer, String> integerStringEntry : xlsFieldMap.entrySet()) {
                int fieldNO = integerStringEntry.getKey();
                String fieldName = integerStringEntry.getValue();
                XSSFCell fieldValue = xssfRow.getCell(fieldNO - 1);
                setPayOrderFiled(payOrder, fieldName, fieldValue);
            }

            payOrderList.add(payOrder);
        }
        return payOrderList;
    }

    //把Excel中cell的值填入对象中
    public static void setPayOrderFiled(PayOrder payOrder, String fieldName, XSSFCell fieldValue) throws Exception{
        if (ExcelFieldEnum.PayOrderExcelFieldEnum.id.equals(fieldName)) {
            if (!StringUtils.isEmpty(ExcelUtil.getValue(fieldValue))) {
                payOrder.setId(Long.parseLong(ExcelUtil.getValue(fieldValue)));
            }
        } else if (ExcelFieldEnum.PayOrderExcelFieldEnum.orderId.equals(fieldName)) {
            payOrder.setOrderid(ExcelUtil.getValue(fieldValue));
        }
        else if (ExcelFieldEnum.PayOrderExcelFieldEnum.transactionId.equals(fieldName)) {
            payOrder.setTransactionid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.transactionDate.equals(fieldName)) {
            payOrder.setTransactionDate(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.orderType.equals(fieldName)) {
            payOrder.setOrdertype(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.orderInfo.equals(fieldName)) {
            payOrder.setOrderinfo(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.accountCode.equals(fieldName)) {
            payOrder.setAccountCode(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.accountType.equals(fieldName)) {
            payOrder.setOrdertype(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.payPeriod.equals(fieldName)) {
            payOrder.setPayPeriod(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.tradeNo.equals(fieldName)) {
            payOrder.setTradeNo(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.thirdOrderId.equals(fieldName)) {
            payOrder.setThirdOrderid(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.failureReasons.equals(fieldName)) {
            payOrder.setFailurereasons(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.platformId.equals(fieldName)) {
            payOrder.setPlatformId(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.price.equals(fieldName)) {
            payOrder.setPrice(new BigDecimal(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.type.equals(fieldName)) {
            payOrder.setType(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.payTime.equals(fieldName)) {
            payOrder.setPayTime(simpleDateFormat.parse(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.createTime.equals(fieldName)) {
            payOrder.setCreateTime(simpleDateFormat.parse(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.updateTime.equals(fieldName)) {
            payOrder.setUpdateTime(simpleDateFormat.parse(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.returnUrl.equals(fieldName)) {
            payOrder.setReturnUrl(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.notifyUrl.equals(fieldName)) {
            payOrder.setNotifyUrl(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.deleteFlag.equals(fieldName)) {
            payOrder.setDeleteFlag(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.merchantId.equals(fieldName)) {
            payOrder.setMerchantid(Long.parseLong(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.productId.equals(fieldName)) {
            payOrder.setProductid(Long.parseLong(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.royaltyFlag.equals(fieldName)) {
            payOrder.setRoyaltyFlag(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.digestAlg.equals(fieldName)) {
            payOrder.setDigestAlg(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.proCount.equals(fieldName)) {
            payOrder.setProCount(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.order_Type.equals(fieldName)) {
            payOrder.setOrderType(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.merchantUrl.equals(fieldName)) {
            payOrder.setMerchantUrl(ExcelUtil.getValue(fieldValue));
        }else if (ExcelFieldEnum.PayOrderExcelFieldEnum.status.equals(fieldName)) {
            //// TODO: 2016/7/19 此处有bug，需要判空，以后改
            if(!StringUtils.isEmpty(ExcelUtil.getValue(fieldValue))) {
                payOrder.setStatus(Integer.parseInt(ExcelUtil.getValue(fieldValue)));
            }
        }


    }
}
