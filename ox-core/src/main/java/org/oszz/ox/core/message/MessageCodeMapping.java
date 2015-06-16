package org.oszz.ox.core.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.oszz.ox.common.tuple.ThreeTuple;

import com.google.protobuf.GeneratedMessage;

public class MessageCodeMapping {

	
	public Map<Short, ThreeTuple<Class<? extends GeneratedMessage>, IMessageHandler, MessageProcesserType>> msgCodeMappings;

	private MessageCodeMapping(){
		msgCodeMappings = new ConcurrentHashMap<Short, ThreeTuple<Class<? extends GeneratedMessage>, IMessageHandler, MessageProcesserType>>();
	}
	
	public static MessageCodeMapping getInstance(){
		return InnerClass.instance;
	}
	
	public void register(Short msgCode, Class<? extends GeneratedMessage> generatedMessageClass, 
			IMessageHandler msgHandler, MessageProcesserType messageProcesserType){
		ThreeTuple<Class<? extends GeneratedMessage>, IMessageHandler, MessageProcesserType> tt = 
				new ThreeTuple<Class<? extends GeneratedMessage>, IMessageHandler, MessageProcesserType>(generatedMessageClass, msgHandler, messageProcesserType);
		msgCodeMappings.put(msgCode, tt);
	}
	
	public Class<? extends GeneratedMessage> getMessageClass(Short msgCode){
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
