package org.oszz.ox.core.conf;

/**
 * 默认配置
 * @author ZZ
 *
 */
public enum DefaultConfig {
	
	/**
	 * 默认字符集<br>
	 * UTF-8
	 */
	CHARSET("UTF-8"),
	
	/**
	 * GET请求
	 */
	HTTP_GET_REQUEST("GET"),
	
	/**
	 * POST请求
	 */
	HTTP_POST_REQUEST("POST"),
	
	/**
	 * ProtoBuf类的newBuilder方法名
	 */
	PROTO_BUF_NEW_BUILDER_METHOD_NAME("newBuilder"),
	
	/**
	 * favicon.ico请求的URL样式
	 */
	FAVICON_ICO_URL_PATTERN("favicon.ico"),
	
	;

	private String value = "";
	private DefaultConfig(String value){
		this.value = value;
	}
	
	/**
	 * 返回字符串值
	 * @author ZZ
	 * @return 返回字符串值
	 */
	public String getValue(){
		return this.value;
	}
	/**
	 * 返回int值
	 * @author ZZ
	 * @return 返回int值
	 */
	public int getIntValue(){
		return Integer.parseInt(this.value.trim());
	}
	/**
	 * 返回double值
	 * @author ZZ
	 * @return 返回double值
	 */
	public double getDoubleValue(){
		return Double.parseDouble(this.value.trim());
	}
	
	
}
