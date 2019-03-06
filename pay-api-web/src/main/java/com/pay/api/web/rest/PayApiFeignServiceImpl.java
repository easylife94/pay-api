package com.pay.api.web.rest;

import com.pay.api.client.dto.rest.TradeMemberCreateFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberCreateResultFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberUpdateFeignDTO;
import com.pay.api.client.dto.rest.TradeMemberUpdateResultFeignDTO;
import com.pay.api.client.model.TradeMemberDO;
import com.pay.api.client.service.IPayApiFeignService;
import com.pay.api.client.utils.RsaUtils;
import com.pay.api.core.dao.TradeMemberDao;
import com.pay.api.core.service.IIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.util.Date;

/**
 * RESTfull服务实现
 *
 * @author chenwei
 * @date 2019/1/14 14:15
 */
@RestController
public class PayApiFeignServiceImpl implements IPayApiFeignService {

    private static Logger logger = LoggerFactory.getLogger(PayApiFeignServiceImpl.class);

    @Value("${security.rsa.key-size}")
    private Integer keySize;

    private final IIdService iIdService;
    private final TradeMemberDao tradeMemberDao;

    @Autowired
    public PayApiFeignServiceImpl(IIdService iIdService, TradeMemberDao tradeMemberDao) {
        this.iIdService = iIdService;
        this.tradeMemberDao = tradeMemberDao;
    }


    @Override
    public String test() {
        logger.info("test service is available");
        return "service is available";
    }

    @Override
    public TradeMemberCreateResultFeignDTO tradeMemberCreate(TradeMemberCreateFeignDTO tradeMemberCreateFeignDTO) {
        TradeMemberCreateResultFeignDTO resultFeignDTO = new TradeMemberCreateResultFeignDTO();
        //判断member number是否已经创建了交易会员
        TradeMemberDO existTradeMemberDO = tradeMemberDao.selectByMemberNumber(tradeMemberCreateFeignDTO.getMemberNumber());
        if (existTradeMemberDO == null) {
            //生成rsa公钥私钥
            //创建do，保存系统公钥私钥和会员公钥
            KeyPair keyPair = RsaUtils.generateKeyPair(keySize);
            TradeMemberDO tradeMemberDO = new TradeMemberDO();
            tradeMemberDO.setId(iIdService.generateId());
            tradeMemberDO.setAgentId(tradeMemberCreateFeignDTO.getAgentId());
            tradeMemberDO.setAgentLevel(tradeMemberCreateFeignDTO.getAgentLevel());
            tradeMemberDO.setAgentName(tradeMemberCreateFeignDTO.getAgentName());
            tradeMemberDO.setAgentNumber(tradeMemberCreateFeignDTO.getAgentNumber());
            tradeMemberDO.setGmtCreate(new Date());
            tradeMemberDO.setIsDeleted(false);
            tradeMemberDO.setMemberId(tradeMemberCreateFeignDTO.getMemberId());
            tradeMemberDO.setMemberName(tradeMemberCreateFeignDTO.getMemberName());
            tradeMemberDO.setMemberNumber(tradeMemberCreateFeignDTO.getMemberNumber());
            tradeMemberDO.setMemberPubKey(tradeMemberCreateFeignDTO.getMemberPubKey());
            tradeMemberDO.setSysPriKey(RsaUtils.getPrivateKey(keyPair));
            tradeMemberDO.setSysPubKey(RsaUtils.getPublicKey(keyPair));
            tradeMemberDao.insert(tradeMemberDO);

            resultFeignDTO.setMemberNumber(tradeMemberDO.getMemberNumber());
            resultFeignDTO.setSysPriKey(tradeMemberDO.getSysPriKey());
            resultFeignDTO.setSysPubKey(tradeMemberDO.getSysPubKey());
        } else {
            resultFeignDTO.feignFail("TRADE_MEMBER_EXISTED","创建交易会员失败，交易会员已存在");
            logger.error("创建交易会员失败，交易会员已存在。memberNumber:{}", tradeMemberCreateFeignDTO.getMemberNumber());
        }
        return resultFeignDTO;
    }

    @Override
    public TradeMemberUpdateResultFeignDTO tradeMemberUpdate(TradeMemberUpdateFeignDTO tradeMemberUpdateFeignDTO) {
        TradeMemberUpdateResultFeignDTO resultFeignDTO = new TradeMemberUpdateResultFeignDTO();
        //todo 更新交易会员
        //判断是否需要调用dao更新
        return resultFeignDTO;
    }
}
