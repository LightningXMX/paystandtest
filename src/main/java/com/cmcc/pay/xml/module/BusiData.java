package com.cmcc.pay.xml.module;

import com.cmcc.pay.paystand.test.xml.module.askforpay.AskForPayBusiData;
import com.cmcc.pay.paystand.test.xml.module.refund.RefundBusiData;
import com.cmcc.pay.paystand.test.xml.module.settleperiodchange.SettlePeriodChangeBusiData;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by ech0 on 2016/3/14.
 */
@XmlSeeAlso({AskForPayBusiData.class,RefundBusiData.class, SettlePeriodChangeBusiData.class})
public abstract class BusiData {

}


