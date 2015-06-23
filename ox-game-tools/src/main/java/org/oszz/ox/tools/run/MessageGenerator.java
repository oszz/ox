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
import org.oszz.ox.tools.message.java.JavaMsgCodeMappingGenerator;
import org.oszz.ox.tools.message.java.JavaMsgGenerator;
import org.oszz.ox.tools.message.java.JavaMsgHandlerGenerator;
import org.oszz.ox.tools.message.java.JavaMsgProtoGenerator;

/**
 * 消息相关的生成器
 * @author ZZ
 *
 */
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
	private static final String  MSG_CODE_MAPPING_REGISTER_SERVICE_VM_FILE = "message/vm/messageCodeMapping.vm";
	
	/**
	 * 消息的配置文件
	 */
	private static final String MESSAGE_CODE_XML_PATH = "conf/message/messageCode.xml";
	
	/**
	 * 生成消息MsgCodeMappingRegisterService的模板类
	 */
	private static final String  MSG_JAVA_VM_FILE = "message/vm/messageForJava.vm";
	

	public static void main(String[] args) throws Exception {
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(CONIG_FILE_PAHT);
		
		//加载消息配置
		MessageConfig msgConfig = lpf.load(confProps, MessageConfig.class);
		
		//加载消息编码的相关配置
		MessageXMLLoader xmlLoader = new MessageXMLLoader();
		List<MessageCodeConfig> msgCodeConfigs = xmlLoader.load(MESSAGE_CODE_XML_PATH);
		
		generatorJava(msgConfig, msgCodeConfigs);
		
	}
	
	/**
	 * 生成java相关的代码
	 * @author ZZ
	 * @param msgConfig 消息的配置
	 * @param msgCodeConfigs 消息编码的相关配置（处理类、注释等）
	 */
	private static void generatorJava(MessageConfig msgConfig, List<MessageCodeConfig> msgCodeConfigs){
		//生成java的proto.java类
		IMessageGenerator javaMsgProtoGenerator = new JavaMsgProtoGenerator(msgConfig);
		javaMsgProtoGenerator.generate();
		
		//生成java的MessageCode.java类
		IMessageGenerator javaMsgCodeGenerator = new JavaMsgCodeGenerator(msgConfig, msgCodeConfigs, MSG_CODE_FOR_JAVA_VM_FILE);
		javaMsgCodeGenerator.generate();
		
		//生成消息的处理类
		IMessageGenerator javaMsgHandlerGenerator = new JavaMsgHandlerGenerator(msgConfig, msgCodeConfigs, MSG_HANDLER_VM_FILE);
		javaMsgHandlerGenerator.generate();
		
		//生成消息类（包装了code\protoBuf的消息）
		JavaMsgGenerator javaMsgGenerator = new JavaMsgGenerator(msgConfig, msgCodeConfigs, MSG_JAVA_VM_FILE);
		javaMsgGenerator.generate();
		
		//生成消息编码与消息、处理类的对应关系
		IMessageGenerator javaMsgCodeMappingGenerator = new JavaMsgCodeMappingGenerator(msgConfig, msgCodeConfigs, MSG_CODE_MAPPING_REGISTER_SERVICE_VM_FILE);
		javaMsgCodeMappingGenerator.generate();
	}
}
