package com.cmcc.pay.xml.module;


import java.util.Map;
import com.cmcc.pay.util.*;
import com.cmcc.pay.xml.module.askforpay.AskForPayBusiData;
import com.cmcc.pay.xml.module.refund.RefundBusiData;
import com.cmcc.pay.xml.module.settleperiodchange.SettlePeriodChangeBusiData;

/**
 * Created by ech0 on 2016/3/15.
 */
public class AdvPayBuilder {

    public static AdvPay build(Map<String, String> input, InterfaceType interfaceType) {


        PubInfo pubInfo = new PubInfo();
        pubInfo.setVersion(input.get(AdvPayEnum.Version));
        pubInfo.setTransactionId(input.get(AdvPayEnum.TransactionId));
        pubInfo.setTransactionDate(input.get(AdvPayEnum.TransactionDate));
        pubInfo.setOriginId(input.get(AdvPayEnum.OriginId));
        pubInfo.setDigestAlg(input.get(AdvPayEnum.DigestAlg));
        String finalVerifyCode = MD5Generator.sign(input, MD5Generator.MD5_KEY);
        pubInfo.setVerifyCode(finalVerifyCode);

        AdvPay advPay = new AdvPay();
        BusiData busiData = null;
        if (InterfaceType.askForPay.equals(interfaceType)) {
            busiData = AskForPayBusiData.build(input);
        } else if (InterfaceType.refund.equals(interfaceType)) {
            busiData = RefundBusiData.build(input);
        } else if (InterfaceType.settlePeriodChange.equals(interfaceType)) {
            busiData = SettlePeriodChangeBusiData.build(input);
        }
        advPay.setPubInfo(pubInfo);
        advPay.setBusiData(busiData);
        return advPay;


    }


}