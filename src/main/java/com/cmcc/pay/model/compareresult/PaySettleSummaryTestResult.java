package com.cmcc.pay.model.compareresult;

import com.cmcc.pay.model.biz.PaySettle;
import com.cmcc.pay.model.biz.PaySettleSummary;

/**
 * Created by LIGHTNING on 2016/7/17.
 */
public class PaySettleSummaryTestResult {
    private boolean testResult;
    private String msg;
    private PaySettleSummary dbResult;

    public PaySettleSummary getDbResult() {
        return dbResult;
    }

    public void setDbResult(PaySettleSummary dbResult) {
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
