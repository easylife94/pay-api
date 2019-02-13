package com.pay.api.core;

import com.pay.center.client.constants.PlatformEnum;

import java.lang.annotation.*;

/**
 * @author chenwei
 * @date 2019/2/11 16:52
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PlatformComponent {

    /**
     * 平台枚举类
     *
     * @return
     */
    PlatformEnum value();
}
