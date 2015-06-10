package org.oszz.ox.tools.run;

import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.tools.message.IMessageGenerator;
import org.oszz.ox.tools.message.JavaMsgCodeGenerator;
import org.oszz.ox.tools.message.JavaMsgGenerator;
import org.oszz.ox.tools.message.MessageConfig;

public class MessageGenerator {
	/**
	 * 消息命令的配置文件
	 */
	private static final String CONIG_FILE_PAHT = "message/message.properties";
	/**
	 * 生成MessageCode.java的模板类
	 */
	private static final String MSG_CODE_VM_FILE = "message/vm/messageCode.vm";
	/**
	 * 需要协议号的消息列表
	 */
	private static final String MESSAGE_CODE_LIST_PATH = "message/messageCode.list";

	public static void main(String[] args) {
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(CONIG_FILE_PAHT);
		
		MessageConfig msgConfig = lpf.load(confProps, MessageConfig.class);
		
		IMessageGenerator javaMsgGenerator = new JavaMsgGenerator(msgConfig);
		javaMsgGenerator.generate();
		
		IMessageGenerator javaMsgCodeGenerator = new JavaMsgCodeGenerator(msgConfig, MSG_CODE_VM_FILE, MESSAGE_CODE_LIST_PATH);
		javaMsgCodeGenerator.generate();
	}
}
