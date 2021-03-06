package com.cmcc.pay.model.tools;

/**
 * 这个类保存Excel中第一行字段值，一定要对应！
 * Created by echo on 2016/7/18.
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
        public static final String platformId = "platformId";
        public static final String merchantId = "merchantId";
        public static final String productId = "productId";

        public static final String productType = "productType";
        public static final String settleChannel = "settleChannel";
        public static final String totalFee = "totalFee";
        public static final String incomeFee = "incomeFee";
        public static final String merchantFee = "merchantFee";
        public static final String serviceFee = "serviceFee";

        public static final String type = "type";
        public static final String royaltyFlag = "royaltyFlag";
        public static final String settlePeriod = "settlePeriod";
        public static final String createTime = "createTime";
        public static final String updateTime = "updateTime";
        public static final String deleteFlag = "deleteFlag";



        public static final String mark = "mark";
        public static final String auditDate = "auditDate";

    }


    class PaySettleSummaryExcelFieldEnum {
        public static final String testResult = "test_result";//结果sheet独有
        public static final String message = "message";//结果sheet独有

        public static final String id = "id";
        public static final String platformId = "platform_id";
        public static final String settleId = "settleid";
        public static final String merchantId = "merchant_id";


        public static final String productType = "product_type";
        public static final String transferType = "transfer_type";
        public static final String settleChannel = "settle_channel";
        public static final String totalFee = "total_fee";


        public static final String incomeFee = "income_fee";
        public static final String merchantFee = "merchant_fee";
        public static final String serviceFee = "service_fee";
        public static final String type = "type";



        public static final String settlePeriod = "settle_period";
        public static final String status = "status";
        public static final String auditDate = "audit_date";
        public static final String deleteFlag = "delete_flag";


        public static final String createTime = "create_time";
        public static final String updateTime = "update_time";

    }
    public static class CommonExcelFieldEnum {
        public static String version = "Version";

        public static String transactionId = "TransactionId";

        public static String transactionDate = "TransactionDate";

        public static String originId = "OriginId";

        public static String digestAlg = "DigestAlg";

        public static String verifyCode = "VerifyCode";

        public static String expectedResults = "expectedResults";

        public static String desc = "desc";
    }

    public static class RefundExcelFieldEnum {

        public static String orderId = "OrderId";

        public static String refundNotifyURL = "refundNotifyURL";

        public static String refundReason = "refundReason";


    }

    public static class ChangeSettlePeriodExcelFieldEnum {

        public static String orderId = "OrderId";
        public static String settlePeriod = "settlePeriod";


    }
}
