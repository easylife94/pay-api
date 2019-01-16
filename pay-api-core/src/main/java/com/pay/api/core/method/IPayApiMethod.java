package com.pay.api.core.method;

import com.pay.api.client.dto.api.ApiPayMethodResultDTO;

/**
 * 支付方法接口，实现类使用Component注解，实例名称前缀<code>PAY.</code>
 * 通过spring context实现工厂模式访问具体支付方法
 *
 * @author chenwei
 * @date 2019/1/16 11:43
 */
public interface IPayApiMethod {

    /**
     * 执行
     *
     * @param content
     * @return
     */
    ApiPayMethodResultDTO operate(String content);

}
