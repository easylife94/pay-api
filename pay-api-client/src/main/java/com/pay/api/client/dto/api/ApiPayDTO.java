package com.pay.api.client.dto.api;

/**
 * 支付接口请求参数
 *
 * @author chenwei
 * @date 2019/1/14 11:04
 */
public class ApiPayDTO {

    /**
     * 会员编号
     */
    private String member;

    /**
     * 接口名称
     */
    private String method;

    /**
     * 签名算法类型：RSA,RSA2
     * 实例值：RSA2
     */
    private String signType;

    /**
     * 签名串
     */
    private String sign;

    /**
     * 接口版本：固定值为1.0
     */
    private String version;

    /**
     * 时间戳，格式：yyyy-MM-dd HH:mm:ss
     * 实例值：2014-07-24 03:07:50
     */
    private String timestamp;

    /**
     * content编码格式：uft-8,gbk
     * 实例值：uft-8
     */
    private String charset;

    /**
     * 请求参数content和返回结果content支持格式：目前仅支持json
     * 实例值：JSON
     */
    private String format;
    /**
     * content是否加密
     * 实例值：true
     */
    private Boolean encrypt;

    /**
     * 请求参数内容
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

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
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
}
