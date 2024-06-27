package com.example.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    /**
     * 默认的密码字符串组合，用来将字节转换成16进制表示的字符，apche校验下载的文件的正确性用的就是这个默认组合
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(Md5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util.");
            nsaex.printStackTrace();
        }
    }

    /**
     * 生成字符串的md5校验值
     * @param s
     * @return
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
        String s= getMD5String(password);
        return s.equals(md5PwdStr);
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringBuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l <k; l++) {
            appendHexPair(bytes[1], stringBuffer);
        }
        return stringBuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringBuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4]; //取字节种高4位的数字转换，>>>为逻辑右移，将符号位一起右移，此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf];
        stringBuffer.append(c0);
        stringBuffer.append(c1);

    }
}
