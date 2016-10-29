package com.cmcc.pay.xml.module;

import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.List;

/**
 * Created by echo on 16/7/25.
 */
public abstract class TestData {
    public static String expectedResultSplit = ";";
    private AdvPay advPay;
    private List<String> expectedResults;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getExpectedResults() {
        return expectedResults;
    }

    public void setExpectedResults(List<String> expectedResults) {
        this.expectedResults = expectedResults;
    }

    public AdvPay getAdvPay() {
        return advPay;
    }

    public void setAdvPay(AdvPay advPay) {
        this.advPay = advPay;
    }

    public abstract List<TestData> build(List<XSSFRow> xssfRowList);

}
