package com.pay.api.client.model;

import lombok.Data;

import java.util.Date;

/**
 * @author chenwei
 */
@Data
public class TradeMerchantConfigDO {
    private Long id;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Boolean isDeleted;

    private Long merchantId;

    private String merchantNumber;

    private String channelMerchantId;

    private String merchantSecretKey;

    private String merhcantAppId;

    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;
}