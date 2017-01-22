package com.loan.app.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * FileName: com.loan.app.utils.SecurityUtils.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/22 15:11
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:11    1.0          Create
 */
public class SecurityUtils {

    private static SecurityUtils instance;

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private SecurityUtils(){

    }

    public static SecurityUtils getInstance(){
        if(null == instance)
            return new SecurityUtils();
        return instance;
    }

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    private String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 转换字节数组为高位字符串
     * @param b 字节数组
     * @return
     */
    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * MD5 摘要计算(byte[]).
     * @param src byte[]
     * @throws Exception
     * @return String
     */
    public String md5Digest(byte[] src) {
        MessageDigest alg;
        try {
            // MD5 is 32 bit message digest
            alg = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return byteArrayToHexString(alg.digest(src));
    }

    public static void main(String[] args) {
        try {
            System.out.println(SecurityUtils.getInstance().md5Digest("111111".getBytes("iso8859-1")));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
