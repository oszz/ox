package org.oszz.ox.common.utils.crypto;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.oszz.ox.common.utils.StringUtils;

/**
 * AES加密工具类
 * @author ZZ
 *
 */
public class AESUtils {
	
	/** 
	 * 加密 
	 * @param content 需要加密的内容 
	 * @param password 密匙 
	 * @return 返回加密后的16进制的字符串
	 */  
	public static String encrypt2HexStr(String content, String password) { 
		return StringUtils.parseByte2HexStr(encrypt(content, password));
	}  

	/** 
	 * 加密 
	 * @param content 需要加密的内容 
	 * @param password 密匙 
	 * @return 返回加密后的字节数组
	 */  
	public static byte[] encrypt(String content, String password) {  
        try { 
        	String name = CryptoType.AES.getName();
        	int length = CryptoType.AES.getSecretLength();
        	String charset = CryptoType.AES.getCharset();
        	
            KeyGenerator kgen = KeyGenerator.getInstance(name);  
            kgen.init(length, new SecureRandom(password.getBytes()));  
            SecretKey secretKey = kgen.generateKey();  
            byte[] enCodeFormat = secretKey.getEncoded();  
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, name);  
            Cipher cipher = Cipher.getInstance(name);// 创建密码器  
            byte[] byteContent = content.getBytes(charset);  
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化  
            byte[] result = cipher.doFinal(byteContent);  
            return result; // 加密  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return null;  
	}  
	
	/**
	 * 解密 
	 * @param hexStr 带解密16进制的字符串
	 * @param password 解密密钥 
	 * @return 返回解密后的字符串
	 */  
	public static String decrypt(String hexStr, String password) {
		byte[] bytes = StringUtils.parseHexStr2Bytes(hexStr);
		byte[] decryptResult = decrypt(bytes, password);
		return new String(decryptResult);
	} 
	
	/**
	 * 解密 
	 * @param content  待解密内容 
	 * @param password 解密密钥 
	 * @return 
	 */  
	public static byte[] decrypt(byte[] content, String password) {  
        try {  
        	String name = CryptoType.AES.getName();
        	int length = CryptoType.AES.getSecretLength();
        	
            KeyGenerator kgen = KeyGenerator.getInstance(name);  
            kgen.init(length, new SecureRandom(password.getBytes()));  
            SecretKey secretKey = kgen.generateKey();  
            byte[] enCodeFormat = secretKey.getEncoded();  
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, name);              
            Cipher cipher = Cipher.getInstance(name);// 创建密码器  
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化  
            byte[] result = cipher.doFinal(content);  
            return result; // 加密  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return null;  
	} 
}
