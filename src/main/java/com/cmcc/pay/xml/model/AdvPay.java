package com.cmcc.pay.paystand.test.xml.module;

import javax.xml.bind.annotation.*;

/**
 * Created by ech0 on 2016/3/13.
 */
@XmlRootElement(name = "AdvPay")
public class AdvPay {

    private PubInfo pubInfo;

    private BusiData busiData;


    public PubInfo getPubInfo() {
        return pubInfo;
    }

    @XmlElement(name = "PubInfo")
    public void setPubInfo(PubInfo pubInfo) {
        this.pubInfo = pubInfo;
    }

    public BusiData getBusiData() {
        return busiData;
    }

    @XmlElementRef(name = "BusiData")
    public void setBusiData(BusiData busiData) {
        this.busiData = busiData;
    }

}
