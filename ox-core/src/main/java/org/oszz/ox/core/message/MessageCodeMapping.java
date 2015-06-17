package org.oszz.ox.core.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.oszz.ox.common.tuple.ThreeTuple;

public class MessageCodeMapping {

	
	public Map<Short, ThreeTuple<Class<? extends IMessage>, IMessageHandler, MessageProcesserType>> msgCodeMappings;

	private MessageCodeMapping(){
		msgCodeMappings = new ConcurrentHashMap<Short, ThreeTuple<Class<? extends IMessage>, IMessageHandler, MessageProcesserType>>();
	}
	
	public static MessageCodeMapping getInstance(){
		return InnerClass.instance;
	}
	
	public void register(Short msgCode, Class<? extends IMessage> messageClass, 
			IMessageHandler msgHandler, MessageProcesserType messageProcesserType){
		ThreeTuple<Class<? extends IMessage>, IMessageHandler, MessageProcesserType> tt = 
				new ThreeTuple<Class<? extends IMessage>, IMessageHandler, MessageProcesserType>(messageClass, msgHandler, messageProcesserType);
		msgCodeMappings.put(msgCode, tt);
	}
	
	public Class<? extends IMessage> getMessageClass(Short msgCode){
		return msgCodeMappings.get(msgCode).getFirst();
	}
	
	public IMessageHandler getMessageHandler(Short msgCode){
		return msgCodeMappings.get(msgCode).getSecond();
	}
	public MessageProcesserType getMessageProcesserType(Short msgCode){
		return msgCodeMappings.get(msgCode).getThird();
	}
	
	
	
	private static final class InnerClass {
		public static MessageCodeMapping instance = new MessageCodeMapping();
	}
}
