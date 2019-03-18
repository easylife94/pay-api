package com.pay.api.client.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 支付接口请求参数
 *
 * @author chenwei
 * @date 2019/1/14 11:04
 */
@Data
public class ApiPayDTO implements Serializable {

    private static final long serialVersionUID = -8049979341516231218L;
    /**
     * 会员编号
     * 必填
     */
    private String member;

    /**
     * 接口名称
     * 必填
     */
    private String method;

    /**
     * 签名算法类型：RSA,RSA2
     * 实例值：RSA2
     * 必填
     */
    private String signType;

    /**
     * 签名串
     * 必填
     */
    private String sign;

    /**
     * 接口版本：固定值为1.0
     * 必填
     */
    private String version;

    /**
     * 时间戳，格式：yyyy-MM-dd HH:mm:ss
     * 实例值：2014-07-24 03:07:50
     * 必填
     */
    private String timestamp;

    /**
     * 请求参数content和返回结果content支持格式：目前仅支持json
     * 实例值：JSON
     * 必填
     */
    private String format;
    /**
     * 返回结果content是否加密，默认请求不加密
     * 实例值：true
     * 非必填
     */
    private Boolean encrypt;

    /**
     * 请求参数内容
     * 必填
     */
    private String content;

}
