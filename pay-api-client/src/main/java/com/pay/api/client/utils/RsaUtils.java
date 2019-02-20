package com.pay.api.client.utils;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Rsa帮助类
 *
 * @author chenwei
 * @date 2019/1/22 13:40
 */
public class RsaUtils {

    /**
     * RSA加密算法
     */
    public static final String SIGN_TYPE_RSA = "RSA";

    /**
     * SHA1WithRSA签名算法
     */
    public static final String SIGN_SHA1RSA_ALGORITHMS = "SHA1WithRSA";

    /**
     * SHA256WithRSA签名算法
     */
    public static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    /**
     * 字符串内容编码
     */
    public static final String CHARSET = "UTF-8";

    /**
     * 获取私钥对象，PCKS8
     *
     * @param algorithm  算法
     * @param privateKey 私钥字符串
     * @return 返回私钥
     * @throws NoSuchAlgorithmException 算法不存在异常
     * @throws InvalidKeySpecException  非法密钥异常
     */
    public static PrivateKey getPrivateKey(String algorithm, String privateKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(algorithm);
        return factory.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * 获取公钥对象
     *
     * @param algorithm 算法
     * @param publicKey 公钥字符串
     * @return
     * @throws InvalidKeySpecException  算法不存在异常
     * @throws NoSuchAlgorithmException 非法密钥异常
     */
    public static PublicKey getPublicKey(String algorithm, String publicKey)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey.replaceAll("\r\n", ""));
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(algorithm);
        return factory.generatePublic(x509EncodedKeySpec);
    }

    /**
     * 签名
     *
     * @param data          待签名数据
     * @param privateKey    私钥
     * @param signAlgorithm 签名算法
     * @return 返回签名字符串
     * @throws UnsupportedEncodingException 不支持编码格式异常
     * @throws NoSuchAlgorithmException     算法不存在异常
     * @throws InvalidKeyException          非法密钥异常
     * @throws SignatureException           创建签名对象异常
     */
    public static String sign(String data, PrivateKey privateKey, String signAlgorithm)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        byte[] bytes = data.getBytes(CHARSET);
        Signature signature = Signature.getInstance(signAlgorithm);
        signature.initSign(privateKey);
        signature.update(bytes);
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 验签
     *
     * @param data          待验签数据
     * @param sign          签名字符串
     * @param publicKey     公钥对象
     * @param signAlgorithm 签名算法
     * @return 当且仅当验签成功返回ture，否者返回false
     * @throws UnsupportedEncodingException 不支持编码格式异常
     * @throws NoSuchAlgorithmException     算法不存在异常
     * @throws InvalidKeyException          非法密钥异常
     * @throws SignatureException           创建签名对象异常
     */
    public static boolean verify(String data, String sign, PublicKey publicKey, String signAlgorithm)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        byte[] bytes = data.getBytes(CHARSET);
        Signature signature = Signature.getInstance(signAlgorithm);
        signature.initVerify(publicKey);
        signature.update(bytes);
        return signature.verify(Base64.getDecoder().decode(sign));
    }


}
