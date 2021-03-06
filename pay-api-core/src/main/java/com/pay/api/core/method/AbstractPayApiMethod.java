package com.pay.api.core.method;

import com.pay.api.client.constants.ApiPayMethodErrorEnum;
import com.pay.api.client.constants.ApiPayMethodResultEnum;
import com.pay.api.client.dto.ApiPayMethodParamsCheckResultDTO;
import com.pay.api.client.dto.ApiPayMethodResultDTO;
import com.pay.api.client.dto.TradeMemberDTO;
import com.pay.center.client.dto.service.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象支付接口方法类
 *
 * @author chenwei
 * @date 2019/2/1 13:34
 */
@Slf4j
public abstract class AbstractPayApiMethod<T> implements IPayApiMethod {

    /**
     * 定义参数校验方法
     *
     * @param content   请求参数json字符串
     * @param memberDTO 会员参数
     * @return 返回ApiPayMethodParamsCheckResultDTO对象。对象的data字段为转化后请求参数
     */
    public abstract ApiPayMethodParamsCheckResultDTO<T> checkParams(String content, TradeMemberDTO memberDTO);

    /**
     * 定义方法实际执行
     *
     * @param content   请求参数对象
     * @param memberDTO 会员参数
     * @return 返回方法执行结果
     */
    public abstract ApiPayMethodResultDTO realOperate(T content, TradeMemberDTO memberDTO);

    /**
     * 先执行参数校验,在实际执行方法
     *
     * @param content   请求参数内容
     * @param memberDTO 会员
     * @return 返回方法执行结果
     */
    @Override
    public final ApiPayMethodResultDTO operate(String content, TradeMemberDTO memberDTO) {
        try {
            ApiPayMethodParamsCheckResultDTO<T> paramsCheckResultDTO = checkParams(content, memberDTO);
            if (!Boolean.TRUE.equals(paramsCheckResultDTO.getPass())) {
                ApiPayMethodResultDTO apiPayMethodResultDTO = new ApiPayMethodResultDTO();
                apiPayMethodResultDTO.setSubCode(ApiPayMethodErrorEnum.CHECK_FAIL.getCode());
                apiPayMethodResultDTO.setSubMsg(ApiPayMethodErrorEnum.CHECK_FAIL.getMsg() + ":" +
                        paramsCheckResultDTO.getMsg());
                apiPayMethodResultDTO.setResult(ApiPayMethodResultEnum.FAIL);
                return apiPayMethodResultDTO;
            }
            return realOperate(paramsCheckResultDTO.getData(), memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("方法执行异常，ERROR：{}", e.getMessage());
            ApiPayMethodResultDTO apiPayMethodResultDTO = new ApiPayMethodResultDTO();
            apiPayMethodResultDTO.setResult(ApiPayMethodResultEnum.FAIL);
            apiPayMethodResultDTO.setSubMsg(ApiPayMethodErrorEnum.OPERATE_EXCEPTION.getCode());
            apiPayMethodResultDTO.setSubCode(ApiPayMethodErrorEnum.OPERATE_EXCEPTION.getMsg());
            return apiPayMethodResultDTO;
        }
    }
}
