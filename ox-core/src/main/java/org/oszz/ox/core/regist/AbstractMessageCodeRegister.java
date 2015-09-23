package org.oszz.ox.core.regist;

import org.oszz.ox.core.Globals;
import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.core.message.IMessageReceived;
import org.oszz.ox.core.message.MessageCodeMapping;
import org.oszz.ox.core.message.MessageProcesserType;

public abstract class AbstractMessageCodeRegister implements IMessageCodeRegister {
	
	@Override
	public void regist(Short msgCode, Class<? extends IMessageReceived> messageClass,
			IMessageHandler msgHandler,
			MessageProcesserType messageProcesserType) {
		MessageCodeMapping msgCodeMapping = new MessageCodeMapping(msgCode, messageClass, msgHandler, messageProcesserType);
		Globals.addMessageCodeMapping(msgCodeMapping);
	}

	@Override
	public void regist(Class<? extends MessageCodeMapping> messageCodeMappingClazz) {
		//TODO 不用
	}

}
