package com.pay.api.core.method.impl;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.constants.ApiPayMethodNames;
import com.pay.api.client.constants.ApiPayMethodResultEnum;
import com.pay.api.client.constants.ApiPayOrderQueryErrorEnum;
import com.pay.api.client.constants.PayApiBeanPrefix;
import com.pay.api.client.dto.ApiPayMethodParamsCheckResultDTO;
import com.pay.api.client.dto.ApiPayMethodResultDTO;
import com.pay.api.client.dto.method.ApiPayOrderQueryDTO;
import com.pay.api.client.dto.method.ApiPayOrderQueryResultDTO;
import com.pay.api.client.model.TradeOrderDO;
import com.pay.api.client.utils.DateUtils;
import com.pay.api.core.dao.TradeOrderDao;
import com.pay.api.core.method.AbstractPayApiMethod;
import com.pay.api.core.service.ITradeOrderService;
import com.pay.center.client.dto.service.MemberDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单查询方法
 *
 * @author chenwei
 * @date 2019/1/16 16:52
 */
@Component(PayApiBeanPrefix.METHOD + ApiPayMethodNames.ODER_QUERY)
public class PayApiMethodOrderQuery extends AbstractPayApiMethod<ApiPayOrderQueryDTO> {

    private static final Logger logger = LoggerFactory.getLogger(PayApiMethodOrderQuery.class);

    private final ITradeOrderService tradeOrderService;

    @Autowired
    public PayApiMethodOrderQuery(ITradeOrderService tradeOrderService) {
        this.tradeOrderService = tradeOrderService;
    }

    @Override
    public ApiPayMethodParamsCheckResultDTO<ApiPayOrderQueryDTO> checkParams(String content, MemberDTO memberDTO) {
        ApiPayMethodParamsCheckResultDTO<ApiPayOrderQueryDTO> checkResultDTO = new ApiPayMethodParamsCheckResultDTO<>();
        JSONObject jsonObject = JSONObject.parseObject(content);
        ApiPayOrderQueryDTO data = jsonObject.toJavaObject(ApiPayOrderQueryDTO.class);
        if (StringUtils.isAllBlank(data.getMemberOrderNumber(), data.getSysOrderNumber())) {
            checkResultDTO.setPass(false);
            checkResultDTO.setMsg("[memberOrderNumber]和[sysOrderNumber]不能都为空");
        }
        checkResultDTO.setPass(true);
        checkResultDTO.setData(data);
        return checkResultDTO;
    }


    @Override
    public ApiPayMethodResultDTO realOperate(ApiPayOrderQueryDTO content, MemberDTO memberDTO) {
        ApiPayMethodResultDTO<ApiPayOrderQueryResultDTO> apiPayMethodResultDTO = new ApiPayMethodResultDTO<>();
        TradeOrderDO tradeOrderDO = tradeOrderService.findOneOrder(content.getSysOrderNumber(), memberDTO.getMemberNumber(), content.getMemberOrderNumber());

        if (tradeOrderDO != null) {
            ApiPayOrderQueryResultDTO orderQueryResultDTO = new ApiPayOrderQueryResultDTO(tradeOrderDO.getMemberOrderNumber(),
                    tradeOrderDO.getSysOrderNumber(), tradeOrderDO.getTradeAmount().toString(), tradeOrderDO.getCurrency(),
                    DateUtils.time2Str(tradeOrderDO.getGmtCreate()), tradeOrderDO.getTradeStatus(), tradeOrderDO.getDefrayalChannel(),
                    tradeOrderDO.getTradeType());
            apiPayMethodResultDTO.setResult(ApiPayMethodResultEnum.SUCCESS);
            apiPayMethodResultDTO.setData(orderQueryResultDTO);
        } else {
            logger.info("订单查询失败，系统订单号：{},会员订单号：{}", content.getSysOrderNumber(), content.getMemberOrderNumber());
            apiPayMethodResultDTO.setResult(ApiPayMethodResultEnum.FAIL);
            apiPayMethodResultDTO.setSubCode(ApiPayOrderQueryErrorEnum.ORDER_NOT_EXIST.getCode());
            apiPayMethodResultDTO.setSubMsg(ApiPayOrderQueryErrorEnum.ORDER_NOT_EXIST.getMsg());
        }
        return apiPayMethodResultDTO;
    }
}
