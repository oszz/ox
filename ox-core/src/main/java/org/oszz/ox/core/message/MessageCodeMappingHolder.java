package org.oszz.ox.core.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.oszz.ox.common.tuple.ThreeTuple;

public final class MessageCodeMappingHolder implements IMessageCodeMappingHolder {
	
	protected Map<Short, ThreeTuple<Class<? extends IMessage>, IMessageHandler, MessageProcesserType>> msgCodeMappings;
	
	private MessageCodeMappingHolder(){
		msgCodeMappings = new ConcurrentHashMap<Short, ThreeTuple<Class<? extends IMessage>, IMessageHandler, MessageProcesserType>>();
	}
	
	public static MessageCodeMappingHolder getInstance(){
		return InnerClass.instance;
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
	
	private static class InnerClass {
		public static final MessageCodeMappingHolder instance = new MessageCodeMappingHolder();
	}
}
