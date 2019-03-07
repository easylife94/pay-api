package com.pay.api.client.dto;

import java.util.StringJoiner;

/**
 * 支付宝授权参数
 *
 * @author chenwei
 * @date 2019/3/7 16:45
 */
public class AliPayAuthDTO {

    /**
     * 通道编号，用来查询交易通道配置
     */
    private String channelNumber;

    /**
     * 重定向地址，获取openId后重定向地址
     * 地址不能带参数（例如：http://xxx.com?key1=value1&key2=value2）。
     * 需要传递的参数放入attach
     */
    private String redirectUrl;

    /**
     * 附带数据，会按照attach名称传递给重定向地址
     */
    private String attach;

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                AliPayAuthDTO.class.getSimpleName() + "{", "}")
                .add("channelNumber=" + channelNumber)
                .add("redirectUrl=" + redirectUrl)
                .add("attach=" + attach)
                .toString();
    }
}
