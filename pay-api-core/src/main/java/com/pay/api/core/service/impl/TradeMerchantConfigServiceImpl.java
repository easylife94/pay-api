package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeMerchantConfigDTO;
import com.pay.api.client.model.TradeMerchantConfigDO;
import com.pay.api.core.dao.TradeMerchantConfigDao;
import com.pay.api.core.service.ITradeMerchantConfigService;
import com.pay.center.client.dto.service.TradeMerchantDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019/3/7 15:51
 */
@Service
public class TradeMerchantConfigServiceImpl implements ITradeMerchantConfigService {

    private final TradeMerchantConfigDao tradeMerchantConfigDao;

    @Autowired
    public TradeMerchantConfigServiceImpl(TradeMerchantConfigDao tradeMerchantConfigDao) {
        this.tradeMerchantConfigDao = tradeMerchantConfigDao;
    }

    @Override
    public TradeMerchantConfigDTO getMerchantConfig(String merchantNumber) {
        TradeMerchantConfigDTO tradeMerchantConfigDTO = null;
        TradeMerchantConfigDO tradeMerchantConfigDO = tradeMerchantConfigDao.selectOneByMerchantNumber(merchantNumber);
        if(tradeMerchantConfigDO != null){
            BeanUtils.copyProperties(tradeMerchantConfigDO,tradeMerchantConfigDTO);
        }
        return tradeMerchantConfigDTO;
    }
}
