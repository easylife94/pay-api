package com.pay.api.client.utils;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * Rsa帮助类
 *
 * @author chenwei
 * @date 2019/1/22 13:40
 */
public class RsaUtils {

    public static final String SIGN_TYPE_RSA = "RSA";
    public static final String SIGN_TYPE_RSA2 = "RSA2";
    private static final String SIGN_SHA1RSA_ALGORITHMS = "SHA1WithRSA";
    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * 获取私钥对象
     *
     * @param algorithm  算法
     * @param privateKey 私钥字符串
     * @return 返回私钥
     */
    public static PrivateKey getPrivateKey(String algorithm, String privateKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(algorithm);
        return factory.generatePrivate(pkcs8EncodedKeySpec);
    }
}
