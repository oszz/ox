package org.oszz.ox.tools.run;

import java.util.List;
import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.tools.message.IMessageGenerator;
import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.message.conf.MessageConfig;
import org.oszz.ox.tools.message.conf.MessageXMLLoader;
import org.oszz.ox.tools.message.java.JavaMsgCodeGenerator;
import org.oszz.ox.tools.message.java.JavaMsgCodeMappingRegisterServiceGenerator;
import org.oszz.ox.tools.message.java.JavaMsgHandlerGenerator;
import org.oszz.ox.tools.message.java.JavaMsgProtoGenerator;

public class MessageGenerator {
	/**
	 * 消息命令的配置文件
	 */
	private static final String CONIG_FILE_PAHT = "message/message.properties";
	/**
	 * 生成MessageCode.java的模板类
	 */
	private static final String MSG_CODE_FOR_JAVA_VM_FILE = "message/vm/messageCodeForJava.vm";
	/**
	 * 生成消息Handler.java的模板类
	 */
	private static final String MSG_HANDLER_VM_FILE = "message/vm/messageHandler.vm";
	
	/**
	 * 生成消息MsgCodeMappingRegisterService的模板类
	 */
	private static final String  MSG_CODE_MAPPING_REGISTER_SERVICE_VM_FILE = "message/vm/MessageCodeMappingRegisterService.vm";
	
	/**
	 * 消息的配置文件
	 */
	private static final String MESSAGE_CODE_XML_PATH = "conf/message/messageCode.xml";

	public static void main(String[] args) throws Exception {
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(CONIG_FILE_PAHT);
		
		MessageConfig msgConfig = lpf.load(confProps, MessageConfig.class);
		
		IMessageGenerator javaMsgProtoGenerator = new JavaMsgProtoGenerator(msgConfig);
		javaMsgProtoGenerator.generate();
		
		MessageXMLLoader xmlLoader = new MessageXMLLoader();
		List<MessageCodeConfig> msgCodeConfigs = xmlLoader.load(MESSAGE_CODE_XML_PATH);
		
		IMessageGenerator javaMsgCodeGenerator = new JavaMsgCodeGenerator(msgConfig, msgCodeConfigs, MSG_CODE_FOR_JAVA_VM_FILE);
		javaMsgCodeGenerator.generate();
		
		IMessageGenerator javaMsgHandlerGenerator = new JavaMsgHandlerGenerator(msgConfig, msgCodeConfigs, MSG_HANDLER_VM_FILE);
		javaMsgHandlerGenerator.generate();
		
		
		IMessageGenerator javaMsgCodeMappingRegisterServiceGenerator = new JavaMsgCodeMappingRegisterServiceGenerator(msgConfig, msgCodeConfigs, MSG_CODE_MAPPING_REGISTER_SERVICE_VM_FILE);
		javaMsgCodeMappingRegisterServiceGenerator.generate();
	}
}
