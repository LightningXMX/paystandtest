package com.cmcc.pay.mapper;

import java.util.List;
import java.util.Map;

import com.cmcc.pay.model.biz.PaySettle;
import com.cmcc.pay.model.biz.PaySettleSummary;
import org.apache.ibatis.annotations.Param;

//import com.cmcc.pay.model.account.PaySettle;
//import com.cmcc.pay.model.account.PaySettleSummary;
//import com.cmcc.pay.model.admin.PaySettleReport;
//import com.cmcc.pay.model.admin.PayTransferReport;

public interface PaySettleSummaryMapper {

    int insertBatch(List<PaySettleSummary> list);

    int deleteByPrimaryKey(Long id);

    /**遍历 查询 所有通过已终审数据*/
    List<PaySettleSummary> getAllPassAppeal(Map<String, Object> param);

    /**报表结算总额*/
//    List<PaySettleReport> selectSumSummPrice(Map<String, Object> param);

    /**报表-通过settleid查名称*/
//    List<PayTransferReport> selectMerNameByIds(Map<String, Object> param);

    public List<PaySettleSummary> queryByDate(Map<String, Object> param);
    
    List<PaySettleSummary> selectBySettleids(Map<String, Object> param);

    /**修改审计状态*/
    int updateStatusByPrimaryKey(Map<String, Object> map);

    PaySettleSummary querySettleCount(@Param(value = "paySettleSummary") PaySettleSummary paySettleSummary,
                                      @Param(value = "payMerchantList") List<String> payMerchantList);

    List<PaySettleSummary> selectByQuery(@Param(value = "paySettleSummary") PaySettleSummary paySettleSummary,
                                         @Param(value = "payMerchantList") List<String> payMerchantList);

    int updateByMerchantId(@Param(value = "paySettleSummary") PaySettleSummary paySettleSummary,
                           @Param(value = "payMerchantList") List<String> payMerchantList);

    Long selectForRefundParam(Map<String, Object> map);

    PaySettleSummary selectBySettleid(String settleid);

    int updateFeeWithRefund(PaySettle paySettle);
    
    int updateFeeWithSettlePeriodChanged(PaySettle paySettle);

    int queryBySettleTime(String settleTime);
    
    PaySettleSummary select(PaySettleSummary paySettleSummary);
    
    List<PaySettleSummary> selectByTimeOther(Map<String, Object> param);

    PaySettleSummary selectByMultiCondition(Map<String, Object> param);

    long countAll();
}
