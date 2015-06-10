package org.oszz.ox.core.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.protobuf.MessageOrBuilder;

public class MessageCodeMapping {

	public Map<Short, Class<MessageOrBuilder>> msgCodeMappings;

	private MessageCodeMapping(){
		msgCodeMappings = new ConcurrentHashMap<Short, Class<MessageOrBuilder>>();
	}
	
	public static MessageCodeMapping getInstance(){
		return InnerClass.instance;
	}
	
	public void register(Short msgCode, Class<MessageOrBuilder> generatedMessageClass){
		msgCodeMappings.put(msgCode, generatedMessageClass);
	}
	
	public Class<MessageOrBuilder> getGeneratedMessage(Short msgCode){
		return msgCodeMappings.get(msgCode);
	}
	
	private static final class InnerClass {
		public static MessageCodeMapping instance = new MessageCodeMapping();
	}
}
