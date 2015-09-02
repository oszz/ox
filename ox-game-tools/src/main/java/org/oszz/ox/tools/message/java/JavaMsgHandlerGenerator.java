package org.oszz.ox.tools.message.java;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.tools.conf.Config;
import org.oszz.ox.tools.conf.ConfigManager;
import org.oszz.ox.tools.conf.msg.Message;
import org.oszz.ox.tools.constant.MessageCodeFileType;
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
		Map<MessageCodeFileType, List<Message>> codeFileTypeMessages = ConfigManager.getInstance().getCodeFileMessages();
		for(Map.Entry<MessageCodeFileType, List<Message>> codeFileTypeMessageEntry : codeFileTypeMessages.entrySet()){
			MessageCodeFileType mcft = codeFileTypeMessageEntry.getKey();
			List<Message> messages = codeFileTypeMessageEntry.getValue();
		}
		
		
		
//		String javaClassSuffix = SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		/*String outPath = this.getAbsoluteJavaOutputPath(config.getJavaOutputPath());
		
		for(MessageCodeConfig msgCodeConf : this.msgCodeConfigs){
			if(msgCodeConf.isGenerator()){//如果是生成
				String handlerClassPackageName = msgCodeConf.getHandlerClassPackageName().trim();
				if(msgCodeConf.getMsgType().equals(ToolsConstant.MESSAGE_CG_TYPE)){//如果CG类型的消息，需要生成handler类
					String comments = msgCodeConf.getComments();
					String handlerClassName = msgCodeConf.getMsgHandlerClassName();
					String fileName = handlerClassName + SystemProperty.JAVA_CLASS_SUFFIX.getValue();
					String packagePath = ClassUtils.packageName2Path(handlerClassPackageName);
					
					String fileOutputPath = outPath + SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
					fileOutputPath = FileUtils.getDirIfExists(fileOutputPath) + SystemProperty.FILE_SEPARATOR.getValue();
					File file = new File(fileOutputPath + fileName);
					if(!file.exists()){//如果dataManager的类存在，则不用再生成dataManager
						writeFile(handlerClassPackageName, handlerClassName, comments, file);
					}
				}
			}
		}*/
	}
	
	private void writeFile(String handlerClassPackageName, String className, String comments, File file){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("handlerClassPackageName", handlerClassPackageName);
		ctx.put("comments", comments);
		ctx.put("handlerClassName", className);
		
		VelocityUtils.write(this.msgHandler_vmFile, ctx, file.getAbsolutePath(), config.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", file.getName(), config.getCharsetName());
	}

}
