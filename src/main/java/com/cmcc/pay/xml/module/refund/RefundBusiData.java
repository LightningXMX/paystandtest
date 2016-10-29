package com.cmcc.pay.xml.module.refund;

import com.cmcc.pay.paystand.test.xml.module.AdvPayEnum;
import com.cmcc.pay.paystand.test.xml.module.BusiData;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Created by echo on 2016/7/24.
 */
//@XmlRootElement(name = "BusiData")
@XmlRootElement(name = "BusiData")
public class RefundBusiData extends BusiData{


    private String orderId;
    private String refundNotifyURL;
    private String refundReason;

    public String getOrderId() {
        return orderId;
    }

    @XmlElement(name = "OrderId")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRefundNotifyURL() {
        return refundNotifyURL;
    }

    @XmlElement(name = "refundNotifyURL")
    public void setRefundNotifyURL(String refundNotifyURL) {
        this.refundNotifyURL = refundNotifyURL;
    }

    public String getRefundReason() {
        return refundReason;
    }

    @XmlElement(name = "refundReason")
    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }



    public static RefundBusiData build(Map<String,String> input){
        RefundBusiData busiData = new RefundBusiData();
        busiData.setOrderId(input.get(AdvPayEnum.OrderId));
        busiData.setRefundNotifyURL(input.get(AdvPayEnum.RefundNotifyURL));
        busiData.setRefundReason(input.get(AdvPayEnum.RefundReason));



        return busiData;
    }
}
