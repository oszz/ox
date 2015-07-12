package org.oszz.ox.tools.message.java;

import java.io.File;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FileUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.oszz.ox.tools.message.AbstractMessageCodeGenerator;
import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.utils.VelocityUtils;

/**
 * MessageHandler的生成器
 * @author ZZ
 *
 */
public class JavaMsgHandlerGenerator extends AbstractMessageCodeGenerator {

	private String msgHandler_vmFile;//模板文件
	
	
	public JavaMsgHandlerGenerator(ModuleConfig moduleConfig,
			List<MessageCodeConfig> msgCodeConfigs, String msgHandler_vmFile) {
		super(moduleConfig, msgCodeConfigs);
		this.msgHandler_vmFile = msgHandler_vmFile;
	}

	@Override
	public void generate() {
//		String javaClassSuffix = SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		String outPath = this.getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
		
		for(MessageCodeConfig msgCodeConf : this.msgCodeConfigs){
			if(msgCodeConf.isGenerator()){//如果是生成
				String handlerClassPackageName = msgCodeConf.getHandlerClassPackageName().trim();
				if(msgCodeConf.getMsgType().equals(ToolsConstant.MESSAGE_CG_TYPE)){//如果CG类型的消息，需要生成handler类
//					VelocityContext ctx = new VelocityContext();
//					String comments = msgCodeConf.getComments();
//					String handlerClassName = msgCodeConf.getMsgHandlerClassName();
//					
//					ctx.put("handlerClassPackageName", handlerClassPackageName);
//					ctx.put("comments", comments);
//					ctx.put("handlerClassName", handlerClassName);
//					
//					String outPath = this.getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
//					String fileName = handlerClassName + javaClassSuffix;
//					String packagePath = ClassUtils.packageName2Path(handlerClassPackageName);
//					
//					outPath += SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
//					outPath = FileUtils.getDirIfExists(outPath) + SystemProperty.FILE_SEPARATOR.getValue();
//					VelocityUtils.write(this.msgHandler_vmFile, ctx, outPath + fileName, moduleConfig.getCharsetName());
//					log.info("成功生成 {} . 字符集：{}", fileName, moduleConfig.getCharsetName());
					
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
		}
	}
	
	private void writeFile(String handlerClassPackageName, String className, String comments, File file){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("handlerClassPackageName", handlerClassPackageName);
		ctx.put("comments", comments);
		ctx.put("handlerClassName", className);
		
		VelocityUtils.write(this.msgHandler_vmFile, ctx, file.getAbsolutePath(), moduleConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", file.getName(), moduleConfig.getCharsetName());
	}

}
