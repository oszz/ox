package org.oszz.ox.tools.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oszz.ox.tools.conf.msg.Message;
import org.oszz.ox.tools.conf.msg.MessagesXMLLoader;
import org.oszz.ox.tools.constant.MessageCodeFileType;

public class ConfigManager implements IConfigManager {
	
	private List<Message> messages ;
	
	private ConfigManager(){}
	
	public static ConfigManager getInstance(){
		return InnerClass.instance;
	}

	@Override
	public void init(String msgXMLFilePath) {
		try {
			MessagesXMLLoader msgXMLLoader = new MessagesXMLLoader();
			messages = msgXMLLoader.load(msgXMLFilePath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Message> getMessages() {
		return messages;
	}
	@Override
	public Map<MessageCodeFileType, List<Message>> getCodeFileMessages() {
		//将所有的消息分门别类
		Map<MessageCodeFileType, List<Message>> codeFileTypeMessages = 
				new HashMap<MessageCodeFileType, List<Message>>();
		for(Message message : messages){
			String msgType = message.getType();
			MessageCodeFileType[] codeFileTypes = MessageCodeFileType.getType(msgType);
			for(MessageCodeFileType mcft : codeFileTypes){
				List<Message> childMessages = codeFileTypeMessages.get(mcft);
				if(childMessages == null){
					childMessages = new ArrayList<Message>();
					childMessages.add(message);
					codeFileTypeMessages.put(mcft, childMessages);
				}else{
					childMessages.add(message);
				}
			}
		}
		return codeFileTypeMessages;
	}

	
	
	private static class InnerClass {
		public static ConfigManager instance = new ConfigManager();
	}

}
