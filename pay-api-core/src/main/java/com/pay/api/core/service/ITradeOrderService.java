package com.pay.api.core.service;

import com.pay.api.client.dto.TradeOrderCreateDTO;
import com.pay.api.client.model.TradeOrderDO;

/**
 * 订单服务接口
 *
 * @author chenwei
 * @date 2019-01-29
 */
public interface ITradeOrderService {


    /**
     * 创建订单对象
     * 会员订单号不能重复
     *
     * @param tradeOrderCreateDTO 创建订单参数
     * @return 订单DO
     */
    TradeOrderDO createTradeOrder(TradeOrderCreateDTO tradeOrderCreateDTO);

    /**
     * 更新订单记录ØØ
     *
     * @param tradeOrderDO 订单
     * @return 保存成功返回true
     */
    boolean updateTradeOrder(TradeOrderDO tradeOrderDO);

    /**
     * 保存订单对象到数据库
     *
     * @param tradeOrderDO 订单对象
     * @return 保存成功返回true
     */
    boolean saveTradeOrder(TradeOrderDO tradeOrderDO);

    /**
     * 查找唯一订单
     *
     * @param sysOrderNumber    系统订单号
     * @param memberNumber      会员编号
     * @param memberOrderNumber 会员订单号（在会员编号参数不为空时生效）
     * @return 返回唯一订单
     */
    TradeOrderDO findOneOrder(String sysOrderNumber, String memberNumber, String memberOrderNumber);

    /**
     * 会员订单号是否存在
     *
     * @param memberNumber      会员编号
     * @param memberOrderNumber 会员订单号
     * @return 当且仅当会员订单号不存在时返回false
     */
    Boolean memberOrderExisted(String memberNumber, String memberOrderNumber);

}
