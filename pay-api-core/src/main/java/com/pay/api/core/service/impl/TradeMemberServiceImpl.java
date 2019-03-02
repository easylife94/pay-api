package com.pay.api.core.service.impl;

import com.pay.api.client.dto.TradeMemberDTO;
import com.pay.api.client.model.TradeMemberDO;
import com.pay.api.core.dao.TradeMemberDao;
import com.pay.api.core.service.ITradeMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenwei
 * @date 2019-03-02
 */
@Service
public class TradeMemberServiceImpl implements ITradeMemberService {

    private static final Logger logger = LoggerFactory.getLogger(TradeMemberServiceImpl.class);

    private final TradeMemberDao tradeMemberDao;

    @Autowired
    public TradeMemberServiceImpl(TradeMemberDao tradeMemberDao) {
        this.tradeMemberDao = tradeMemberDao;
    }

    @Override
    public TradeMemberDTO getMember(String memberNumber) {

        TradeMemberDO tradeMemberDO = tradeMemberDao.selectByMemberNumber(memberNumber);
        if(tradeMemberDO != null){
            return new TradeMemberDTO(tradeMemberDO.getMemberId(),tradeMemberDO.getMemberNumber(),
                    tradeMemberDO.getMemberName(),tradeMemberDO.getMemberPubKey(),tradeMemberDO.getSysPubKey(),
                    tradeMemberDO.getSysPriKey(),tradeMemberDO.getAgentId(),tradeMemberDO.getAgentNumber(),tradeMemberDO.getAgentName(),
                    tradeMemberDO.getAgentLevel());

        }
        return null;
    }
}
