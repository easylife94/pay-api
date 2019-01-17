package com.pay.api.web.api;

import com.pay.api.client.constants.ApiPayGatewayResultEnum;
import com.pay.api.client.dto.api.ApiPayDTO;
import com.pay.api.client.dto.api.ApiPayMethodResultDTO;
import com.pay.api.client.dto.api.ApiPayResultDTO;
import com.pay.api.core.method.IPayApiMethod;
import com.pay.api.core.service.IPayApiGatewayService;
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

    @Autowired
    private IPayApiGatewayService payApiGatewayService;

    /**
     * 验证签名、路由方法和返回参数签名
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/gateway", method = RequestMethod.POST)
    public ApiPayResultDTO gateway(@RequestBody ApiPayDTO apiPayDTO) {
        ApiPayResultDTO apiPayResultDTO = new ApiPayResultDTO();
        Boolean verifySign = payApiGatewayService.verifySign(apiPayDTO.getContent(), apiPayDTO.getSignType(), apiPayDTO.getSign());
        ApiPayGatewayResultEnum gatewayResultEnum;

        logger.info("支付接口网关，请求参数:{}", apiPayDTO);

        //1.验证签名
        if (Boolean.TRUE.equals(verifySign)) {

            //2.1路由方法
            IPayApiMethod apiMethod = payApiGatewayService.route(apiPayDTO.getMethod());
            if (apiMethod != null) {

                //2.2 执行方法
                ApiPayMethodResultDTO resultDTO = apiMethod.operate(apiPayDTO.getContent());
                apiPayResultDTO.setSubCode(resultDTO.getSubCode());
                apiPayResultDTO.setSubMsg(resultDTO.getSubMsg());
                apiPayResultDTO.setContent(resultDTO.getData());

                //3.参数签名
                payApiGatewayService.sign(apiPayDTO, apiPayResultDTO);

                //4.是否要加密
                if (Boolean.TRUE.equals(apiPayDTO.getEncrypt())) {
                    String encryptContent = payApiGatewayService.encrypt(apiPayDTO.getContent());
                    apiPayDTO.setContent(encryptContent);
                }
                gatewayResultEnum = ApiPayGatewayResultEnum.SUCCESS;
            } else {
                gatewayResultEnum = ApiPayGatewayResultEnum.METHOD_NOT_EXIST;
            }
        } else {
            gatewayResultEnum = ApiPayGatewayResultEnum.SIGN_ERROR;
        }

        apiPayResultDTO.setCode(gatewayResultEnum.getCode());
        apiPayResultDTO.setMsg(gatewayResultEnum.getMsg());

        logger.info("支付接口网关，返回参数:{}", apiPayResultDTO);

        return apiPayResultDTO;
    }
}
