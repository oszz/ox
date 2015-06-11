package org.oszz.ox.core.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.oszz.ox.common.tuple.TwoTuple;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.MessageLite;

public class MessageCodeMapping {

//	public Map<Short, Class<? extends MessageLite>> msgCodeMappings;
	
	public Map<Short, TwoTuple<Class<? extends MessageLite>, IMessageHandler>> msgCodeMappings;

	private MessageCodeMapping(){
		msgCodeMappings = new ConcurrentHashMap<Short, TwoTuple<Class<? extends MessageLite>, IMessageHandler>>();
	}
	
	public static MessageCodeMapping getInstance(){
		return InnerClass.instance;
	}
	
	public void register(Short msgCode, Class<? extends GeneratedMessage> generatedMessageClass, IMessageHandler msgHandler){
		TwoTuple<Class<? extends MessageLite>, IMessageHandler> tt = 
				new TwoTuple<Class<? extends MessageLite>, IMessageHandler>(generatedMessageClass, msgHandler);
		msgCodeMappings.put(msgCode, tt);
	}
	
	public Class<? extends MessageLite> getMessageClass(Short msgCode){
		return msgCodeMappings.get(msgCode).getFirst();
	}
	
	public IMessageHandler getMessageHandler(Short msgCode){
		return msgCodeMappings.get(msgCode).getSecond();
	}
	
	private static final class InnerClass {
		public static MessageCodeMapping instance = new MessageCodeMapping();
	}
}
