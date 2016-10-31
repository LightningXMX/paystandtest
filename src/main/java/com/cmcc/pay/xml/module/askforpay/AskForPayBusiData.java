package com.cmcc.pay.xml.module.askforpay;


import com.cmcc.pay.xml.module.AdvPayEnum;
import com.cmcc.pay.xml.module.BusiData;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Created by echo on 2016/7/24.
 */
@XmlRootElement(name = "BusiData")
public class AskForPayBusiData extends BusiData {
    private String accountType;
    private String accountCode;
    private String payInfo;
    private String orderId;
    private String merchantId;
    private String productId;
    private String proCount;
    private String payAmount;
    private String payPeriod;
    private String payNotifyIntURL;
    private String payNotifyPageURL;
    private String royaltyFlag;
    private String merchantUrl;


    public String getAccountType() {
        return accountType;
    }

    @XmlElement(name = "AccountType")
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountCode() {
        return accountCode;
    }
    @XmlElement(name = "AccountCode")
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getPayInfo() {
        return payInfo;
    }
    @XmlElement(name = "PayInfo")
    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getOrderId() {
        return orderId;
    }
    @XmlElement(name = "OrderId")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMerchantId() {
        return merchantId;
    }
    @XmlElement(name = "MerchantId")
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductId() {
        return productId;
    }
    @XmlElement(name = "ProductId")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProCount() {
        return proCount;
    }
    @XmlElement(name = "ProCount")
    public void setProCount(String proCount) {
        this.proCount = proCount;
    }

    public String getPayAmount() {
        return payAmount;
    }
    @XmlElement(name = "PayAmount")
    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayPeriod() {
        return payPeriod;
    }
    @XmlElement(name = "PayPeriod")
    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public String getPayNotifyIntURL() {
        return payNotifyIntURL;
    }
    @XmlElement(name = "PayNotifyIntURL")
    public void setPayNotifyIntURL(String payNotifyIntURL) {
        this.payNotifyIntURL = payNotifyIntURL;
    }

    public String getPayNotifyPageURL() {
        return payNotifyPageURL;
    }
    @XmlElement(name = "PayNotifyPageURL")
    public void setPayNotifyPageURL(String payNotifyPageURL) {
        this.payNotifyPageURL = payNotifyPageURL;
    }

    public String getRoyaltyFlag() {
        return royaltyFlag;
    }
    @XmlElement(name = "RoyaltyFlag")
    public void setRoyaltyFlag(String royaltyFlag) {
        this.royaltyFlag = royaltyFlag;
    }

    public String getMerchantUrl() {
        return merchantUrl;
    }
    @XmlElement(name = "MerchantUrl")
    public void setMerchantUrl(String merchantUrl) {
        this.merchantUrl = merchantUrl;
    }



    public static BusiData build(Map<String,String> input){

        AskForPayBusiData busiData = new AskForPayBusiData();
        busiData.setAccountType(input.get(AdvPayEnum.AccountType));
        busiData.setAccountCode(input.get(AdvPayEnum.AccountCode));
        busiData.setPayInfo(input.get(AdvPayEnum.PayInfo));
        busiData.setOrderId(input.get(AdvPayEnum.OrderId));
        busiData.setMerchantId(input.get(AdvPayEnum.MerchantId));
        busiData.setProductId(input.get(AdvPayEnum.ProductId));
        busiData.setProCount(input.get(AdvPayEnum.ProCount));
        busiData.setPayAmount(input.get(AdvPayEnum.PayAmount));
        busiData.setPayPeriod(input.get(AdvPayEnum.PayPeriod));
        busiData.setPayNotifyIntURL(input.get(AdvPayEnum.PayNotifyIntURL));
        busiData.setPayNotifyPageURL(input.get(AdvPayEnum.PayNotifyPageURL));
        busiData.setRoyaltyFlag(input.get(AdvPayEnum.RoyaltyFlag));
        busiData.setMerchantUrl(input.get(AdvPayEnum.MerchantUrl));

        return busiData;

    }

}
