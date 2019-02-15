package com.pay.api.core.service.impl;

import com.pay.api.client.constants.*;
import com.pay.api.client.dto.ApiPayDTO;
import com.pay.api.client.dto.ApiPayParamsCheckResultDTO;
import com.pay.api.client.dto.ApiPayResultDTO;
import com.pay.api.client.utils.SignUtils;
import com.pay.api.core.method.IPayApiMethod;
import com.pay.api.core.service.IPayApiGatewayService;
import com.pay.api.core.utils.SpringContextUtil;
import com.pay.center.client.dto.service.MemberDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 支付网关服务
 *
 * @author chenwei
 * @date 2019/1/14 11:32
 */
@Service
public class PayApiGatewayServiceImpl implements IPayApiGatewayService {

    private static final Logger logger = LoggerFactory.getLogger(PayApiGatewayServiceImpl.class);

    @Override
    public ApiPayParamsCheckResultDTO publicParamsCheck(ApiPayDTO apiPayDTO) {
        ApiPayParamsCheckResultDTO apiPayParamsCheckDTO = new ApiPayParamsCheckResultDTO();

        //1.是否为空
        if (StringUtils.isBlank(apiPayDTO.getMember())) {
            return paramsError(apiPayParamsCheckDTO, "member", ApiPayGatewayPublicParamsErrorEnum.PARAM_REQUIRED);
        }
        if (StringUtils.isBlank(apiPayDTO.getMethod())) {
            return paramsError(apiPayParamsCheckDTO, "method", ApiPayGatewayPublicParamsErrorEnum.PARAM_REQUIRED);
        }
        if (StringUtils.isBlank(apiPayDTO.getSignType())) {
            return paramsError(apiPayParamsCheckDTO, "signType", ApiPayGatewayPublicParamsErrorEnum.PARAM_REQUIRED);
        }
        if (StringUtils.isBlank(apiPayDTO.getSign())) {
            return paramsError(apiPayParamsCheckDTO, "sign", ApiPayGatewayPublicParamsErrorEnum.PARAM_REQUIRED);
        }
        if (StringUtils.isBlank(apiPayDTO.getVersion())) {
            return paramsError(apiPayParamsCheckDTO, "version", ApiPayGatewayPublicParamsErrorEnum.PARAM_REQUIRED);
        }
        if (StringUtils.isBlank(apiPayDTO.getTimestamp())) {
            return paramsError(apiPayParamsCheckDTO, "timestamp", ApiPayGatewayPublicParamsErrorEnum.PARAM_REQUIRED);
        }
        if (StringUtils.isBlank(apiPayDTO.getFormat())) {
            return paramsError(apiPayParamsCheckDTO, "format", ApiPayGatewayPublicParamsErrorEnum.PARAM_REQUIRED);
        }
        if (StringUtils.isBlank(apiPayDTO.getContent())) {
            return paramsError(apiPayParamsCheckDTO, "content", ApiPayGatewayPublicParamsErrorEnum.PARAM_REQUIRED);
        }

        //2.参数值校验
        //2.1 signType
        String signType = apiPayDTO.getSignType().toUpperCase();
        ApiPayGatewaySignTypeEnum signTypeEnum = ApiPayGatewaySignTypeEnum.getByType(signType);
        if (signTypeEnum == null) {
            return paramsError(apiPayParamsCheckDTO, "sign", ApiPayGatewayPublicParamsErrorEnum.SIGN_TYPE_ERROR);
        }
        //2.2 format
        String format = apiPayDTO.getFormat().toUpperCase();
        ApiPayGatewayFormatEnum formatEnum = ApiPayGatewayFormatEnum.getByType(format);
        if (formatEnum == null) {
            return paramsError(apiPayParamsCheckDTO, "format", ApiPayGatewayPublicParamsErrorEnum.FORMAT_ERROR);
        }
        //2.3 version
        String version = apiPayDTO.getVersion();
        ApiPayGatewayVersionEnum versionEnum = ApiPayGatewayVersionEnum.get(version);
        if (versionEnum != null) {
            return paramsError(apiPayParamsCheckDTO, "version", ApiPayGatewayPublicParamsErrorEnum.VERSION_ERROR);
        }

        //2.4 timestamp
        if(!Boolean.TRUE.equals(checkTimestampFormat(apiPayDTO.getTimestamp()))){
            return paramsError(apiPayParamsCheckDTO, "timestamp", ApiPayGatewayPublicParamsErrorEnum.TIMESTAMP_ERROR);
        }

        apiPayParamsCheckDTO.setPass(true);
        return apiPayParamsCheckDTO;
    }

    /**
     * 参数异常时，设置pass为false。并设置提示信息和类型。
     *
     * @param apiPayParamsCheckDTO 参数校验返回结果
     * @param param                参数名称
     * @return apiPayParamsCheckDTO参数原值返回
     */
    private ApiPayParamsCheckResultDTO paramsError(ApiPayParamsCheckResultDTO apiPayParamsCheckDTO, String param, ApiPayGatewayPublicParamsErrorEnum e) {
        apiPayParamsCheckDTO.setPass(false);
        apiPayParamsCheckDTO.setMsg("参数：[" + param + "]错误，错误信息：" + e.getError());
        apiPayParamsCheckDTO.setType(e.getType());
        return apiPayParamsCheckDTO;
    }

    /**
     * 简单校验日期字符串格式：yyyy-MM-dd HH:mm:ss
     *
     * @param timestamp
     * @return
     */
    private Boolean checkTimestampFormat(String timestamp) {
        String eL = "^\\d{4}-\\d{2}-\\d{2}\\s[0-2]\\d:[0-5]\\d:[0-5]\\d$";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(timestamp);
        return m.matches();
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        PayApiGatewayServiceImpl payApiGatewayService = new PayApiGatewayServiceImpl();
        for(int i = 0 ; i < 1000000;i++){
            System.out.println(payApiGatewayService.checkTimestampFormat("1199-01-01 23:59:12"));
        }
        System.out.println(System.currentTimeMillis() - l);
    }

    @Override
    public Boolean verifySign(ApiPayDTO apiPayDTO, MemberDTO memberDTO) {
        String signType = apiPayDTO.getSignType().toUpperCase();
        ApiPayGatewaySignTypeEnum signTypeEnum = ApiPayGatewaySignTypeEnum.getByType(signType);
        if (signType != null) {
            try {
                switch (signTypeEnum) {
                    case RSA:
                        return SignUtils.verifyRsa(apiPayDTO.getContent(), memberDTO.getMemberPubKey(), apiPayDTO.getSign());
                    case RSA2:
                        return SignUtils.verifyRsa2(apiPayDTO.getContent(), memberDTO.getMemberPubKey(), apiPayDTO.getSign());
                    default:
                        logger.error("暂不支持签名算法类型:{}", signType);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("验签异常，异常信息：{}", e.getMessage());
            }
        } else {
            logger.error("签名枚举类型不存在:{}", signType);
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean sign(ApiPayDTO apiPayDTO, ApiPayResultDTO apiPayResultDTO, MemberDTO memberDTO) {
        String contentStr = SignUtils.str(apiPayResultDTO.getContent());
        String signType = apiPayDTO.getSignType().toUpperCase();
        ApiPayGatewaySignTypeEnum signTypeEnum = ApiPayGatewaySignTypeEnum.getByType(signType);
        if (signType != null) {
            try {
                switch (signTypeEnum) {
                    case RSA:
                        apiPayResultDTO.setSign(SignUtils.signRsa(contentStr, memberDTO.getSysPriKey()));
                        return Boolean.TRUE;
                    case RSA2:
                        apiPayResultDTO.setSign(SignUtils.signRsa2(contentStr, memberDTO.getSysPriKey()));
                        return Boolean.TRUE;
                    default:
                        logger.error("暂不支持签名算法类型:{}", signType);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("签名异常，异常信息：{}", e.getMessage());
            }
        } else {
            logger.error("签名枚举类型不存在:{}", signType);
        }
        return Boolean.FALSE;
    }

    @Override
    public String encrypt(String content, MemberDTO memberDTO) {

        return null;
    }

    @Override
    public IPayApiMethod route(String method) {
        StringBuilder methodBeanId = new StringBuilder();
        methodBeanId.append(PayApiBeanPrefix.METHOD);
        methodBeanId.append(method);

        Object methodBean = SpringContextUtil.getBean(methodBeanId.toString());
        if (methodBean instanceof IPayApiMethod) {
            return (IPayApiMethod) methodBean;
        } else {
            logger.error("路由方法失败，找不到方法Bean id:{}", methodBeanId);
        }
        return null;
    }
}
