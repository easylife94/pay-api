package com.pay.api.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.dto.OauthSuccessDTO;
import com.pay.api.client.dto.TradeChannelConfigDTO;
import com.pay.api.core.service.IAliPayService;
import com.pay.api.core.service.ITradeChannelConfigService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 对支付宝提供接口
 *
 * @author chenwei
 * @date 2019/3/7 16:36
 */
@Controller
@RequestMapping("/alipay/support")
public class AliPaySupportController {

    private static final Logger logger = LoggerFactory.getLogger(AliPaySupportController.class);

    private final ITradeChannelConfigService tradeChannelConfigService;
    private final IAliPayService aliService;

    @Autowired
    public AliPaySupportController(ITradeChannelConfigService tradeChannelConfigService, IAliPayService aliService) {
        this.tradeChannelConfigService = tradeChannelConfigService;
        this.aliService = aliService;
    }

    /**
     * 支付宝授权回调，获取openId并重定向到指定地址
     *
     * @param appAuthCode 应用授权码
     * @param authCode    支付宝授权码
     * @param state       附带参数
     * @return 重定向
     */
    @RequestMapping("/auth")
    public String auth(@RequestParam(value = "app_auth_code", required = false) String appAuthCode, @RequestParam(value = "auth_code", required = false) String authCode, String state) {
        String appAuthToken = null;
        String userId = null;
        OauthSuccessDTO oAuthSuccessDTO = JSONObject.parseObject(state).toJavaObject(OauthSuccessDTO.class);
        //参数校验
        if (StringUtils.isBlank(appAuthCode) && StringUtils.isBlank(authCode)) {
            logger.error("支付宝授权回调异常，app_auth_code和auth_code不能都为空");
        } else if (StringUtils.isBlank(oAuthSuccessDTO.getChannelNumber())) {
            logger.error("支付宝授权回调异常，channelNumber不能为空");
        } else {
            try {
                if (StringUtils.isBlank(appAuthCode) && StringUtils.isBlank(authCode)) {
                    logger.error("支付宝授权回调异常，app_auth_code和auth_code都为空");
                } else {
                    TradeChannelConfigDTO channelConfig = tradeChannelConfigService.getChannelConfig(oAuthSuccessDTO.getChannelNumber());
                    if (channelConfig == null) {
                        logger.error("支付宝授权回调异常，channelNumber:{}交易通道配置不存在", oAuthSuccessDTO.getChannelNumber());
                    } else {
                        //appAuthCode和authCode处理方式不同
                        if (StringUtils.isNotBlank(appAuthCode)) {
                            appAuthToken = aliService.getAppAuthToken(appAuthCode, channelConfig.getAlipayAppId(), channelConfig.getAlipayAppPriKey(), channelConfig.getAlipayPubKey());
                        }

                        if (StringUtils.isNotBlank(authCode)) {
                            userId = aliService.getUserId(authCode, channelConfig.getAlipayAppId(), channelConfig.getAlipayAppPriKey(), channelConfig.getAlipayPubKey());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("支付宝授权回调异常，app_auth_code:{},auth_code,state:{},ERROR:{}", appAuthCode, authCode, state, e.getMessage());
            }
        }
        return buildRedirectUrl(oAuthSuccessDTO.getBusinessUrl(), oAuthSuccessDTO.getBusinessData(), appAuthToken, userId);
    }

    /**
     * 构建重定向地址，附带参数
     *
     * @param businessUrl  业务地址
     * @param businessData 业务数据
     * @param appAuthToken 应用授权token
     * @param userId       支付宝userId
     * @return 返回重定向地址
     */
    private String buildRedirectUrl(String businessUrl, String businessData, String appAuthToken, String userId) {
        String querySplitStr = "?";
        //去除重定向地址中的参数
        if (businessUrl.contains(querySplitStr)) {
            businessUrl.substring(businessUrl.indexOf("?"));
        }

        StringBuilder sb = new StringBuilder("redirect:");
        sb.append(businessUrl);
        sb.append("?");
        if (StringUtils.isNotBlank(businessData)) {
            sb.append("businessData").append("=").append(businessData);
        }
        if (StringUtils.isNotBlank(appAuthToken)) {
            sb.append("appAuthToken").append("=").append(appAuthToken);
        }
        if (StringUtils.isNotBlank(appAuthToken)) {
            sb.append("userId").append("=").append(userId);
        }
        return sb.toString();
    }

    @RequestMapping("/auth/redirectTest")
    @ResponseBody
    public String redirectTest(String attach, String userId, String appAuthToken) {
        System.out.println(attach);
        return "ok";
    }
}
