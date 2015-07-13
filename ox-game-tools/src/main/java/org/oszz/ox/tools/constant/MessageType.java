package org.oszz.ox.tools.constant;

public enum MessageType {

	/**
	 * 消息类型：GC 游戏服到客户端的消息<br>
	 * GameServer to Client 
	 */
	GC("GC"),
	/**
	 * 消息类型：CG 客户端到游戏服的消息<br>
	 * Client to GameServer
	 */
	CG("CG"),
	
	/**
	 * 消息类型：LG 登陆服到游戏服的消息<br>
	 * LoginServer to GameServer
	 */
	LG("LG"),
	/**
	 * 消息类型：GL 游戏服到登陆服的消息<br>
	 * GameServer to LoginServer
	 */
	GL("GL"),
	
	/**
	 * 消息类型：CL 客户端到登陆服的消息<br>
	 * Client to LoginServer
	 */
	CL("CL"),
	
	/**
	 * 消息类型：LC 登陆服到客户端的消息<br>
	 * LoginServer to Client
	 */
	LC("LC"),
	
	;
	
	private String type;
	
	private MessageType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
	
	public static MessageType get(String msgType){
		return MessageType.valueOf(msgType);
	}
}
