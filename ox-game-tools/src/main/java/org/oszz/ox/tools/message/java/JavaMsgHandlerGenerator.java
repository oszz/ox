package org.oszz.ox.tools.message.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FileUtils;
import org.oszz.ox.common.utils.NameUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.conf.Config;
import org.oszz.ox.tools.conf.ConfigManager;
import org.oszz.ox.tools.conf.msg.Message;
import org.oszz.ox.tools.constant.MessageTypeCodeConfig;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.oszz.ox.tools.generator.GeneratorPathManagerAdapter;
import org.oszz.ox.tools.message.IMessageHandlerGenerator;
import org.oszz.ox.tools.utils.VelocityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MessageHandler的生成器
 * @author ZZ
 *
 */
public class JavaMsgHandlerGenerator extends GeneratorPathManagerAdapter implements IMessageHandlerGenerator {
	private static final Logger log = LoggerFactory.getLogger("JavaMsgHandlerGenerator");
	private String msgHandler_vmFile;//模板文件
	private Config config;
	
	
	public JavaMsgHandlerGenerator(Config config, String msgHandler_vmFile) {
		this.config = config;
		this.msgHandler_vmFile = msgHandler_vmFile;
	}

	@Override
	public void generate() {
		Map<String, List<Message>> protoMessages = ConfigManager.getInstance().getMessagesByProto();
		for(Map.Entry<String, List<Message>> protoMessageEntry : protoMessages.entrySet()){
			String protoName = protoMessageEntry.getKey();
			List<Message> messages = protoMessageEntry.getValue();
			//因为是接口，所以类名前加 I
			String className = "I"+ NameUtils.getClassName(protoName) + ToolsConstant.HANDLER_NAME_SUFFIX;
			MessageTypeCodeConfig[] mtccs = MessageTypeCodeConfig.values();
			for(MessageTypeCodeConfig mtcc : mtccs){
				List<Message> filterMessages = filter(messages, mtcc);
				if(filterMessages != null && filterMessages.size() != 0){
					write(className, filterMessages, mtcc.getHandlerPackageName());
				}
			}
		}
	}
	
	private List<Message> filter(List<Message> messages, MessageTypeCodeConfig mtcc){
		List<Message> filterMessages = new ArrayList<Message>();
		for(Message message : messages){
			if(message.getType().endsWith(mtcc.getEndWithStrForHandler())){
				filterMessages.add(message);
			}
		}
		return filterMessages;
	}
	
	private void write(String className, List<Message> messages, String packageName){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("packageName", packageName);
		ctx.put("className", className);
		ctx.put("messages", messages);
		
		String packagePath = ClassUtils.packageName2Path(packageName);
		String outPath = this.getAbsoluteJavaOutputPath(config.getJavaOutputPath());
		outPath += SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
		outPath = FileUtils.getDirIfExists(outPath) + SystemProperty.FILE_SEPARATOR.getValue();
		String fileName = className + SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		
		VelocityUtils.write(this.msgHandler_vmFile, ctx, outPath+"/"+fileName, config.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", className, config.getCharsetName());
	}

}
