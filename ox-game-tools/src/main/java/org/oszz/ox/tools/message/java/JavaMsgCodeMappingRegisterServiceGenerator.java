package org.oszz.ox.tools.message.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FilePathUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.core.message.MessageProcesserType;
import org.oszz.ox.tools.message.AbstractMessageCodeGenerator;
import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.message.conf.MessageConfig;
import org.oszz.ox.tools.message.conf.MsgCodeMappingRegisterServiceConfig;
import org.oszz.ox.tools.utils.VelocityUtils;

public class JavaMsgCodeMappingRegisterServiceGenerator extends AbstractMessageCodeGenerator{
	private static final String PACKAGE_NAME = "org.oszz.ox.server.base.message";
	private static final String CLASS_NAME = "MessageCodeMappingRegisterService.java";
	
	private static final String ASYN = "MessageProcesserType.ASYN";
	private static final String SCENE = "MessageProcesserType.SCENE";
	private static final String WORLD = "MessageProcesserType.WORLD";
	
	private String msgCodeMappingRegisterService_vmFile;
	
	public JavaMsgCodeMappingRegisterServiceGenerator(MessageConfig msgConfig,
			List<MessageCodeConfig> msgCodeConfigs, String msgCodeMappingRegisterService_vmFile) {
		super(msgConfig, msgCodeConfigs);
		this.msgCodeMappingRegisterService_vmFile = msgCodeMappingRegisterService_vmFile;
	}

	@Override
	public void generate() {
		String javaClassSuffix = SystemProperty.CLASS_SUFFIX.getValue();
		
		List<MsgCodeMappingRegisterServiceConfig> mcmrscs = new ArrayList<MsgCodeMappingRegisterServiceConfig>();
		
		for(MessageCodeConfig msgCodeConf : this.msgCodeConfigs){
			String handlerClassPackageName = msgCodeConf.getHandlerClassPackageName().trim();
			if(!"".equalsIgnoreCase(handlerClassPackageName)){//不为空，说明需要生产handler类
				String constName = msgCodeConf.getConstName();
				String comments = msgCodeConf.getComments();
				String msgClass = msgCodeConf.getMsgPackageName() + "." + msgCodeConf.getMsgName() + javaClassSuffix;
				String handlerClass = handlerClassPackageName + "." + msgCodeConf.getMsgHandlerClassName();
				String messageProcesserType = msgCodeConf.getMessageProcesserType();
				
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
		
		ctx.put("mcmrscs", mcmrscs);
		
		String outPath = this.getAbsoluteJavaOutputPath();
		String packagePath = ClassUtils.packageName2Path(PACKAGE_NAME);
		
		outPath += SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
		outPath = FilePathUtils.getDirIfExists(outPath) + SystemProperty.FILE_SEPARATOR.getValue();
		VelocityUtils.write(this.msgCodeMappingRegisterService_vmFile, ctx, outPath + CLASS_NAME, msgConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", CLASS_NAME, msgConfig.getCharsetName());
	}
}
