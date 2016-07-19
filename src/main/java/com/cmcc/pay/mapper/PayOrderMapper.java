package com.cmcc.pay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cmcc.pay.model.biz.PayOrder;
//import com.cmcc.pay.model.admin.PayOrderReport;
//import com.cmcc.pay.model.admin.PaySettleReport;
//import com.cmcc.pay.model.check.CheckException;
//import com.cmcc.pay.model.check.Statistics;

public interface PayOrderMapper {
    //稽核查询
    Double queryPayOrderCount(@Param(value = "payOrder") PayOrder payOrder,
                              @Param(value = "list") List<String> list);

    //结算报表-支付额
//    List<PaySettleReport> selectSumPrice(Map<String, Object> param);
//
//    //结算报表-从支付表获取退款额
//    List<PaySettleReport> selectRefundFromOrder(Map<String, Object> param);
//
//    //结算报表-支付明细
//    List<PayOrderReport> selectPayOrderDetail(Map<String, Object> param);

    //结算报表-支付明细数
    int selectPayOrderDetailCount(Map<String, Object> param);

    //支付记录调账
    int updateByOrderid(Map<String, Object> param);

    //根据id更新
    int update(PayOrder payOrder);

    List<PayOrder> queryCheckFilePage(Map<String, Object> param);//获取支付记录（以支付平台为准）

    List<PayOrder> selectByOrderid(Map<String, Object> param);

    //根据trade_no获取记录，与上一个method重复
    PayOrder selectByTradeNo(String tradeNo);

    int queryCount(Map<String, Object> param);

    List<PayOrder> queryPagination(Map<String, Object> param);

    //统计每天的支付总额

    double querySum(Map<String, Object> param);

    //插入记录
    int insert(PayOrder payOrder);
    
    //批量插入
    int batchInsert(@Param(value = "list")List<PayOrder> orders, @Param(value = "tableSuffix")String tableSuffix);

    //not use
    int deleteByPrimaryKey(Long id);

    //not use
    PayOrder selectByPrimaryKey(Long id);

    /**
     * 获取昨天pay_order 的交易记录数据，该数据 已经过滤掉  退款记录和分润记录
     * @param map 策略分表用的时间参数
     */
    List<PayOrder> selectForSettle(Map<String, Object> map);

    /**
     * 获取昨天pay_order 的交易记录中是分润的数据
     * @param map 策略分表用的时间参数
     */
    List<PayOrder> selectForShare(Map<String, String> map);

    //微信支付APP订单查询
    List<PayOrder> selectPaysByQuery(PayOrder pay);

    List<Long> selectAllMerchantId(String startTime);

//    List<Statistics> selectAllCountAndPrice(Map<String, Object> map);

    // 如果改订单状态为未支付，则更新该订单传给支付宝或财付通等的订单号
    int updateOrdertypeById(PayOrder payOrder);

    //更改支付方式
    int updatePayTypeById(Map map);

    //not use
    void updateByPrimaryKey(PayOrder pay);

    //查询用户充值，钱包支付，所有金额
    Double selectAllRechargeprice(Map<String, String> param);

    List<PayOrder> selectUnuseOrder(Map<String, String> map);

    Double selectTotalPrice(Map<String, Object> params);
    
    void deletePay(String tradeNo);

    Double queryPayOrderCountLast(@Param(value = "payOrder") PayOrder payOrder,
                              @Param(value = "monthDay") String monthDay);

}
