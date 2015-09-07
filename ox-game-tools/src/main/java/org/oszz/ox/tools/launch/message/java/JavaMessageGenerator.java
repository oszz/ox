package org.oszz.ox.tools.launch.message.java;

import org.oszz.ox.tools.conf.Config;
import org.oszz.ox.tools.generator.IGenerator;
import org.oszz.ox.tools.message.IMessageCodeGenerator;
import org.oszz.ox.tools.message.IMessageGenerator;
import org.oszz.ox.tools.message.IMessageHandlerGenerator;
import org.oszz.ox.tools.message.IMessageProtoGenerator;
import org.oszz.ox.tools.message.java.JavaMsgCodeGenerator;
import org.oszz.ox.tools.message.java.JavaMsgGenerator;
import org.oszz.ox.tools.message.java.JavaMsgHandlerGenerator;
import org.oszz.ox.tools.message.java.JavaMsgProtoGenerator;

public class JavaMessageGenerator implements IGenerator{

	/**
	 * 生成MessageCode.java的模板类
	 */
	private static final String JAVA_MSG_CODE_FOR_VM_FILE = "vm/java/messageCode.vm";
	/**
	 * 生成消息Handler.java的模板类
	 */
	private static final String JAVA_MSG_HANDLER_VM_FILE = "vm/java/messageHandler.vm";
	
	/**
	 * 生成消息MsgCodeMappingRegisterService的模板类
	 */
	private static final String  JAVA_MSG_CODE_REGISTER_SERVICE_VM_FILE = "vm/java/messageCodeRegister.vm";
	
	/**
	 * 生成消息MsgCodeMappingRegisterService的模板类
	 */
	private static final String  JAVA_MSG_VM_FILE = "vm/java/message.vm";
	
	
	private Config config;
	
	public JavaMessageGenerator (Config config){
		this.config = config;
	}
	
	/**
	 * 生成java相关的代码
	 * @author ZZ
	 * @param msgConfig 消息的配置
	 * @param msgCodeConfigs 消息编码的相关配置（处理类、注释等）
	 */
	public void generator(Config config){
		//生成java的proto.java类
		IMessageProtoGenerator javaMsgProtoGenerator = new JavaMsgProtoGenerator(config);
		javaMsgProtoGenerator.generate();
		
		/*//生成java的MessageCode.java类
		IMessageGenerator javaMsgCodeGenerator = new JavaMsgCodeGenerator(moduleConfig, msgCodeConfigs, JAVA_MSG_CODE_FOR_VM_FILE);
		javaMsgCodeGenerator.generate();
		
		
		//生成消息的处理类
		IMessageGenerator javaMsgHandlerGenerator = new JavaMsgHandlerGenerator(moduleConfig, msgCodeConfigs, JAVA_MSG_HANDLER_VM_FILE);
		javaMsgHandlerGenerator.generate();
		
		//生成消息类（包装了code\protoBuf的消息）
		JavaMsgGenerator javaMsgGenerator = new JavaMsgGenerator(moduleConfig, msgCodeConfigs, JAVA_MSG_VM_FILE);
		javaMsgGenerator.generate();
		
		//生成消息编码与消息、处理类的对应关系
		IMessageGenerator javaMsgCodeMappingGenerator = new JavaMsgCodeRegisterGenerator(moduleConfig, msgCodeConfigs, JAVA_MSG_CODE_REGISTER_SERVICE_VM_FILE);
		javaMsgCodeMappingGenerator.generate();*/
	}

	@Override
	public void generate(){
		//生成protoBuf类
		IMessageProtoGenerator javaMsgProtoGenerator = new JavaMsgProtoGenerator(config);
		javaMsgProtoGenerator.generate();
			
		//生成java的MessageCode.java类
		IMessageCodeGenerator javaMsgCodeGenerator = new JavaMsgCodeGenerator(config, JAVA_MSG_CODE_FOR_VM_FILE);
		javaMsgCodeGenerator.generate();
		
		//生成消息类（包装了code\protoBuf的消息）
		IMessageGenerator javaMsgGenerator = new JavaMsgGenerator(config, JAVA_MSG_VM_FILE);
		javaMsgGenerator.generate();
		
		//生成消息的处理接口
		IMessageHandlerGenerator javaMsgHandlerGenerator = new JavaMsgHandlerGenerator(config, JAVA_MSG_HANDLER_VM_FILE);
		javaMsgHandlerGenerator.generate();	
	}
}
