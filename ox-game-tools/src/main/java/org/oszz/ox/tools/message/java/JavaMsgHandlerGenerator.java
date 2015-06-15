package org.oszz.ox.tools.message.java;

import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FilePathUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.message.AbstractMessageCodeGenerator;
import org.oszz.ox.tools.message.MessageCodeConf;
import org.oszz.ox.tools.message.MessageConfig;
import org.oszz.ox.tools.utils.VelocityUtils;

/**
 * MessageHandler的生成器
 * @author ZZ
 *
 */
public class JavaMsgHandlerGenerator extends AbstractMessageCodeGenerator {

	private String msgHandler_vmFile;//模板文件
	
	
	public JavaMsgHandlerGenerator(MessageConfig msgConfig,
			String messageCodeListPath, String msgHandler_vmFile) {
		super(msgConfig, messageCodeListPath);
		this.msgHandler_vmFile = msgHandler_vmFile;
	}

	@Override
	public void generate() {
		String javaClassSuffix = SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		
		List<MessageCodeConf> msgCodeConfs = this.getMsgCodeConfs();
		for(MessageCodeConf msgCodeConf : msgCodeConfs){
			String handlerClassPackageName = msgCodeConf.getHandlerClassPackageName().trim();
			if(!"".equalsIgnoreCase(handlerClassPackageName)){//不为空，说明需要生产handler类
				VelocityContext ctx = new VelocityContext();
				String comments = msgCodeConf.getComments();
				String handlerClassName = msgCodeConf.getHandlerClassName();
				
				ctx.put("handlerClassPackageName", handlerClassPackageName);
				ctx.put("comments", comments);
				ctx.put("handlerClassName", handlerClassName);
				
				String outPath = this.getAbsoluteJavaOutputPath();
				String fileName = handlerClassName + javaClassSuffix;
				String packagePath = ClassUtils.packageName2Path(handlerClassPackageName);
				
				outPath += SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
				outPath = FilePathUtils.getDirIfExists(outPath) + SystemProperty.FILE_SEPARATOR.getValue();
				VelocityUtils.write(this.msgHandler_vmFile, ctx, outPath + fileName, msgConfig.getCharsetName());
				log.info("成功生成 {} . 字符集：{}", fileName, msgConfig.getCharsetName());
			}
		}

	}

}
