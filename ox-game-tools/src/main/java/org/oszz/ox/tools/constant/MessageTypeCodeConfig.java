package org.oszz.ox.tools.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成MessageCode时文件对应的分类
 * 
 * @author ZZ
 *
 */
public enum MessageTypeCodeConfig {

	/**
	 * GameServer的messageCode
	 */
	MESSAGE_CODE_FOR_GAME_SERVER("GameServer", "MessageCodeForGameServer",
			"org.oszz.ox.gs", "org.oszz.ox.gs.handler",
			new MessageType[] { MessageType.CG, MessageType.GC, MessageType.LG,
					MessageType.GL },  "G"),

	/**
	 * LoginServer的messageCode
	 */
	MESSAGE_CODE_FOR_LOGIN_SERVER("LoginServer", "MessageCodeForLoginServer",
			"org.oszz.ox.ls", "org.oszz.ox.msg.ls.handler", new MessageType[] {
					MessageType.CL, MessageType.LC, MessageType.LG,
					MessageType.GL },  "L"),

	;

	private String name;
	private String className;
	private String packageName;
	private String handlerPackageName;

	private MessageType[] messageTypes;
	
	private String endWithStrForHandler;//消息类型结尾字符，用于生成handler类

	private MessageTypeCodeConfig(String name, String className,
			String packageName, String handlerPackageName,
			MessageType[] messageTypes, String endWithStrForHandler) {
		this.name = name;
		this.className = className;
		this.packageName = packageName;
		this.messageTypes = messageTypes;
		this.handlerPackageName = handlerPackageName;
		this.endWithStrForHandler = endWithStrForHandler;
	}

	public static MessageTypeCodeConfig[] getType(String msgTypeStr) {
		List<MessageTypeCodeConfig> msgCodeTypes = new ArrayList<MessageTypeCodeConfig>();

		MessageTypeCodeConfig[] msgCodeTypeConfigs = MessageTypeCodeConfig
				.values();
		for (MessageTypeCodeConfig mtcc : msgCodeTypeConfigs) {
			if (mtcc.isContain(msgTypeStr)) {
				msgCodeTypes.add(mtcc);
			}
		}
		return msgCodeTypes.toArray(new MessageTypeCodeConfig[0]);
	}

	public boolean isContain(String msgTypeStr) {
		MessageType msgType = MessageType.get(msgTypeStr);
		boolean flag = false;
		if (msgType != null) {
			MessageType[] msgTypes = getMessageTypes();
			for (MessageType mt : msgTypes) {
				if (msgType == mt) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	public String getName() {
		return name;
	}

	public String getClassName() {
		return className;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getHandlerPackageName() {
		return handlerPackageName;
	}

	public MessageType[] getMessageTypes() {
		return messageTypes;
	}
	
	public String getEndWithStrForHandler() {
		return endWithStrForHandler;
	}
}
