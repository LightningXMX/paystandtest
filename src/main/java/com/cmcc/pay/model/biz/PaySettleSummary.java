package com.cmcc.pay.model.biz;

import java.util.Date;

public class PaySettleSummary {

    private Long id;

    private String platformId;

    private String settleid;

    private Long merchantId;

    private Integer productType;// 产品类型：0 实物 1 虚拟

    private Integer transferType;// 转账类型：0 自动 1 人工

    private Integer settleChannel;// 结算类型：0 接出 1 结入

    private Long totalFee;

    private Long incomeFee;

    private Long merchantFee;

    private Long serviceFee;

    private Integer type;

    private Integer settlePeriod;

    private Integer status;

    private Date auditDate;

    private Integer deleteFlag;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSettleid() {
        return settleid;
    }

    public void setSettleid(String settleid) {
        this.settleid = settleid == null ? null : settleid.trim();
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getIncomeFee() {
        return incomeFee;
    }

    public void setIncomeFee(Long incomeFee) {
        this.incomeFee = incomeFee;
    }

    public Long getMerchantFee() {
        return merchantFee;
    }

    public void setMerchantFee(Long merchantFee) {
        this.merchantFee = merchantFee;
    }

    public Long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSettlePeriod() {
        return settlePeriod;
    }

    public void setSettlePeriod(Integer settlePeriod) {
        this.settlePeriod = settlePeriod;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public Integer getSettleChannel() {
        return settleChannel;
    }

    public void setSettleChannel(Integer settleChannel) {
        this.settleChannel = settleChannel;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

}
