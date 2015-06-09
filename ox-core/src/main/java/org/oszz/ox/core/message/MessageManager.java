package org.oszz.ox.core.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MessageManager {
	
	public Map<Short, IMessageHandler> msgHandlers;

	private MessageManager(){
		msgHandlers = new ConcurrentHashMap<Short, IMessageHandler>();
	}
	
	public static MessageManager getInstance(){
		return InnerClass.instance;
	}
	
	public void register(Short msgCode, IMessageHandler msgHandler){
		msgHandlers.put(msgCode, msgHandler);
	}
	
	public void register(IMessage message, IMessageHandler msgHandler){
		register(message.getCode(), msgHandler);
	}
	
	public IMessageHandler getHandler(Short msgCode){
		return msgHandlers.get(msgCode);
	}
	
	private static final class InnerClass {
		public static MessageManager instance = new MessageManager();
	}
}
