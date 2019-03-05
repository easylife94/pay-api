package com.pay.api.client.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
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
     * RSA算法
     */
    public static final String ALGORITHM_RSA = "RSA";

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
        byte[] keyBytes = Base64.getDecoder().decode(privateKey.replaceAll("\r|\n", ""));
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
        byte[] keyBytes = Base64.getDecoder().decode(publicKey.replaceAll("\r|\n", ""));
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

    /**
     * 加密，加密后二进制数组转为base64字符串
     *
     * @param data   待加密数据
     * @param pubKey 公钥
     * @return 返回加密后数据
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    public static String encrypt(String data, PublicKey pubKey) throws NoSuchAlgorithmException, IOException,
            NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        byte[] bytes = data.getBytes(CHARSET);
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        int inputLen = bytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for (int i = 0; inputLen - offSet > 0; offSet = i * 117) {
            byte[] cache;
            if (inputLen - offSet > 117) {
                cache = cipher.doFinal(bytes, offSet, 117);
            } else {
                cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * 解密，base64字符串转为二进制数组后进行解密
     *
     * @param data   待解密数据
     * @param priKey 私钥
     * @return 返回解密后数据
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    public static String decrypt(String data, PrivateKey priKey) throws NoSuchAlgorithmException, IOException,
            NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        byte[] bytes = Base64.getDecoder().decode(data.getBytes(CHARSET));
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        int inputLen = bytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for (int i = 0; inputLen - offSet > 0; offSet = i * 128) {
            byte[] cache;
            if (inputLen - offSet > 128) {
                cache = cipher.doFinal(bytes, offSet, 128);
            } else {
                cache = cipher.doFinal(bytes, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData, CHARSET);
    }


    /**
     * 生成秘钥对
     *
     * @param keySize
     * @return
     */
    public static KeyPair generateKeyPair(int keySize) {
        KeyPairGenerator keyPairGenerator = null;

        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        } catch (NoSuchAlgorithmException var3) {
            var3.printStackTrace();
        }

        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    /**
     * 获取私钥
     *
     * @param keyPair
     * @return
     */
    public static String getPrivateKey(KeyPair keyPair) {
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    /**
     * 获取公钥
     *
     * @param keyPair
     * @return
     */
    public static String getPublicKey(KeyPair keyPair) {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

}
