package com.pay.api.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pay.api.client.dto.OAuthSuccessDTO;
import com.pay.api.client.dto.WechatConfigDTO;
import com.pay.api.core.service.IWechatService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author chenwei
 * @date 2019/3/8 12:43
 */
@Service
public class WechatServiceImpl implements IWechatService {

    @Override
    public String buildAuthUrl(OAuthSuccessDTO oAuthSuccessDTO, WechatConfigDTO wechatConfigDTO) throws UnsupportedEncodingException {
        StringBuilder wechatAuthUrl = new StringBuilder();
        wechatAuthUrl.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
        wechatAuthUrl.append(wechatConfigDTO.getAppId());
        wechatAuthUrl.append("&redirect_uri=");
        wechatAuthUrl.append(URLEncoder.encode(wechatConfigDTO.getAuthRedirectUrl(), "UTF-8"));
        wechatAuthUrl.append("&response_type=code&scope=snsapi_base&state=");
        wechatAuthUrl.append(Base64.getEncoder().encodeToString(JSONObject.toJSONString(oAuthSuccessDTO).getBytes(StandardCharsets.UTF_8)));
        wechatAuthUrl.append("&connect_redirect=1");
        wechatAuthUrl.append("#wechat_redirect");
        return wechatAuthUrl.toString();
    }
}
