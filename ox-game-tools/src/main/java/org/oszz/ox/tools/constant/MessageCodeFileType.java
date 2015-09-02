package org.oszz.ox.tools.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成MessageCode时文件对应的分类
 * @author ZZ
 *
 */
public enum MessageCodeFileType {

	/**
	 * GameServer的messageCode
	 */
	MESSAGE_CODE_FOR_GAME_SERVER("MessageCodeForGameServer","org.oszz.ox.msg"),
	
	/**
	 * LoginServer的messageCode
	 */
	MESSAGE_CODE_FOR_LOGIN_SERVER("MessageCodeForLoginServer","org.oszz.ox.msg"),
	
	;
	
	private String className;
	private String packageName;
	
	private MessageCodeFileType(String className, String packageName){
		this.className = className;
		this.packageName = packageName;
	}
	
	public static MessageCodeFileType[] getType(String msgType){
		List<MessageCodeFileType> msgCodeTypes = new ArrayList<MessageCodeFileType>();
		if (msgType.equalsIgnoreCase(MessageType.CG.getType()) || 
				msgType.equalsIgnoreCase(MessageType.GC.getType()) ) {
			
			msgCodeTypes.add(MessageCodeFileType.MESSAGE_CODE_FOR_GAME_SERVER);
			
		} else if (msgType.equalsIgnoreCase(MessageType.CL.getType()) || 
				msgType.equalsIgnoreCase(MessageType.LC.getType())) {
			
			msgCodeTypes.add(MessageCodeFileType.MESSAGE_CODE_FOR_LOGIN_SERVER);
			
		} else if (msgType.equalsIgnoreCase(MessageType.LG.getType()) || 
				msgType.equalsIgnoreCase(MessageType.GL.getType()) ) {
			
			msgCodeTypes.add(MessageCodeFileType.MESSAGE_CODE_FOR_GAME_SERVER);
			msgCodeTypes.add(MessageCodeFileType.MESSAGE_CODE_FOR_LOGIN_SERVER);
		}
		return msgCodeTypes.toArray(new MessageCodeFileType[0]);
	}

	public String getClassName() {
		return className;
	}

	public String getPackageName() {
		return packageName;
	}
}
