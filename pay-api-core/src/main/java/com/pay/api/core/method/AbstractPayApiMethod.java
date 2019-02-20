package com.pay.api.core.method;

import com.pay.api.client.constants.ApiPayMethodResultEnum;
import com.pay.api.client.dto.ApiPayMethodParamsCheckResultDTO;
import com.pay.api.client.dto.ApiPayMethodResultDTO;
import com.pay.center.client.dto.service.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象支付接口方法类
 *
 * @author chenwei
 * @date 2019/2/1 13:34
 */
public abstract class AbstractPayApiMethod<T> implements IPayApiMethod {

    private static final Logger logger = LoggerFactory.getLogger(AbstractPayApiMethod.class);

    /**
     * 定义参数校验方法
     *
     * @param content
     * @return
     */
    public abstract ApiPayMethodParamsCheckResultDTO<T> checkParams(String content);

    /**
     * 定义方法实际执行
     *
     * @param content
     * @param memberDTO
     * @return
     */
    public abstract ApiPayMethodResultDTO realOperate(T content, MemberDTO memberDTO);

    /**
     * 先执行参数校验,在实际执行方法
     *
     * @param content   请求参数内容
     * @param memberDTO 会员
     * @return 返回方法执行结果
     */
    @Override
    public final ApiPayMethodResultDTO operate(String content, MemberDTO memberDTO) {
        ApiPayMethodParamsCheckResultDTO<T> paramsCheckResultDTO = checkParams(content);
        if (!Boolean.TRUE.equals(paramsCheckResultDTO.getPass())) {
            ApiPayMethodResultDTO apiPayMethodResultDTO = new ApiPayMethodResultDTO();
            apiPayMethodResultDTO.setSubCode();
            apiPayMethodResultDTO.setSubMsg(paramsCheckResultDTO.getMsg());
            apiPayMethodResultDTO.setResult(ApiPayMethodResultEnum.FAIL);
            return apiPayMethodResultDTO;
        }
        return realOperate(paramsCheckResultDTO.getData(), memberDTO);
    }
}
