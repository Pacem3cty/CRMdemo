package com.example.crmdemo.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author ᛟ
 * @date 2021/11/25 - 22:35
 */
public class AESUtil {
    static Cipher cipher;
    static Key key;

    static {
        try {
            key = AESUtil.getKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String originalText) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {//加密
        cipher.init(Cipher.ENCRYPT_MODE, key);//设置为加密模式
        byte[] result = cipher.doFinal(originalText.getBytes());
        System.out.println("jdk aes encrypt: " + Base64.encodeBase64String(result));
        return Base64.encodeBase64String(result);
    }

    public static String decrypt(String cipherText) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {//解密
        cipher.init(Cipher.DECRYPT_MODE, key);//设置为解密模式
        byte[] result = cipher.doFinal(Base64.decodeBase64(cipherText));
        System.out.println("jdk aes desrypt:" + new String(result));
        return new String(result);

    }

    public static Key getKey() throws NoSuchAlgorithmException {//返回随机秘钥对象
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");//指定AES算法构造KeyGenerator对象
        keyGenerator.init(128);//设置密钥长度为128
        SecretKey secretKey = keyGenerator.generateKey();//生成密钥对象
        byte[] keyBytes = secretKey.getEncoded();
        Key key = new SecretKeySpec(keyBytes, "AES");
        return key;
    }
}
