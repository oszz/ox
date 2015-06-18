package org.oszz.ox.server.base.cache;

/**
 * 缓存内容的Key
 * @author ZZ
 *
 */
public enum CacheKey {

	/**
	 * 认证账号缓存列表
	 */
	AUTH_ACCOUNT_LIST("AUTH_ACCOUNT_LIST"),
	
	;
	
	private String key;
	private CacheKey(String key){
		this.key = key;
	}
	
	public String value(){
		return key;
	}
}
