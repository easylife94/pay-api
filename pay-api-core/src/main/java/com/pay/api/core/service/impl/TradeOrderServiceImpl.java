package com.pay.api.core.service.impl;

import com.pay.api.client.constants.TradeOrderCheckStatusEnum;
import com.pay.api.client.constants.TradeOrderCurrenyEnum;
import com.pay.api.client.constants.TradeOrderNotifyStatusEnum;
import com.pay.api.client.constants.TradeOrderStatusEnum;
import com.pay.api.client.dto.TradeOrderCreateDTO;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.core.dao.TradeOrderDao;
import com.pay.api.core.service.IIdService;
import com.pay.api.core.service.ITradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chenwei
 * @date 2019-01-29
 */
@Service
public class TradeOrderServiceImpl implements ITradeOrderService {

    private final TradeOrderDao tradeOrderDao;
    private final IIdService idService;

    @Autowired
    public TradeOrderServiceImpl(TradeOrderDao tradeOrderDao, IIdService idService) {
        this.tradeOrderDao = tradeOrderDao;
        this.idService = idService;
    }

    @Override
    public TradeOrderDO createTradeOrder(TradeOrderCreateDTO tradeOrderCreateDTO) {
        TradeOrderDO tradeOrderDO = new TradeOrderDO();
        //1.生成订单号
        tradeOrderDO.setId(idService.generateId());
        Date orderTime = new Date();
        //基本状态
        tradeOrderDO.setIsDeleted(false);
        tradeOrderDO.setGmtCreate(orderTime);
        tradeOrderDO.setTradeStatus(TradeOrderStatusEnum.WAIT.getType());
        tradeOrderDO.setCheckStatus(TradeOrderCheckStatusEnum.WAIT.getType());
        tradeOrderDO.setNotifyStatus(TradeOrderNotifyStatusEnum.WAIT.getType());

        //支付方式信息
        tradeOrderDO.setDefrayalChannel(tradeOrderCreateDTO.getDefrayalChannel());
        tradeOrderDO.setDefrayalType(tradeOrderCreateDTO.getDefrayalType());

        //会员信息
        tradeOrderDO.setMemberId(tradeOrderCreateDTO.getMemberId());
        tradeOrderDO.setMemberNumber(tradeOrderCreateDTO.getMemberNumber());
        tradeOrderDO.setMemberName(tradeOrderCreateDTO.getMemberName());

        //会员订单号
        tradeOrderDO.setMemberOrderNumber(tradeOrderCreateDTO.getMemberOrderNumber());

        //代理商信息
        tradeOrderDO.setAgentId(tradeOrderCreateDTO.getAgentId());
        tradeOrderDO.setAgentLevel(tradeOrderCreateDTO.getAgentLevel());
        tradeOrderDO.setAgentName(tradeOrderCreateDTO.getAgentName());
        tradeOrderDO.setAgentNumber(tradeOrderCreateDTO.getAgentNumber());

        //系统订单信息
        tradeOrderDO.setSysOrderNumber(idService.generateTradeOrderNumber());
        tradeOrderDO.setSysOrderTime(orderTime.getTime());

        //币种
        tradeOrderDO.setCurrency(TradeOrderCurrenyEnum.CNY.getType());

        tradeOrderDao.updateByPrimaryKeySelective(tradeOrderDO);
        return tradeOrderDO;
    }

    @Override
    public boolean updateTradeOrder(TradeOrderDO tradeOrderDO) {
        //todo 更新订单
        return false;
    }
}
