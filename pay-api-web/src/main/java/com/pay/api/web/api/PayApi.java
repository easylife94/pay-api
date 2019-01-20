package com.pay.api.web.api;

import com.pay.api.client.constants.ApiPayGatewayParamsErrorEnum;
import com.pay.api.client.constants.ApiPayGatewayResultEnum;
import com.pay.api.client.dto.api.ApiPayDTO;
import com.pay.api.client.dto.api.ApiPayMethodResultDTO;
import com.pay.api.client.dto.api.ApiPayResultDTO;
import com.pay.api.core.method.IPayApiMethod;
import com.pay.api.core.service.IPayApiGatewayService;
import com.pay.center.client.dto.service.MemberDTO;
import com.pay.center.client.service.client.IPayCenterFeignServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付接口
 *
 * @author chenwei
 * @date 2019/1/14 10:57
 */
@RestController
@RequestMapping("/api/pay")
public class PayApi {

    private static final Logger logger = LoggerFactory.getLogger(PayApi.class);

    private final IPayApiGatewayService payApiGatewayService;
    private final IPayCenterFeignServiceClient payCenterFeignServiceClient;

    @Autowired
    public PayApi(IPayApiGatewayService payApiGatewayService, IPayCenterFeignServiceClient payCenterFeignServiceClient) {
        this.payApiGatewayService = payApiGatewayService;
        this.payCenterFeignServiceClient = payCenterFeignServiceClient;
    }

    /**
     * 验证签名、路由方法和返回参数签名
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/gateway", method = RequestMethod.POST)
    public ApiPayResultDTO gateway(@RequestBody ApiPayDTO apiPayDTO) {
        logger.info("支付接口网关，请求参数:{}", apiPayDTO);
        ApiPayResultDTO apiPayResultDTO = new ApiPayResultDTO();

        //1.公共参数校验
        ApiPayGatewayParamsErrorEnum errorEnum = payApiGatewayService.publicParamsCheck(apiPayDTO);
        if(errorEnum != null){
            apiPayResultDTO.setSubCode(errorEnum.getType());
            apiPayResultDTO.setSubMsg(errorEnum.getError());
            return gatewayError(apiPayResultDTO, ApiPayGatewayResultEnum.PUBLIC_PARAMS_ERROR);
        }


        //2.获取会员
        MemberDTO memberDTO = payCenterFeignServiceClient.getMember(apiPayDTO.getMember());
        if (memberDTO == null) {
            return gatewayError(apiPayResultDTO, ApiPayGatewayResultEnum.MEMBER_NOT_EXIST);
        }

        //3.验证签名
        Boolean verifySign = payApiGatewayService.verifySign(apiPayDTO, memberDTO);
        if (!Boolean.TRUE.equals(verifySign)) {
            return gatewayError(apiPayResultDTO, ApiPayGatewayResultEnum.SIGN_ERROR);
        }

        //4.1路由方法
        IPayApiMethod apiMethod = payApiGatewayService.route(apiPayDTO.getMethod());
        if (apiMethod == null) {
            return gatewayError(apiPayResultDTO, ApiPayGatewayResultEnum.METHOD_NOT_EXIST);
        }

        //4.2 执行方法
        ApiPayMethodResultDTO resultDTO = apiMethod.operate(apiPayDTO.getContent(), memberDTO);
        apiPayResultDTO.setSubCode(resultDTO.getSubCode());
        apiPayResultDTO.setSubMsg(resultDTO.getSubMsg());
        apiPayResultDTO.setContent(resultDTO.getData());

        //5.参数签名
        payApiGatewayService.sign(apiPayDTO, apiPayResultDTO, memberDTO);

        //6.是否要加密
        if (Boolean.TRUE.equals(apiPayDTO.getEncrypt())) {
            String encryptContent = payApiGatewayService.encrypt(apiPayDTO.getContent(), memberDTO);
            apiPayDTO.setContent(encryptContent);
        }

        apiPayResultDTO.setCode(ApiPayGatewayResultEnum.SUCCESS.getCode());
        apiPayResultDTO.setMsg(ApiPayGatewayResultEnum.SUCCESS.getMsg());

        logger.info("支付接口网关，返回参数:{}", apiPayResultDTO);

        return apiPayResultDTO;
    }

    /**
     * 网关返回失败
     *
     * @param apiPayResultDTO 接口返回参数
     * @param e               返回结果类型枚举
     * @return 参数apiPayResultDTO原值返回
     */
    private ApiPayResultDTO gatewayError(ApiPayResultDTO apiPayResultDTO, ApiPayGatewayResultEnum e) {
        apiPayResultDTO.setCode(e.getCode());
        apiPayResultDTO.setMsg(e.getMsg());
        logger.info("支付接口网关，返回失败参数:{}", apiPayResultDTO);
        return apiPayResultDTO;
    }
}
