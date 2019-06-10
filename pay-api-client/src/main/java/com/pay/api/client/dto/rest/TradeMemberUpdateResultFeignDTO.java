package com.pay.api.client.dto.rest;

import com.pay.common.client.dto.rest.BaseResultFeignDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 交易会员更新结果DTO
 *
 * @author chenwei
 * @date 2019/3/2 17:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TradeMemberUpdateResultFeignDTO extends BaseResultFeignDTO {
    private static final long serialVersionUID = 7134278332743603747L;

    /**
     * 会员编号
     */
    private String memberNumber;
}
