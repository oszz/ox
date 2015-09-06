package org.oszz.ox.tools.message.java;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.xmlbeans.impl.common.NameUtil;
import org.oszz.ox.common.utils.NameUtils;
import org.oszz.ox.tools.conf.Config;
import org.oszz.ox.tools.conf.ConfigManager;
import org.oszz.ox.tools.conf.msg.Message;
import org.oszz.ox.tools.constant.MessageCodeFileType;
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
			String className = NameUtils.getClassName(protoName) + ToolsConstant.HANDLER_NAME_SUFFIX;
			write(className, messages);
		}
	}
	
	private void write(String className, List<Message> messages){
		VelocityContext ctx = new VelocityContext();
		
//		ctx.put("packageName", packageName);
//		ctx.put("className", className);
//		ctx.put("messages", messages);
//		
//		VelocityUtils.write(this.msgHandler_vmFile, ctx, file.getAbsolutePath(), config.getCharsetName());
//		log.info("成功生成 {} . 字符集：{}", className, config.getCharsetName());
	}

}
