package org.oszz.ox.core.conf;

/**
 * HttpSession的Key
 * @author ZZ
 *
 */
public enum HttpSessionKey {

	/**
	 * 玩家
	 */
	PLAYER("palyer"),
	
	/**
	 * Game Server’session key
	 */
	GS_SESSION("gsSession"),
	;
	
	
	private String value;
	private HttpSessionKey(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
