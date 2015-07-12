package org.oszz.ox.tools.message.java;

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
 * MessageCode的生成器
 * @author ZZ
 *
 */
public class JavaMsgCodeGenerator extends AbstractMessageCodeGenerator {
	
//	private static final String PACKAGE_NAME = "org.oszz.ox.server.base.message";
	

	private String msgCode_vmFile;//模板文件
	
	/**
	 * java端的MessageCode生成器
	 * @param msgConfig 消息的配置
	 * @param messageCodeListPath 消息码列表文件的路径
	 * @param msgCode_vmFile 模板数据
	 */
	public JavaMsgCodeGenerator(ModuleConfig moduleConfig, List<MessageCodeConfig> msgCodeConfigs, String msgCode_vmFile) {
		super(moduleConfig, msgCodeConfigs);
		this.msgCode_vmFile = msgCode_vmFile;
		
	}

	@Override
	public void generate() {
		VelocityContext ctx = new VelocityContext();
		String packageName = ToolsConstant.MAPPING_PACKAGE_NAME;
		ctx.put("packageName", packageName);
		ctx.put("msgCodeConfigs", this.msgCodeConfigs);
		
				 
		String outPath = this.getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
		
		String packagePath = ClassUtils.packageName2Path(packageName);
		outPath += SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
		outPath = FileUtils.getDirIfExists(outPath) + SystemProperty.FILE_SEPARATOR.getValue();
		
		VelocityUtils.write(this.msgCode_vmFile, ctx, outPath + JAVA_MESSAGE_CODE_FILE_NAME, moduleConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", JAVA_MESSAGE_CODE_FILE_NAME, moduleConfig.getCharsetName());
	}
}
