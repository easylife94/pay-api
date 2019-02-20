package com.pay.api.web.api;

import com.pay.api.client.constants.ApiPayGatewayResultEnum;
import com.pay.api.client.dto.ApiPayDTO;
import com.pay.api.client.dto.ApiPayMethodResultDTO;
import com.pay.api.client.dto.ApiPayParamsCheckResultDTO;
import com.pay.api.client.dto.ApiPayResultDTO;
import com.pay.api.client.exception.PayApiException;
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
     * 网关接口
     *
     * @param apiPayDTO 网关请求参数
     * @return 统一返回结果
     */
    @RequestMapping(value = "/gateway", method = RequestMethod.POST)
    public ApiPayResultDTO gateway(@RequestBody ApiPayDTO apiPayDTO) {
        logger.info("支付接口网关，请求参数:{}", apiPayDTO);
        ApiPayResultDTO apiPayResultDTO = new ApiPayResultDTO();
        try {

            //1.公共参数校验
            ApiPayParamsCheckResultDTO paramsCheckResultDTO = payApiGatewayService.publicParamsCheck(apiPayDTO);
            if (Boolean.FALSE.equals(paramsCheckResultDTO.getPass())) {
                apiPayResultDTO.setSubCode(paramsCheckResultDTO.getType());
                apiPayResultDTO.setSubMsg(paramsCheckResultDTO.getMsg());
                return gatewayError(apiPayDTO, apiPayResultDTO, ApiPayGatewayResultEnum.PUBLIC_PARAMS_ERROR);
            }

            //2.获取会员
            MemberDTO memberDTO = payCenterFeignServiceClient.getMember(apiPayDTO.getMember());
            if (memberDTO == null) {
                return gatewayError(apiPayDTO, apiPayResultDTO, ApiPayGatewayResultEnum.MEMBER_NOT_EXIST);
            }

            //3.验证签名
            Boolean verifySign = payApiGatewayService.verifySign(apiPayDTO, memberDTO);
            if (!Boolean.TRUE.equals(verifySign)) {
                return gatewayError(apiPayDTO, apiPayResultDTO, ApiPayGatewayResultEnum.VERIFY_SIGN_ERROR);
            }

            //4.1路由方法
            IPayApiMethod apiMethod = payApiGatewayService.route(apiPayDTO.getMethod());
            if (apiMethod == null) {
                return gatewayError(apiPayDTO, apiPayResultDTO, ApiPayGatewayResultEnum.METHOD_NOT_EXIST);
            }

            //4.2 执行方法
            ApiPayMethodResultDTO resultDTO = apiMethod.operate(apiPayDTO.getContent(), memberDTO);
            switch (resultDTO.getResult()) {
                case SUCCESS:
                    apiPayResultDTO.setContent(resultDTO.getData());
                    break;
                case FAIL:
                    apiPayResultDTO.setCode(ApiPayGatewayResultEnum.METHOD_FAIL.getCode());
                    apiPayResultDTO.setMsg(ApiPayGatewayResultEnum.METHOD_FAIL.getMsg());
                    apiPayResultDTO.setSubCode(resultDTO.getSubCode());
                    apiPayResultDTO.setSubMsg(resultDTO.getSubMsg());
                    break;
                default:
                    throw new PayApiException("不支持方法执行返回结果类型：" + resultDTO.getResult());
            }

            //5.参数签名
            if (!Boolean.TRUE.equals(payApiGatewayService.sign(apiPayDTO, apiPayResultDTO, memberDTO))) {
                return gatewayError(apiPayDTO, apiPayResultDTO, ApiPayGatewayResultEnum.SIGN_ERROR);
            }

            //6.是否要加密
            if (Boolean.TRUE.equals(apiPayDTO.getEncrypt())) {
                String encryptContent = payApiGatewayService.encrypt(apiPayDTO.getContent(), memberDTO);
                apiPayDTO.setContent(encryptContent);
            }

            apiPayResultDTO.setCode(ApiPayGatewayResultEnum.SUCCESS.getCode());
            apiPayResultDTO.setMsg(ApiPayGatewayResultEnum.SUCCESS.getMsg());

            logger.info("支付接口网关，返回成功，请求参数:{}，返回参数:{}", apiPayDTO, apiPayResultDTO);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付网关异常，异常：{}。请求参数：{}，", e.getMessage());
            apiPayResultDTO.setCode(ApiPayGatewayResultEnum.SYSTEM_ERROR.getCode());
            apiPayResultDTO.setMsg(ApiPayGatewayResultEnum.SYSTEM_ERROR.getMsg());
        }
        return apiPayResultDTO;
    }

    /**
     * 网关返回失败
     *
     * @param apiPayDTO       接口请求参数
     * @param apiPayResultDTO 接口返回参数
     * @param e               返回结果类型枚举
     * @return 参数apiPayResultDTO原值返回
     */
    private ApiPayResultDTO gatewayError(ApiPayDTO apiPayDTO, ApiPayResultDTO apiPayResultDTO, ApiPayGatewayResultEnum e) {
        apiPayResultDTO.setCode(e.getCode());
        apiPayResultDTO.setMsg(e.getMsg());
        logger.info("支付接口网关，返回失败。请求参数：{}，返回参数:{}", apiPayDTO, apiPayResultDTO);
        return apiPayResultDTO;
    }
}
