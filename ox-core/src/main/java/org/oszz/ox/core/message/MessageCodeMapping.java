package org.oszz.ox.core.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.protobuf.MessageLite;

public class MessageCodeMapping {

	public Map<Short, Class<MessageLite>> msgCodeMappings;

	private MessageCodeMapping(){
		msgCodeMappings = new ConcurrentHashMap<Short, Class<MessageLite>>();
	}
	
	public static MessageCodeMapping getInstance(){
		return InnerClass.instance;
	}
	
	public void register(Short msgCode, Class<MessageLite> msgLiteClass){
		msgCodeMappings.put(msgCode, msgLiteClass);
	}
	
	public Class<MessageLite> getMessageLite(Short msgCode){
		return msgCodeMappings.get(msgCode);
	}
	
	private static final class InnerClass {
		public static MessageCodeMapping instance = new MessageCodeMapping();
	}
}
