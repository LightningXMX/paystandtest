package com.cmcc.pay.xml.module;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ech0 on 2016/3/13.
 */

@XmlRootElement(name = "PubInfo")
public class PubInfo {

    private String version;

    private String transactionId;

    private String transactionDate;

    private String originId;

    private String digestAlg;

    private String verifyCode;

    public String getVersion() {
        return version;
    }

    @XmlElement(name = "Version")
    public void setVersion(String version) {
        this.version = version;
    }


    public String getTransactionId() {
        return transactionId;
    }

    @XmlElement(name = "TransactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    @XmlElement(name = "TransactionDate")
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getOriginId() {
        return originId;
    }

    @XmlElement(name = "OriginId")
    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getDigestAlg() {
        return digestAlg;
    }

    @XmlElement(name = "DigestAlg")
    public void setDigestAlg(String digestAlg) {
        this.digestAlg = digestAlg;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    @XmlElement(name = "VerifyCode")
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}


