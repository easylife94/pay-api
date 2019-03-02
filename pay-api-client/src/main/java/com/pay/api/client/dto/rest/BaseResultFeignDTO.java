package com.pay.api.client.dto.rest;

import java.io.Serializable;

/**
 * feign 返回结果基类
 *
 * @author chenwei
 * @date 2019/3/2 16:50
 */
public abstract class BaseResultFeignDTO implements Serializable {

    private static final long serialVersionUID = -9114582532269279841L;

    /**
     * 默认返回结果是成功
     */
    public BaseResultFeignDTO() {
        this.success = true;
    }

    /**
     * 服务调用是否成功
     */
    private Boolean success;

    /**
     * 返回编码，success为false时生效
     */
    private String returnCode;

    /**
     * 返回信息，success为false时生效
     */
    private String returnMsg;

    /**
     * 服务调用失败
     *
     * @param returnCode 返回编码
     * @param returnMsg  返回信息
     */
    public void feignFail(String returnCode, String returnMsg) {
        this.success = false;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }


}
