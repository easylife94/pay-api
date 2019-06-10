package com.pay.api.client.dto.rest;

import com.pay.common.client.dto.rest.BaseResultFeignDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 交易通道配置创建结果DTO
 * @author chenwei
 * @date 2019/3/7 16:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TradeChannelConfigCreateResultFeignDTO extends BaseResultFeignDTO {
    private static final long serialVersionUID = -3161529522494589910L;
}
