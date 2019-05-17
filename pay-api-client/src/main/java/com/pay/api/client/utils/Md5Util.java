package com.pay.api.client.utils;

import java.security.MessageDigest;

/**
 * Md5Util
 * @author chenwei
 */
public class Md5Util {

    /**
     * md5LowerCase
     *
     * @param s
     * @return
     */
    public static String md5(String s) {

        /**
         * hexDigits
         */
        char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * MagicNumberConstants.N2];
            int k = MagicNumberConstants.N0;

            for (int i = MagicNumberConstants.N0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> MagicNumberConstants.N4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * md5LowerCase
     *
     * @param s
     * @return
     */
    public final static String md5LowerCase(String s) {
    	return Md5Util.md5(s).toLowerCase();
    }

    /**
     * password
     *
     * @param s
     * @return
     */
    public final static String password(String s) {
    	return Md5Util.md5LowerCase(Md5Util.md5LowerCase(s));
    }

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Md5Util.md5(Md5Util.md5("123456").toLowerCase()));
        System.out.println(Md5Util.md5("加密"));
    }

    private static class MagicNumberConstants {

        /**
         * The Constant 0.
         */
        public static final Integer N0 = 0;
        /**
         * The Constant 1.
         */
        public static final Integer N1 = 1;
        /**
         * The Constant 2.
         */
        public static final Integer N2 = 2;
        /**
         * The Constant 3.
         */
        public static final Integer N3 = 3;
        /**
         * The Constant 4.
         */
        public static final Integer N4 = 4;
        /**
         * The Constant 5.
         */
        public static final Integer N5 = 5;
        /**
         * The Constant 6.
         */
        public static final Integer N6 = 6;
        /**
         * The Constant 10.
         */
        public static final Integer N10 = 10;
        /**
         * The Constant 14.
         */
        public static final Integer N14 = 14;
        /**
         * The Constant 16.
         */
        public static final Integer N16 = 16;
        /**
         * The Constant 20.
         */
        public static final Integer N20 = 20;
        /**
         * The Constant 25.
         */
        public static final Integer N25 = 25;
        /**
         * The Constant 57.
         */
        public static final Integer N57 = 57;

        /**
         * The Constant 100.
         */
        public static final Integer N100 = 100;
        /**
         * The Constant 256.
         */
        public static final Integer N256 = 256;
        /**
         * The Constant 500.
         */
        public static final Integer N500 = 500;
        /**
         * The Constant 1000.
         */
        public static final Integer N1000 = 1000;
        /**
         * The Constant 10000.
         */
        public static final Integer N10000 = 10000;
        /**
         * The Constant 20000.
         */
        public static final Integer N20000 = 20000;

        /**
         * The Constant 99999999999999.99.
         */
        public static final Double N99999999999999_99 = 99999999999999.99;

        /**
         * The Constant -1.
         */
        public static final Integer NF1 = -1;

        /**
         * The Constant -99999999999999.99.
         */
        public static final Double NF99999999999999_99 = -99999999999999.99;
    }
}