package com.weylan.blog.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

@Slf4j
public class AESUtils {

    private static String defaultKey = "xiaoshitoujizxcv";
    private static String defaultIv = "zxcvxiaoshitouji";
    private static String defaultEncodingFormat = "UTF-8";

    static {
        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encrypt(String data) {
        return AESUtils.encrypt(data, defaultKey, defaultIv, defaultEncodingFormat);
    }

    public static String decrypt(String data) {
        return AESUtils.decrypt(data, new String(Base64.encodeBase64(defaultKey.getBytes())), new String(Base64.encodeBase64(defaultIv.getBytes())), defaultEncodingFormat);
    }

    /**
     * 加密`
     *
     * @param data           //密文，被加密的数据
     * @param key            //秘钥
     * @param iv             //偏移量
     * @param encodingFormat //解密后的结果需要进行的编码
     * @return string
     */
    public static String encrypt(String data, String key, String iv, String encodingFormat) {
        try {
            //-----------------AES加密-------------------------------------------
            //加密方式： AES128(CBC/PKCS7Padding) + Base66
            IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            //PKCS5Padding比PKCS7Padding效率高，PKCS7Padding可支持IOS加解密

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, zeroIv);
            //-----------------base64 加密---------------------------------------
            // 加密后的字节数组
            byte[] encryptedData = cipher.doFinal(data.getBytes(encodingFormat));
            // 对加密后的字节数组进行base64编码
            byte[] base64Data = Base64.encodeBase64(encryptedData);
            return new String(base64Data);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }

    /**
     * @param data           base64加密后的内容
     * @param key            base64加密后的key
     * @param iv             base64加密后的偏移
     * @param encodingFormat 编码方式
     * @return
     */
    public static String decrypt(String data, String key, String iv, String encodingFormat) {
        //base64解密
        byte[] dataByte = Base64.decodeBase64(data);
        byte[] keyByte = Base64.decodeBase64(key);
        byte[] ivByte = Base64.decodeBase64(iv);
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(ivByte);
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, zeroIv);
            byte[] decryptedData = cipher.doFinal(dataByte);
            return new String(decryptedData, encodingFormat);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }

    public static void main(String[] args) throws Exception {
        String haha = AESUtils.encrypt("haha");
        System.out.println(haha);
        String encrypt = AESUtils.decrypt(haha,
                new String(Base64.encodeBase64(defaultKey.getBytes())),
                new String(Base64.encodeBase64(defaultIv.getBytes())),
                defaultEncodingFormat);
        System.out.println(encrypt);
        //测试微信
        String key = "bczsh6tNS36Xtk9TrPG/EQ==";
        String data =  "gIGlrVxyfp90/mhptdRJmsLegNHYNrGHRj2Xr6HogGdpaeVOw70hEHGYh4a98o2m8US8CRziGWAequtzK0GvfI8uaheNzzR2I2uJGkXOqQidv88Vom7AljIk5GfLDnWz4g8ycdvS8O8X/3Lqmdlj6bES42LhAGPj0VIIPCxc3haTGPAk3zSBAiOTkS3F7F2QhYrPxpMXTkL+JdKIumOBpRrN1LUSjpTBk4uBACsBHIQzcs7VdhhqKronnt2e5CEAONIDsOhMqKP6mfTJFAz3W4aueU97qhqGLU+PqhF/YTI1deSQEJ2nHmFtG3fxL+9mxbE8aJKBNcqEXoXYMuMjSouTzfYLRie6OmIJb2rPjZcqkRZ98gRlIKAIJirtunc0HYucZeyDL5ToRYkisfUuxtblQg0CLEtjk/kBODd7vk3rCdiGtK6KNZJxTUAuq+iHp8drI2SLbyT7jBOCKBGWmiNHhp64DRpvslMw2696nqE=";
        String iv = "tAhXzGp2Dl7IegvLcSsEdA==";
        String decrypt = AESUtils.decrypt(data, key, iv, "UTF-8");
        System.out.println(decrypt);
    }
}
