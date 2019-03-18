package com.pay.api.client.dto;

import lombok.Data;

/**
 * 支付接口参数校验结果DTO
 *
 * @author chenwei
 * @date 2019/1/21 11:53
 */
@Data
public class ApiPayParamsCheckResultDTO {

    /**
     * 校验通过，当且仅当校验通过时为true
     */
    private Boolean pass;

    /**
     * 错误类型
     */
    private String type;

    /**
     * 错误信息
     */
    private String msg;
}
