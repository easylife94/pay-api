package com.pay.api.client.stereotype;

import com.pay.center.client.constants.PlatformMappedEnum;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * 交易处理器适配器组件注解
 *
 * @author chenwei
 * @date 2019-02-04
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface TradeHandleAdapterComponent {

    PlatformMappedEnum value() default PlatformMappedEnum.TEST;

}
