package com.cmcc.pay.model.compareresult;

import com.cmcc.pay.model.biz.PaySettle;

import java.io.PipedReader;

/**
 * Created by echo on 2016/7/17.
 */
public class PaySettleTestResult {
    private boolean testResult;
    private String msg;
    private PaySettle dbResult;

    public PaySettle getDbResult() {
        return dbResult;
    }

    public void setDbResult(PaySettle dbResult) {
        this.dbResult = dbResult;
    }

    public boolean isTestResult() {
        return testResult;
    }

    public void setTestResult(boolean testResult) {
        this.testResult = testResult;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
