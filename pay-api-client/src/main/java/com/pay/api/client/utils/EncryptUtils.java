package com.pay.api.client.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 * 加解密帮助类
 *
 * @author chenwei
 * @date 2019/3/4 11:56
 */
public class EncryptUtils {

    /**
     * RSA算法加密
     *
     * @param content
     * @param pubKey
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     * @throws IOException
     */
    public static String encryptRsa(String content, String pubKey) throws InvalidKeySpecException, NoSuchAlgorithmException,
            IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException {
        PublicKey publicKey = RsaUtils.getPublicKey(RsaUtils.ALGORITHM_RSA, pubKey);
        return RsaUtils.encrypt(content, publicKey);
    }

    /**
     * RSA算法解密
     *
     * @param content
     * @param priKey
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     * @throws IOException
     */
    public static String decryptRsa(String content, String priKey) throws InvalidKeySpecException, NoSuchAlgorithmException,
            IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException {
        PrivateKey privateKey = RsaUtils.getPrivateKey(RsaUtils.ALGORITHM_RSA, priKey);
        content = content.replaceAll(" ", "+");
        return RsaUtils.decrypt(content, privateKey);
    }

    public static void main(String[] args) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException,
            IOException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        String priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN9F4HdZxeWtQetF\n" +
                "PLgxESWdaPXqmfg33vTa7DTipzyvFB4rkyDQRwZPw06XDG+rkKzXEaNqCDgNiPXK\n" +
                "0QO7nf/Eno580jPi5XjIMk4nOjGQTxZJ+IG1M+C2o+LP76NRSohDrYdL4FkU85O2\n" +
                "t5z8bJw0mcqQ2jQAdZeAhT3zIG5vAgMBAAECgYEAy9SJJaxpRFK12UluM2FoHATm\n" +
                "a4rvYXHwM20hMu6wanATV6/EM7KxBIwQ61BuZAwmmgQF8D++nR2OKYYs5tGDXOW3\n" +
                "wFyHXXAdSMM+6BnPddzzb+vecK+P3pRbMjjXrXwqwrEYELjgro/uBdMDONsen2o+\n" +
                "OMkUqKggWBx3fHSN2KECQQD2J63PrknRaFVuOKQql/OHAy1xte917ZgsopZs99gF\n" +
                "eL8GLZPI2aDwjM5Yg04Uz4kRqEE3UgUVzwTtMTfzpR6XAkEA6DPqC7G0e6R7ak31\n" +
                "fvEgo6cPqAcphP2jMEVwnoVEQhsG2Myt5FHmaRChpOtw5Nw6NxtRm9ATnVQi6ain\n" +
                "aS8B6QJADkD/9J3AEos7HzXSc9D2viO19va1FhwbCsKjeU3kyXRTg3USMLhBdIyC\n" +
                "ymdYFyZpZodat2xddQTW4TFPbyFpowJBAMO9r5h4DxsDhv4QBdkiz07lr68HipqP\n" +
                "bZdtkggvc2D+g4ES2avU1pTO7lSmHJ7wfyqhHuRoPYCswlmSUL28YLECQAzBroIB\n" +
                "TgjN2+r1aeYDlXZY2FVERoMRB2yWsI3DttT3tOmttmAJ2PGuU2ietaTEpHCqS6pr\n" +
                "2GBVxIYCp0xMNHk=";
        String encrypt = "jggOeq+GsMK9oe925KtyHXFbsimpR3G981wTE7dt6TxMpzHEEKtQjzHYiLB/JgZaAwkBkS+BJNK++M/onB1J3KMCmzpF2rkrwgsNoX0nqRMxe8PCcCfPADViS2X+8W51OPlDH3ttHL93PEh7g4p5WJBhmFL0WaIG8CMnmypcXGkE/e/ffTR6YETW2WEu2HTqUU8grk3RxovGtBYMqnI3R6fyjlDg1EfOzICQsYufjaanh/cbqWJrDGIVFF23REiPeWK9K3hPCVCJNkMODWnZKKPDoQb2sxXCpzasldyoFtdMNJx/v0XRmxGjB4LPdFamlqWd7mXPoV0dGpdQ10y3p27TbIA6WofZ1sOQcN1Xjdi0MnE2uVHiD3TwKrWwXR+/6UUcLQ/7HD07+aNPkUT5sfHmL+Z1OVrtPqTi8gp6YL/2n3r8JU+dDggYXq94uoQzfvp6an2vdavsW6GroCfn1l6flxM6JGiDpCUsfXUzOQjqs2kcU69PWAozSjATPZpR";
        String s = decryptRsa(encrypt, priKey);
        System.out.println(s);
    }
}
