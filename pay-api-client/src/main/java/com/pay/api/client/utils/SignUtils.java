package com.pay.api.client.utils;

/**
 * 统一签名帮助类，包含多种算法
 *
 * @author chenwei
 * @date 2019-01-19
 */
public final class SignUtils {


    /**
     * RSA算法签名
     *
     * @param content 待签名内容
     * @param priKey  签名方私钥
     * @return 返回非null字符串
     */
    public static String signRsa(String content, String priKey) {

        //todo rsa签名算法
        return null;
    }


    /**
     * RSA2算法签名
     *
     * @param content 待签名内容
     * @param priKey  签名方私钥
     * @return 返回非null字符串
     */
    public static String signRsa2(String content, String priKey) {
        //todo rsa2签名算法
        return null;
    }

    /**
     * RSA算法校验签名
     *
     * @param content 待验签内容
     * @param pubKey  签名方公钥
     * @param sign    签名
     * @return 当且仅当验签成功返回 true
     */
    public static boolean verifyRsa(String content, String pubKey, String sign) {
        //todo
        return false;
    }


    /**
     * RSA2算法校验签名
     *
     * @param content 待验签内容
     * @param pubKey  签名方公钥
     * @param sign    签名
     * @return 当且仅当验签成功返回 true
     */
    public static boolean verifyRsa2(String content, String pubKey, String sign) {
        //todo
        return false;
    }
}
