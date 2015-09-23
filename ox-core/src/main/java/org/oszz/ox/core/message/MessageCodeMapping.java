package org.oszz.ox.core.message;

/**
 * 消息编码的映射关系
 * @author ZZ
 *
 */
public class MessageCodeMapping {

	private Short msgCode; 
	private Class<? extends IMessageReceived> messageClass;
	private IMessageHandler msgHandler;
	private MessageProcesserType messageProcesserType;
	
	public MessageCodeMapping(Short msgCode, Class<? extends IMessageReceived> messageClass,
			IMessageHandler msgHandler, MessageProcesserType messageProcesserType){
		this.msgCode = msgCode;
		this.messageClass = messageClass;
		this.msgHandler = msgHandler;
		this.messageProcesserType = messageProcesserType;
		
	}
	public Short getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(Short msgCode) {
		this.msgCode = msgCode;
	}
	public Class<? extends IMessageReceived> getMessageClass() {
		return messageClass;
	}
	public void setMessageClass(Class<? extends IMessageReceived> messageClass) {
		this.messageClass = messageClass;
	}
	public IMessageHandler getMsgHandler() {
		return msgHandler;
	}
	public void setMsgHandler(IMessageHandler msgHandler) {
		this.msgHandler = msgHandler;
	}
	public MessageProcesserType getMessageProcesserType() {
		return messageProcesserType;
	}
	public void setMessageProcesserType(MessageProcesserType messageProcesserType) {
		this.messageProcesserType = messageProcesserType;
	}
}
