package org.oszz.ox.tools.message.conf;


public class MsgCodeMappingRegisterServiceConfig {

	private String constName;
	
	private String comments;
	
	private String msgClass;
	
	private String handlerClass;
	
	private String messageProcesserType;
	
	public MsgCodeMappingRegisterServiceConfig(String constName,String comments, String msgClass, String handlerClass,
			String messageProcesserType){
		this.constName = constName;
		this.comments = comments;
		this.msgClass = msgClass;
		this.handlerClass = handlerClass;
		this.messageProcesserType = messageProcesserType;
	}

	public String getConstName() {
		return constName;
	}

	public String getMsgClass() {
		return msgClass;
	}

	public String getHandlerClass() {
		return handlerClass;
	}

	public String getComments() {
		return comments;
	}

	public String getMessageProcesserType() {
		return messageProcesserType;
	}
}
