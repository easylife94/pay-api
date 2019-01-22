package com.pay.api.client.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
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
     * RSA算法签名，使用SHA256WithRSA
     *
     * @param content 待签名内容
     * @param priKey  签名方私钥
     * @return 返回非null字符串
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeySpecException
     */
    public static String signRsa(String content, String priKey)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, InvalidKeySpecException {
        PrivateKey privateKey = RsaUtils.getPrivateKey(RsaUtils.SIGN_TYPE_RSA, priKey);
        return RsaUtils.sign(content, privateKey, RsaUtils.SIGN_SHA256RSA_ALGORITHMS);
    }


    /**
     * RSA2算法签名，使用SHA256WithRSA
     *
     * @param content 待签名内容
     * @param priKey  签名方私钥
     * @return 返回非null字符串
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeySpecException
     */
    public static String signRsa2(String content, String priKey)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, InvalidKeySpecException {
        PrivateKey privateKey = RsaUtils.getPrivateKey(RsaUtils.SIGN_TYPE_RSA2, priKey);
        return RsaUtils.sign(content, privateKey, RsaUtils.SIGN_SHA256RSA_ALGORITHMS);
    }

    /**
     * RSA算法校验签名
     *
     * @param content 待验签内容
     * @param pubKey  签名方公钥
     * @param sign    签名
     * @return 当且仅当验签成功返回 true
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verifyRsa(String content, String pubKey, String sign)
            throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, SignatureException {
        PublicKey publicKey = RsaUtils.getPublicKey(RsaUtils.SIGN_TYPE_RSA, pubKey);
        return RsaUtils.verify(content, sign, publicKey, RsaUtils.SIGN_SHA256RSA_ALGORITHMS);
    }


    /**
     * RSA2算法校验签名
     *
     * @param content 待验签内容
     * @param pubKey  签名方公钥
     * @param sign    签名
     * @return 当且仅当验签成功返回 true
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verifyRsa2(String content, String pubKey, String sign)
            throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, SignatureException {
        PublicKey publicKey = RsaUtils.getPublicKey(RsaUtils.SIGN_TYPE_RSA2, pubKey);
        return RsaUtils.verify(content, sign, publicKey, RsaUtils.SIGN_SHA256RSA_ALGORITHMS);
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
            JSONObject json = (JSONObject) JSONObject.toJSON(content);
            SortedSet<String> sortedKeys = new TreeSet<>(json.keySet());
            for (String key : sortedKeys) {
                str.append(json.getString(key));
                str.append(SPLIT);
            }
        }
        return str.toString();
    }
}
