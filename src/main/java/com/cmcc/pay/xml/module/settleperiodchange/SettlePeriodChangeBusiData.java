package com.cmcc.pay.xml.module.settleperiodchange;

import com.cmcc.pay.paystand.test.xml.module.AdvPayEnum;
import com.cmcc.pay.paystand.test.xml.module.BusiData;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Created by echo on 2016/7/24.
 */
@XmlRootElement(name = "BusiData")
public class SettlePeriodChangeBusiData extends BusiData{

    private String orderId;
    private String settlePeriod;

    public String getOrderId() {
        return orderId;
    }

    @XmlElement(name = "OrderId")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSettlePeriod() {
        return settlePeriod;
    }

    @XmlElement(name = "settlePeriod")
    public void setSettlePeriod(String settlePeriod) {
        this.settlePeriod = settlePeriod;
    }


    public static SettlePeriodChangeBusiData build(Map<String,String> input){

        SettlePeriodChangeBusiData busiData = new SettlePeriodChangeBusiData();
        busiData.setOrderId(input.get(AdvPayEnum.OrderId));
        busiData.setSettlePeriod(input.get(AdvPayEnum.SettlePeriod));
        return busiData;

    }
}
