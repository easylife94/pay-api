package com.pay.api.client.dto.rest;

import com.pay.common.client.dto.rest.BaseResultFeignDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 交易会员创建结果DTO
 *
 * @author chenwei
 * @date 2019/3/2 16:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TradeMemberCreateResultFeignDTO extends BaseResultFeignDTO {

    private static final long serialVersionUID = 8128506897639352393L;

    /**
     * 会员编号
     */
    private String memberNumber;

    /**
     * 系统生成公钥
     */
    private String sysPubKey;

    /**
     * 系统生成私钥
     */
    private String sysPriKey;
}
