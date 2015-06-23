package org.oszz.ox.tools.message.java;

import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.tools.message.AbstractMessageCodeGenerator;
import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.message.conf.MessageConfig;
import org.oszz.ox.tools.utils.VelocityUtils;

/**
 * MessageCode的生成器
 * @author ZZ
 *
 */
public class JavaMsgCodeGenerator extends AbstractMessageCodeGenerator {
	

	private String msgCode_vmFile;//模板文件
	
	/**
	 * java端的MessageCode生成器
	 * @param msgConfig 消息的配置
	 * @param messageCodeListPath 消息码列表文件的路径
	 * @param msgCode_vmFile 模板数据
	 */
	public JavaMsgCodeGenerator(MessageConfig msgConfig, List<MessageCodeConfig> msgCodeConfigs, String msgCode_vmFile) {
		super(msgConfig, msgCodeConfigs);
		this.msgCode_vmFile = msgCode_vmFile;
		
	}

	@Override
	public void generate() {
		VelocityContext ctx = new VelocityContext();
		ctx.put("msgCodeConfigs", this.msgCodeConfigs);
				 
		String outPath = this.getAbsoluteJavaOutputPath(msgConfig.getJavaOutputPath());
		VelocityUtils.write(this.msgCode_vmFile, ctx, outPath+"/"+JAVA_MESSAGE_CODE_FILE_NAME, msgConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", JAVA_MESSAGE_CODE_FILE_NAME, msgConfig.getCharsetName());
	}
}
