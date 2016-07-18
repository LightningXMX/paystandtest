package com.cmcc.pay.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cmcc.pay.model.biz.PaySettle;
import org.apache.ibatis.annotations.Param;


public interface PaySettleMapper {

    int insert(PaySettle paySettle);

    int insertBatch(@Param(value = "paySettles") List<PaySettle> paySettles, 
                    @Param(value = "tableName") String tableName);

    /**
     * @return 查询结算单合并需要的数据
     */
    List<PaySettle> selectForSummary(Map<String, Object> map);

    List<PaySettle> selectPaySettles(PaySettle paySettle);

    PaySettle querySettleCount(@Param(value = "paySettle") PaySettle paySettle,
                               @Param(value = "payMerchantList") List<String> payMerchantList);

    List<PaySettle> selectByQuery(@Param(value = "paySettle") PaySettle paySettle,
                                  @Param(value = "payMerchantList") List<String> payMerchantList);

    List<PaySettle> selectByOrderid(Map<String, Object> param);
    PaySettle selectByOrderId(String orderId);

    //结算报表-通过settleid查询名称
//    Lisst<PayTransferReport> selectNameByIds(Map<String, Object> param);

    /* 根据当前退款单号,逻辑删除settle表中数据：delete_flag=1 */
    int delete(PaySettle paySettle);

    int getTotalCount(HashMap<String, Object> refundMap);

    List<Map<String, Object>> queryByPayTime(HashMap<String, Object> refundMap);

    PaySettle selectBySettleid(String settleId);

//    List<PayTransferReport> selectTypesBySettleId(Map<String, Object> param);

    int queryBySettleTime(String settleTime);

    void deleteBySettleTime(String settleTime);

    int update(PaySettle paySettle);
  
    //应缴佣金查询总数
    int queryPayableFeeByTimeCount(HashMap<String, Object> param);
    
    //应缴佣金查询明细
    List<Map<String, Object>> queryPayableFeeByTime(HashMap<String, Object> param);
}
