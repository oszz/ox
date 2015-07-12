package org.oszz.ox.tools.message.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.core.message.MessageProcesserType;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.oszz.ox.tools.message.AbstractMessageCodeGenerator;
import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.message.conf.MsgCodeMappingRegisterServiceConfig;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.utils.VelocityUtils;

public class JavaMsgCodeRegisterGenerator extends AbstractMessageCodeGenerator{
	private static final String CLASS_NAME = "MessageCodeRegister.java";
	
	private static final String ASYN = "MessageProcesserType.ASYN";
	private static final String SCENE = "MessageProcesserType.SCENE";
	private static final String WORLD = "MessageProcesserType.WORLD";
	
	private String msgCodeMappingRegisterService_vmFile;
	
	public JavaMsgCodeRegisterGenerator(ModuleConfig moduleConfig,
			List<MessageCodeConfig> msgCodeConfigs, String msgCodeMappingRegisterService_vmFile) {
		super(moduleConfig, msgCodeConfigs);
		this.msgCodeMappingRegisterService_vmFile = msgCodeMappingRegisterService_vmFile;
	}

	@Override
	public void generate() {
		List<MsgCodeMappingRegisterServiceConfig> mcmrscs = new ArrayList<MsgCodeMappingRegisterServiceConfig>();
		
		for(MessageCodeConfig msgCodeConf : this.msgCodeConfigs){
			String handlerClassPackageName = msgCodeConf.getHandlerClassPackageName().trim();
			if(msgCodeConf.getMsgType().equals(ToolsConstant.MESSAGE_CG_TYPE)){//如果CG类型的消息，需要生成映射关系
				String constName = msgCodeConf.getConstName();
				String comments = msgCodeConf.getComments();
//				String protoMsgClass = this.getFullClassName(msgCodeConf.getMsgPackageName(), msgCodeConf.getMsgName());
				String handlerClass = this.getFullName(handlerClassPackageName, msgCodeConf.getMsgHandlerClassName());
				String messageProcesserType = msgCodeConf.getMessageProcesserType();
				
				String msgClassName = msgCodeConf.getMsgClassName();
				String msgPackageName = msgCodeConf.getMsgPackageName();
				String msgClass = this.getFullClassName(msgPackageName, msgClassName);
				
				if(messageProcesserType.equalsIgnoreCase(MessageProcesserType.ASYN.valueOf())){
					messageProcesserType = ASYN;
				}else if(messageProcesserType.equalsIgnoreCase(MessageProcesserType.SCENE.valueOf())){
					messageProcesserType = SCENE;
				}else if(messageProcesserType.equalsIgnoreCase(MessageProcesserType.WORLD.valueOf())){
					messageProcesserType = WORLD;
				}
				
				MsgCodeMappingRegisterServiceConfig mcmrsc = new MsgCodeMappingRegisterServiceConfig(constName, comments, msgClass, handlerClass, messageProcesserType);
				mcmrscs.add(mcmrsc);
			}
		}
		
		VelocityContext ctx = new VelocityContext();
		
		String packageName = ToolsConstant.MAPPING_PACKAGE_NAME;
		ctx.put("packageName", packageName);
		ctx.put("mcmrscs", mcmrscs);
		
		String outPath = this.getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
		outPath = this.getAbsoluteOutputPath(outPath, packageName);
		VelocityUtils.write(this.msgCodeMappingRegisterService_vmFile, ctx, outPath + CLASS_NAME, moduleConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", CLASS_NAME, moduleConfig.getCharsetName());
	}
}
