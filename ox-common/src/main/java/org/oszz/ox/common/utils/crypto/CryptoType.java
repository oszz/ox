package org.oszz.ox.common.utils.crypto;

/**
 * 加密类型
 * @author ZZ
 *
 */
public enum CryptoType {

	/**
	 * AES加密方式
	 */
	AES("AES", 128, "UTF-8"),
	
	;
	
	private String name;//加密方式的名字
	private int secretLength;//密匙的长度
	private String charset;//加密的字符集
	
	private CryptoType(String name, int secretLength, String charset){
		this.name = name;
		this.secretLength = secretLength;
		this.charset = charset;
	}

	/**
	 * 返回加密方式的名字<br>
	 * 例： AES、DES
	 * @author ZZ
	 * @return 返回加密方式的名字
	 */
	public String getName() {
		return name;
	}

	/**
	 * 返回密匙的长度
	 * @author ZZ
	 * @return 返回密匙的长度
	 */
	public int getSecretLength() {
		return secretLength;
	}

	/**
	 * 返回加密的字符集
	 * @author ZZ
	 * @return 返回加密的字符集
	 */
	public String getCharset() {
		return charset;
	}
	
	
	
}
