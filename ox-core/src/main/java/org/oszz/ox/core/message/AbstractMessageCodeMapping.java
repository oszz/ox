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
		MessageCodeMappingHolder.getInstance().put(msgCode, messageClass, msgHandler, messageProcesserType);
	}
}
