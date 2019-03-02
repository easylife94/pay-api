package com.pay.api.core.method;

import com.pay.api.client.dto.ApiPayMethodResultDTO;
import com.pay.api.client.dto.TradeMemberDTO;

/**
 * 支付方法接口，实现类使用Component注解，实例名称前缀<code>PAY.</code>
 * 通过spring context实现工厂模式访问具体支付方法
 *
 * @author chenwei
 * @date 2019/1/16 11:43
 */
public interface IPayApiMethod<T> {

    /**
     * 执行
     *
     * @param content   请求参数内容
     * @param memberDTO 会员
     * @return
     */
    ApiPayMethodResultDTO<T> operate(String content, TradeMemberDTO memberDTO);

}
