package org.soin.core.infrastructure.utils;


import java.security.MessageDigest;
import java.util.UUID;

/**
 * 安全处理工具
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:12
 **/
public class SecureUtil {

    /**
     * SHA-256算法
     */
    @SuppressWarnings("all")
    private static final String ALGORITHM = "SHA-256";

    /**
     * 单例
     */
    private static MessageDigest MESSAGEDIGEST;

    static {
        try {
            MESSAGEDIGEST = MessageDigest.getInstance(ALGORITHM);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 生成64HASH值
     *
     * @return HASH值
     */
    protected static String generateRandomHash() {
        //生成UUID
        String uuid = UUID.randomUUID().toString();
        //获取当前时间戳
        long millis = System.currentTimeMillis();
        //将UUID与当前时间戳拼接起来
        String combined = uuid + "_" + millis;
        //获取字节数组
        byte[] bytes = MESSAGEDIGEST.digest(combined.getBytes());
        // 生成64位hash值
        return bytesToHex(bytes);
    }

    /**
     * 将字节数组转换成16进制字符串
     *
     * @param bytes 字节数组
     * @return 16进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        return java.util.stream.IntStream.range(0, bytes.length)
                .mapToObj(i -> String.format("%02x", bytes[i] & 0xff))
                .collect(java.util.stream.Collectors.joining());
    }


}
