package com.pay.api.web.rest;

import com.pay.api.client.dto.rest.*;
import com.pay.api.client.model.TradeChannelConfigDO;
import com.pay.api.client.model.TradeMemberDO;
import com.pay.api.client.service.IPayApiFeignService;
import com.pay.api.client.utils.RsaUtils;
import com.pay.api.core.dao.TradeChannelConfigDao;
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

    private final IIdService idService;
    private final TradeMemberDao tradeMemberDao;
    private final TradeChannelConfigDao tradeChannelConfigDao;

    @Autowired
    public PayApiFeignServiceImpl(IIdService idService, TradeMemberDao tradeMemberDao, TradeChannelConfigDao tradeChannelConfigDao) {
        this.idService = idService;
        this.tradeMemberDao = tradeMemberDao;
        this.tradeChannelConfigDao = tradeChannelConfigDao;
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
            tradeMemberDO.setId(idService.generateId());
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
            resultFeignDTO.feignFail("TRADE_MEMBER_EXISTED", "创建交易会员失败，交易会员已存在");
            logger.error("创建交易会员失败，交易会员已存在。memberNumber:{}", tradeMemberCreateFeignDTO.getMemberNumber());
        }
        return resultFeignDTO;
    }

    @Override
    public TradeMemberUpdateResultFeignDTO tradeMemberUpdate(TradeMemberUpdateFeignDTO tradeMemberUpdateFeignDTO) {
        TradeMemberUpdateResultFeignDTO resultFeignDTO = new TradeMemberUpdateResultFeignDTO();
        TradeMemberDO existTradeMemberDO = tradeMemberDao.selectByMemberNumber(tradeMemberUpdateFeignDTO.getMemberNumber());
        if (existTradeMemberDO != null) {
            existTradeMemberDO.setMemberPubKey(tradeMemberUpdateFeignDTO.getMemberPubKey());
            tradeMemberDao.updateByPrimaryKeySelective(existTradeMemberDO);

            resultFeignDTO.setMemberNumber(existTradeMemberDO.getMemberNumber());
        } else {
            resultFeignDTO.feignFail("TRADE_MEMBER_NOT_EXISTED", "更新交易会员失败，交易会员不存在");
            logger.error("更新交易会员失败，交易会员不存在。memberNumber:{}", tradeMemberUpdateFeignDTO.getMemberNumber());
        }
        return resultFeignDTO;
    }

    @Override
    public TradeChannelConfigCreateResultFeignDTO tradeChannelCreate(TradeChannelConfigCreateFeignDTO tradeChannelConfigCreateFeignDTO) {

        TradeChannelConfigCreateResultFeignDTO resultFeignDTO = new TradeChannelConfigCreateResultFeignDTO();

        TradeChannelConfigDO tradeChannelConfigDO = new TradeChannelConfigDO();
        tradeChannelConfigDO.setId(idService.generateId());
        tradeChannelConfigDO.setGmtCreate(new Date());
        tradeChannelConfigDO.setIsDeleted(false);

        //通道信息
        tradeChannelConfigDO.setChannelId(tradeChannelConfigCreateFeignDTO.getChannelId());
        tradeChannelConfigDO.setChannelName(tradeChannelConfigCreateFeignDTO.getChannelName());
        tradeChannelConfigDO.setChannelNumber(tradeChannelConfigCreateFeignDTO.getChannelNumber());

        //基础配置
        tradeChannelConfigDO.setTradeNotifyUrl(tradeChannelConfigCreateFeignDTO.getTradeNotifyUrl());
        tradeChannelConfigDO.setTradeReturnUrl(tradeChannelConfigCreateFeignDTO.getTradeReturnUrl());
        tradeChannelConfigDO.setRegisterNotifyUrl(tradeChannelConfigCreateFeignDTO.getRegisterNotifyUrl());

        //平台配置
        tradeChannelConfigDO.setPlatformChannelId(tradeChannelConfigCreateFeignDTO.getPlatformChannelId());
        tradeChannelConfigDO.setPlatformPubKey(tradeChannelConfigCreateFeignDTO.getPlatformPubKey());
        tradeChannelConfigDO.setChannelSecretKey(tradeChannelConfigCreateFeignDTO.getChannelSecretKey());
        tradeChannelConfigDO.setChannelPubKey(tradeChannelConfigCreateFeignDTO.getChannelPubKey());
        tradeChannelConfigDO.setChannelPriKey(tradeChannelConfigCreateFeignDTO.getChannelPriKey());
        tradeChannelConfigDO.setCertUrl(tradeChannelConfigCreateFeignDTO.getCertUrl());

        //支付宝配置
        tradeChannelConfigDO.setAlipayPid(tradeChannelConfigCreateFeignDTO.getAlipayPid());
        tradeChannelConfigDO.setAlipayAppId(tradeChannelConfigCreateFeignDTO.getAlipayAppId());
        tradeChannelConfigDO.setAlipayPubKey(tradeChannelConfigCreateFeignDTO.getAlipayPubKey());
        tradeChannelConfigDO.setAlipayAppPriKey(tradeChannelConfigCreateFeignDTO.getAlipayAppPriKey());
        tradeChannelConfigDO.setAlipayAppPubKey(tradeChannelConfigCreateFeignDTO.getAlipayAppPubKey());
        tradeChannelConfigDO.setAlipayAuthRedirectUrl(tradeChannelConfigCreateFeignDTO.getAlipayAuthRedirectUrl());

        //微信配置
        tradeChannelConfigDO.setWechatAppId(tradeChannelConfigCreateFeignDTO.getWechatAppId());
        tradeChannelConfigDO.setWechatAppSecretKey(tradeChannelConfigCreateFeignDTO.getWechatAppSecretKey());
        tradeChannelConfigDO.setWechatAuthRedirectUrl(tradeChannelConfigCreateFeignDTO.getWechatAuthRedirectUrl());

        //拓展配置
        tradeChannelConfigDO.setExt1(tradeChannelConfigCreateFeignDTO.getExt1());
        tradeChannelConfigDO.setExt2(tradeChannelConfigCreateFeignDTO.getExt2());
        tradeChannelConfigDO.setExt3(tradeChannelConfigCreateFeignDTO.getExt3());
        tradeChannelConfigDO.setExt4(tradeChannelConfigCreateFeignDTO.getExt4());

        int insert = tradeChannelConfigDao.insert(tradeChannelConfigDO);
        if (insert <= 0) {
            resultFeignDTO.feignFail("FAIL", "新增交易通道配置记录失败");
        }
        return resultFeignDTO;

    }

    @Override
    public TradeChannelConfigUpdateResultFeignDTO tradeChannelUpdate(TradeChannelConfigUpdateFeignDTO tradeChannelConfigUpdateFeignDTO) {
        //todo 更新交易通道配置
        return null;
    }
}
