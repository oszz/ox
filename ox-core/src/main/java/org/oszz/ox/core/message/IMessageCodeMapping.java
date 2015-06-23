package org.oszz.ox.core.message;


public interface IMessageCodeMapping {
	
	public void init();

	public void put(Short msgCode, Class<? extends IMessage> messageClass, 
			IMessageHandler msgHandler, MessageProcesserType messageProcesserType);
	
	public Class<? extends IMessage> getMessageClass(Short msgCode);
	
	public IMessageHandler getMessageHandler(Short msgCode);
	
	public MessageProcesserType getMessageProcesserType(Short msgCode);
}
