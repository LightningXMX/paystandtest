package com.cmcc.pay.model.compareresult;

import com.cmcc.pay.model.biz.PaySettle;

import java.io.PipedReader;

/**
 * Created by LIGHTNING on 2016/7/17.
 */
public class PaySettleTestResult {
    private boolean testResult;
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
}
