package org.oszz.ox.tools.run;

import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.tools.message.IMessageGenerator;
import org.oszz.ox.tools.message.MessageConfig;
import org.oszz.ox.tools.message.java.JavaMsgCodeGenerator;
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
	 * 需要协议号的消息列表
	 */
	private static final String MESSAGE_CODE_LIST_PATH = "message/messageCode.list";

	public static void main(String[] args) {
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(CONIG_FILE_PAHT);
		
		MessageConfig msgConfig = lpf.load(confProps, MessageConfig.class);
		
		IMessageGenerator javaMsgProtoGenerator = new JavaMsgProtoGenerator(msgConfig);
		javaMsgProtoGenerator.generate();
		
		IMessageGenerator javaMsgCodeGenerator = new JavaMsgCodeGenerator(msgConfig, MESSAGE_CODE_LIST_PATH, MSG_CODE_FOR_JAVA_VM_FILE);
		javaMsgCodeGenerator.generate();
		
		IMessageGenerator javaMsgHandlerGenerator = new JavaMsgHandlerGenerator(msgConfig, MESSAGE_CODE_LIST_PATH, MSG_HANDLER_VM_FILE);
		javaMsgHandlerGenerator.generate();
	}
}
