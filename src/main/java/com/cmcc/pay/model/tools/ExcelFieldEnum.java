package com.cmcc.pay.model.tools;

/**
 * 这个类保存Excel中第一行字段值，一定要对应！
 * Created by LIGHTNING on 2016/7/18.
 */

public class ExcelFieldEnum {

    public static class   PayOrderExcelFieldEnum {
        public static String id = "id";

        public static final String orderId = "orderid";

        public static final String transactionId = "transactionid";

        public static final String transactionDate = "transaction_date";
        public static final String orderType = "ordertype";
        public static final String orderInfo = "orderinfo";
        public static final String accountCode = "account_code";
        public static final String accountType = "account_type";
        public static final String payPeriod = "pay_period";
        public static final String tradeNo = "trade_no";
        public static final String thirdOrderId = "third_orderid";
        public static final String failureReasons = "failurereasons";
        public static final String platformId = "platformid";
        public static final String price = "price";
        public static final String type = "type";
        public static final String payTime = "pay_time";
        public static final String createTime = "create_time";
        public static final String updateTime = "update_time";
        public static final String returnUrl = "return_url";
        public static final String notifyUrl = "notify_url";
        public static final String deleteFlag = "delete_flag";
        public static final String merchantId = "merchantid";
        public static final String productId = "productid";
        public static final String royaltyFlag = "royalty_flag";
        public static final String digestAlg = "digest_alg";
        public static final String proCount = "pro_count";
        public static final String order_Type = "order_type";
        public static final String merchantUrl = "merchant_url";
        public static final String status = "status";


    }

    class PaySettleExcelFieldEnum {
        public static final String testResult = "test_result";//结果sheet独有
        public static final String message = "message";//结果sheet独有

        public static final String id = "id";
        public static final String settleId = "settleid";
        public static final String orderId = "orderid";
        //// TODO: 2016/7/19
    }

    class PaySettleSummaryExcelFieldEnum {
        public static final String testResult = "test_result";//结果sheet独有
        public static final String message = "message";//结果sheet独有

        public static final String id = "id";
        public static final String platformId = "platform_id";
        public static final String settleId = "settleid";
        public static final String merchantId = "merchant_id";
        //// TODO: 2016/7/19
    }
}
