package com.pay.api.core.service;

import com.pay.api.client.dto.OAuthSuccessDTO;
import com.pay.api.client.dto.WechatConfigDTO;

import java.io.UnsupportedEncodingException;

/**
 * 微信服务接口
 *
 * @author chenwei
 * @date 2019/3/8 12:43
 */
public interface IWechatService {

    /**
     * 构建微信获取授权请求地址
     *
     * @param oAuthSuccessDTO 系统内部传递参数
     * @param wechatConfigDTO  微信配置参数
     * @return 微信获取授权请求地址
     */
    String buildAuthUrl(OAuthSuccessDTO oAuthSuccessDTO, WechatConfigDTO wechatConfigDTO) throws UnsupportedEncodingException;
}
