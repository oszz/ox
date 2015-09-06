package org.oszz.ox.tools.message.java;

import java.util.List;
import java.util.Map;

import org.oszz.ox.tools.conf.Config;
import org.oszz.ox.tools.conf.ConfigManager;
import org.oszz.ox.tools.conf.msg.Message;
import org.oszz.ox.tools.constant.MessageCodeFileType;
import org.oszz.ox.tools.generator.GeneratorPathManagerAdapter;
import org.oszz.ox.tools.message.IMessageCodeMappingGenerator;

public class MessageCodeMappingGenerator extends GeneratorPathManagerAdapter
		implements IMessageCodeMappingGenerator {
	
	private String msgCodeMapping_vmFile;
	private Config config;

	public MessageCodeMappingGenerator(Config config,
			String msgCodeMapping_vmFile) {
		this.config = config;
		this.msgCodeMapping_vmFile = msgCodeMapping_vmFile;
	}

	@Override
	public void generate() {
		Map<MessageCodeFileType, List<Message>> codeFileTypeMessages = ConfigManager.getInstance().getMessagesByType();
		for(Map.Entry<MessageCodeFileType, List<Message>> codeFileTypeMessageEntry : codeFileTypeMessages.entrySet()){
			MessageCodeFileType mcft = codeFileTypeMessageEntry.getKey();
			List<Message> messages = codeFileTypeMessageEntry.getValue();
			
			
		}

	}
	
	private void write(){
		
	}

}
