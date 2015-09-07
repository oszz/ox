package org.oszz.ox.tools.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oszz.ox.tools.conf.msg.Message;
import org.oszz.ox.tools.conf.msg.MessagesXMLLoader;
import org.oszz.ox.tools.constant.MessageTypeCodeConfig;

public class ConfigManager implements IConfigManager {
	
	/**
	 * 所有的消息
	 */
	private List<Message> messages;
	
	/**
	 * 按消息类型（GC\LG\CG 等）的分类
	 */
	private Map<MessageTypeCodeConfig, List<Message>> typeCodeMessages;
	
	/**
	 * 按业务类型的分类
	 */
	private Map<String, List<Message>> protoMessages;
	
	
	private ConfigManager(){}
	
	public static ConfigManager getInstance(){
		return InnerClass.instance;
	}

	@Override
	public void init(String msgXMLFilePath) {
		try {
			MessagesXMLLoader msgXMLLoader = new MessagesXMLLoader(msgXMLFilePath);
			messages = msgXMLLoader.loadMessages();
			initTypeCodeMessages();
			initProtoMessages();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 初始化按消息类型（GC\LG\CG 等）的分类
	 * @author ZZ
	 */
	private void initTypeCodeMessages(){
		typeCodeMessages = new HashMap<MessageTypeCodeConfig, List<Message>>();
		for(Message message : messages){
			String msgType = message.getType();
			MessageTypeCodeConfig[] codeFileTypes = MessageTypeCodeConfig.getType(msgType);
			for(MessageTypeCodeConfig mcft : codeFileTypes){
				List<Message> childMessages = typeCodeMessages.get(mcft);
				if(childMessages == null){
					childMessages = new ArrayList<Message>();
					childMessages.add(message);
					typeCodeMessages.put(mcft, childMessages);
				}else{
					childMessages.add(message);
				}
			}
		}
	}
	/**
	 * 初始化按业务类型的分类
	 * @author ZZ
	 */
	private void initProtoMessages(){
		protoMessages = new HashMap<String, List<Message>>();
		for(Message message : messages){
			String protoName = message.getProtoName();
			List<Message> msgs = protoMessages.get(protoName);
			if(msgs == null){
				msgs = new ArrayList<Message>();
				msgs.add(message);
				protoMessages.put(protoName, msgs);
			}else{
				msgs.add(message);
			}
		}
	}
	

	@Override
	public List<Message> getMessages() {
		return messages;
	}
	@Override
	public Map<MessageTypeCodeConfig, List<Message>> getMessagesByType() {
		return typeCodeMessages;
	}
	
	@Override
	public Map<String, List<Message>> getMessagesByProto() {
		return protoMessages;
	}
	
	private static class InnerClass {
		public static ConfigManager instance = new ConfigManager();
	}
}
