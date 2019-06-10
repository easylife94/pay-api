package com.pay.api.client.dto.rest;

import com.pay.common.client.dto.rest.BaseResultFeignDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 交易通道配置更新结果DTO
 *
 * @author chenwei
 * @date 2019/3/7 16:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TradeChannelConfigUpdateResultFeignDTO extends BaseResultFeignDTO {
    private static final long serialVersionUID = -4045523144363602954L;
}
