package com.pay.api.client.exception;

/**
 * @author chenwei
 * @date 2019/2/14 14:40
 */
public class PayApiException extends RuntimeException{

    private static final long serialVersionUID = -1438263038774193562L;

    public PayApiException(String message) {
        super(message);
    }
}
