package com.pay.api.client.dto;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 支付接口请求参数
 *
 * @author chenwei
 * @date 2019/1/14 11:04
 */
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

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Boolean encrypt) {
        this.encrypt = encrypt;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApiPayDTO.class.getSimpleName() + "[", "]")
                .add("member='" + member + "'")
                .add("method='" + method + "'")
                .add("signType='" + signType + "'")
                .add("sign='" + sign + "'")
                .add("version='" + version + "'")
                .add("timestamp='" + timestamp + "'")
                .add("format='" + format + "'")
                .add("encrypt=" + encrypt)
                .add("content='" + content + "'")
                .toString();
    }


}
