package org.oszz.ox.core.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.oszz.ox.common.tuple.ThreeTuple;

public abstract class AbstractMessageCodeMapping implements IMessageCodeMapping{

	
	protected Map<Short, ThreeTuple<Class<? extends IMessage>, IMessageHandler, MessageProcesserType>> msgCodeMappings;

	public AbstractMessageCodeMapping(){
		msgCodeMappings = new ConcurrentHashMap<Short, ThreeTuple<Class<? extends IMessage>, IMessageHandler, MessageProcesserType>>();
	}
	
	@Override
	public void put(Short msgCode, Class<? extends IMessage> messageClass,
			IMessageHandler msgHandler,
			MessageProcesserType messageProcesserType) {
		ThreeTuple<Class<? extends IMessage>, IMessageHandler, MessageProcesserType> tt = 
				new ThreeTuple<Class<? extends IMessage>, IMessageHandler, MessageProcesserType>(messageClass, msgHandler, messageProcesserType);
		msgCodeMappings.put(msgCode, tt);
	}

	@Override
	public Class<? extends IMessage> getMessageClass(Short msgCode) {
		return msgCodeMappings.get(msgCode).getFirst();
	}

	@Override
	public IMessageHandler getMessageHandler(Short msgCode) {
		return msgCodeMappings.get(msgCode).getSecond();
	}

	@Override
	public MessageProcesserType getMessageProcesserType(Short msgCode) {
		return msgCodeMappings.get(msgCode).getThird();
	}

	
}
