package com.pay.api.client.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 统一签名帮助类，包含多种算法
 *
 * @author chenwei
 * @date 2019-01-19
 */
public final class SignUtils {

    private static final String SPLIT = "&";

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
        //todo rsa签名校验
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
        //todo rsa2签名校验
        return false;
    }

    /**
     * 将对象转为字符串
     * 如果是String类型直接返回
     *
     * @param content 待转化对象
     * @return
     */
    public static String str(Object content) {
        StringBuilder str = new StringBuilder();
        if (content instanceof String) {
            str.append(content.toString());
        } else {
            JSONObject json = (JSONObject)JSONObject.toJSON(content);
            SortedSet<String> sortedKeys = new TreeSet<>(json.keySet());
            for (String key : sortedKeys) {
                str.append(json.getString(key));
                str.append(SPLIT);
            }
        }
        return str.toString();
    }
}
