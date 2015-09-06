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
import org.oszz.ox.tools.message.IMessageCodeGenerator;
import org.oszz.ox.tools.utils.VelocityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaMsgCodeGenerator extends GeneratorPathManagerAdapter implements IMessageCodeGenerator{
	
	private static final Logger log = LoggerFactory.getLogger("JavaMsgCodeGenerator");
	private Config config;
	private String msgCode_vmFile;
	
	public JavaMsgCodeGenerator(Config config, String msgCode_vmFile){
		this.config = config;
		this.msgCode_vmFile = msgCode_vmFile;
	}
	
	@Override
	public void generate() {
		Map<MessageCodeFileType, List<Message>> codeFileTypeMessages = ConfigManager.getInstance().getMessagesByType();
		for(Map.Entry<MessageCodeFileType, List<Message>> codeFileTypeMessageEntry : codeFileTypeMessages.entrySet()){
			MessageCodeFileType mcft = codeFileTypeMessageEntry.getKey();
			List<Message> childMessages = codeFileTypeMessageEntry.getValue();
			write(mcft, childMessages);
		}
	}
	
	private void write(MessageCodeFileType mcft , List<Message> messages){
		VelocityContext ctx = new VelocityContext();
		String className = mcft.getClassName();
		String packageName = mcft.getPackageName();
		
		ctx.put("packageName", packageName);
		ctx.put("className", className);
		ctx.put("messages", messages);
				 
		String outPath = this.getAbsoluteJavaOutputPath(config.getJavaOutputPath());
		
		String packagePath = ClassUtils.packageName2Path(packageName);
		outPath += SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
		outPath = FileUtils.getDirIfExists(outPath) + SystemProperty.FILE_SEPARATOR.getValue();
		
		String fileName = className + SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		String filePath = outPath + fileName;
		
		VelocityUtils.write(this.msgCode_vmFile, ctx, filePath, config.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", fileName, config.getCharsetName());
	}
}
