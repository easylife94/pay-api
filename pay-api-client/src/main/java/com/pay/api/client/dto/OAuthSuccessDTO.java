package com.pay.api.client.dto;

import lombok.Data;

/**
 * oauth授权成功后，向重定向地址传递参数
 *
 * @author chenwei
 * @date 2019/3/7 16:45
 */
@Data
public class OAuthSuccessDTO {

    /**
     * 通道编号，用来查询交易通道配置
     */
    private String channelNumber;

    /**
     * 业务地址。授权成功后实际执行业务地址。
     * 地址不能带参数（例如：http://xxx.com?key1=value1&key2=value2）。
     * 需要传递的参数放入attach
     */
    private String businessUrl;

    /**
     * 业务数据，会按照attach名称传递给业务地址。
     */
    private String businessData;

    public OAuthSuccessDTO(String channelNumber, String businessUrl, String businessData) {
        this.channelNumber = channelNumber;
        this.businessUrl = businessUrl;
        this.businessData = businessData;
    }
}
