package org.oszz.ox.tools.message.java;

import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FileUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.conf.Config;
import org.oszz.ox.tools.conf.ConfigManager;
import org.oszz.ox.tools.conf.msg.Message;
import org.oszz.ox.tools.constant.MessageCodeFileType;
import org.oszz.ox.tools.generator.GeneratorPathManagerAdapter;
import org.oszz.ox.tools.message.IMessageGenerator;
import org.oszz.ox.tools.utils.VelocityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MessageCode的生成器
 * @author ZZ
 *
 */
public class JavaMsgGenerator extends GeneratorPathManagerAdapter implements IMessageGenerator{
	private static final Logger log = LoggerFactory.getLogger("JavaMsgGenerator");
	

	private String msg_vmFile;//模板文件
	private Config config;
	
	/**
	 * java端的Message生成器
	 * @param msgConfig 消息的配置
	 * @param messageCodeListPath 消息码列表文件的路径
	 * @param msgCode_vmFile 模板数据
	 */
	public JavaMsgGenerator(Config config, String msg_vmFile) {
		this.config = config;
		this.msg_vmFile = msg_vmFile;
		
	}

	@Override
	public void generate() {
		Map<MessageCodeFileType, List<Message>> codeFileTypeMessages = ConfigManager.getInstance().getMessagesByType();
		for(Map.Entry<MessageCodeFileType, List<Message>> codeFileTypeMessageEntry : codeFileTypeMessages.entrySet()){
			MessageCodeFileType mcft = codeFileTypeMessageEntry.getKey();
			List<Message> messages = codeFileTypeMessageEntry.getValue();
			String messageCodeClassName = this.getFullName(mcft.getPackageName(), mcft.getClassName());
			for(Message message : messages){
				if(message.isGenerator()){
					write(message, messageCodeClassName);
				}
			}
		}
	}
	
	private void write(Message message, String messageCodeClassName){
		String className = message.getMsgClassName();
		String packageName = message.getPackageName();
		String msgCodeConstName = message.getConstName();
		String comments = message.getComments();
		String protobufMessageClass = this.getFullClassName(packageName, message.getName());
		
		VelocityContext ctx = new VelocityContext();
		ctx.put("className", className);
		ctx.put("packageName", packageName);
		ctx.put("msgCodeConstName", msgCodeConstName);
		ctx.put("comments", comments);
		ctx.put("protobufMessageClass", protobufMessageClass);
		ctx.put("messageCodeClassName", messageCodeClassName);
		
		String packagePath = ClassUtils.packageName2Path(packageName);
		String outPath = this.getAbsoluteJavaOutputPath(config.getJavaOutputPath());
		outPath += SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
		outPath = FileUtils.getDirIfExists(outPath) + SystemProperty.FILE_SEPARATOR.getValue();
		String fileName = className + SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		VelocityUtils.write(this.msg_vmFile, ctx, outPath+"/"+fileName, config.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", fileName, config.getCharsetName());
	}
	
	@Override
	public String getFullClassName(String packageName, String className) {
		return getFullName(packageName, className)+ SystemProperty.CLASS_SUFFIX.getValue();
	}
	
	@Override
	public String getFullName(String packageName, String className) {
		return packageName + "." + className;
	}
}
