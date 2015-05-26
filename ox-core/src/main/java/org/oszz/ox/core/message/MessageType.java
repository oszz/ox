package org.oszz.ox.core.message;

/**
 * 消息类型
 * @author ZZ
 *
 */
public enum MessageType {
	
	/**
	 * 个人消息
	 */
	PERSONAL("personal"),
	
	/**
	 * 世界消息
	 */
	WORLD("world"),
	
	;

	private String type;
	private MessageType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
}
