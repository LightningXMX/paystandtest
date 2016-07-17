package com.cmcc.pay.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String orderid;

    private String transactionid;

    private String transactionDate;

    private Integer ordertype;//订单状态

    private String orderinfo;

    private String accountCode;

    private Integer accountType;

    private String payPeriod;

    private String tradeNo;

    private String thirdOrderid;

    private String failurereasons;

    private String platformId;

    private BigDecimal price;

    private Integer type;

    private Date payTime;

    private String returnUrl;

    private String notifyUrl;

    private Integer deleteFlag;

    private Long merchantid;

    private Long productid;

    private Date createTime;

    private Date updateTime;

    private Date startTime;

    private Date endTime;

    private Integer digestAlg;

    private Integer royaltyFlag;

    private Integer proCount;

    private String merchantUrl;

    private Integer orderType;//订单类型
    
    private String mergeInfo;
    
    private String productName;
    
    private Integer status; // 转账结果
    
    private String riskInfo; // 风控结果

    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMergeInfo() {
        return mergeInfo;
    }

    public void setMergeInfo(String mergeInfo) {
        this.mergeInfo = mergeInfo;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getProCount() {
        return proCount;
    }

    public void setProCount(Integer proCount) {
        this.proCount = proCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDigestAlg() {
        return digestAlg;
    }

    public void setDigestAlg(Integer digestAlg) {
        this.digestAlg = digestAlg;
    }

    public Integer getRoyaltyFlag() {
        return royaltyFlag;
    }

    public void setRoyaltyFlag(Integer royaltyFlag) {
        this.royaltyFlag = royaltyFlag;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid == null ? null : transactionid.trim();
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate == null ? null : transactionDate.trim();
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public String getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(String orderinfo) {
        this.orderinfo = orderinfo == null ? null : orderinfo.trim();
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode == null ? null : accountCode.trim();
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod == null ? null : payPeriod.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getThirdOrderid() {
        return thirdOrderid;
    }

    public void setThirdOrderid(String thirdOrderid) {
        this.thirdOrderid = thirdOrderid == null ? null : thirdOrderid.trim();
    }

    public String getFailurereasons() {
        return failurereasons;
    }

    public void setFailurereasons(String failurereasons) {
        this.failurereasons = failurereasons == null ? null : failurereasons.trim();
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId == null ? null : platformId.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl == null ? null : returnUrl.trim();
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Long merchantid) {
        this.merchantid = merchantid;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public String getMerchantUrl() {
        return merchantUrl;
    }

    public void setMerchantUrl(String merchantUrl) {
        this.merchantUrl = merchantUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.type = status;
    }
    
	@Override
	public String toString() {
		return "PayOrder [id=" + id + ", orderid=" + orderid + ", transactionid=" + transactionid + ", transactionDate="
				+ transactionDate + ", ordertype=" + ordertype + ", orderinfo=" + orderinfo + ", accountCode="
				+ accountCode + ", accountType=" + accountType + ", payPeriod=" + payPeriod + ", tradeNo=" + tradeNo
				+ ", thirdOrderid=" + thirdOrderid + ", failurereasons=" + failurereasons + ", platformId=" + platformId
				+ ", price=" + price + ", type=" + type + ", payTime=" + payTime + ", returnUrl=" + returnUrl
				+ ", notifyUrl=" + notifyUrl + ", deleteFlag=" + deleteFlag + ", merchantid=" + merchantid
				+ ", productid=" + productid + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", digestAlg=" + digestAlg + ", royaltyFlag="
				+ royaltyFlag + ", proCount=" + proCount + ", merchantUrl=" + merchantUrl + ", orderType=" + orderType
				+ ", mergeInfo=" + mergeInfo + "]";
	}

    public String getRiskInfo() {
        return riskInfo;
    }

    public void setRiskInfo(String riskInfo) {
        this.riskInfo = riskInfo;
    }

}
