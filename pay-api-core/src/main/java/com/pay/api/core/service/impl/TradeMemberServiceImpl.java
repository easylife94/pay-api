package com.pay.api.core.service.impl;

import com.pay.api.client.constants.FeeTypeEnum;
import com.pay.api.client.dto.TradeMemberDTO;
import com.pay.api.client.model.TradeMemberDO;
import com.pay.api.client.model.TradeMemberServiceFeeDO;
import com.pay.api.client.utils.FeeUtils;
import com.pay.api.core.dao.TradeMemberDao;
import com.pay.api.core.dao.TradeMemberServiceFeeDao;
import com.pay.api.core.service.ITradeMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenwei
 * @date 2019-03-02
 */
@Service
public class TradeMemberServiceImpl implements ITradeMemberService {

    private static final Logger logger = LoggerFactory.getLogger(TradeMemberServiceImpl.class);

    private final TradeMemberDao tradeMemberDao;
    private final TradeMemberServiceFeeDao tradeMemberServiceFeeDao;

    @Autowired
    public TradeMemberServiceImpl(TradeMemberDao tradeMemberDao, TradeMemberServiceFeeDao tradeMemberServiceFeeDao) {
        this.tradeMemberDao = tradeMemberDao;
        this.tradeMemberServiceFeeDao = tradeMemberServiceFeeDao;
    }

    @Override
    public TradeMemberDTO getMember(String memberNumber) {

        TradeMemberDO tradeMemberDO = tradeMemberDao.selectByMemberNumber(memberNumber);

        List<TradeMemberServiceFeeDO> tradeMemberServiceFeeDOS = tradeMemberServiceFeeDao.selectByMemberId(tradeMemberDO.getMemberId());
        List<FeeUtils.Fee> serviceFees = new ArrayList<>();
        for (TradeMemberServiceFeeDO memberServiceFeeDO : tradeMemberServiceFeeDOS) {
            serviceFees.add(new FeeUtils.Fee(FeeTypeEnum.valueOf(memberServiceFeeDO.getServiceFeeType()), memberServiceFeeDO.getServiceFee(),
                    memberServiceFeeDO.getTradeAmountMin(), memberServiceFeeDO.getTradeAmountMax()));
        }

        if (tradeMemberDO != null) {
            return new TradeMemberDTO(tradeMemberDO.getMemberId(), tradeMemberDO.getMemberNumber(),
                    tradeMemberDO.getMemberName(), tradeMemberDO.getMemberPubKey(), tradeMemberDO.getSysPubKey(),
                    tradeMemberDO.getSysPriKey(), tradeMemberDO.getAgentId(), tradeMemberDO.getAgentNumber(), tradeMemberDO.getAgentName(),
                    tradeMemberDO.getAgentLevel(), serviceFees);

        }
        return null;
    }
}
