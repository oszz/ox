package org.oszz.ox.core.regist;

import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.core.message.IMessageReceived;
import org.oszz.ox.core.message.MessageCodeMapping;
import org.oszz.ox.core.message.MessageProcesserType;

public interface IMessageCodeRegister extends IRegister<MessageCodeMapping> {
	
	public void regist(Short msgCode, Class<? extends IMessageReceived> messageClass, 
			IMessageHandler msgHandler, MessageProcesserType messageProcesserType);
	
}
