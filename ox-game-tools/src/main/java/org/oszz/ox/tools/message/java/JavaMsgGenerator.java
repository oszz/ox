package org.oszz.ox.tools.message.java;

import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FilePathUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.message.AbstractMessageCodeGenerator;
import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.message.conf.MessageConfig;
import org.oszz.ox.tools.utils.VelocityUtils;

/**
 * MessageCode的生成器
 * @author ZZ
 *
 */
public class JavaMsgGenerator extends AbstractMessageCodeGenerator {
	

	private String msg_vmFile;//模板文件
	
	/**
	 * java端的Message生成器
	 * @param msgConfig 消息的配置
	 * @param messageCodeListPath 消息码列表文件的路径
	 * @param msgCode_vmFile 模板数据
	 */
	public JavaMsgGenerator(MessageConfig msgConfig, List<MessageCodeConfig> msgCodeConfigs, String msg_vmFile) {
		super(msgConfig, msgCodeConfigs);
		this.msg_vmFile = msg_vmFile;
		
	}

	@Override
	public void generate() {
		String javaClassSuffix = SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		for(MessageCodeConfig msgCodeConf : this.msgCodeConfigs){
			if(msgCodeConf.isGenerator()){
				String msgClassName = msgCodeConf.getMsgClassName();
				String msgPackageName = msgCodeConf.getMsgPackageName();
				String msgCodeConstName = msgCodeConf.getConstName();
				String comments = msgCodeConf.getComments();
				String protobufMessageClass = this.getFullClassName(msgPackageName, msgCodeConf.getMsgName());
				
				VelocityContext ctx = new VelocityContext();
				ctx.put("msgClassName", msgClassName);
				ctx.put("msgPackageName", msgPackageName);
				ctx.put("msgCodeConstName", msgCodeConstName);
				ctx.put("comments", comments);
				ctx.put("protobufMessageClass", protobufMessageClass);
				
				String packagePath = ClassUtils.packageName2Path(msgPackageName);
				String outPath = this.getAbsoluteJavaOutputPath(msgConfig.getJavaOutputPath());
				outPath += SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
				outPath = FilePathUtils.getDirIfExists(outPath) + SystemProperty.FILE_SEPARATOR.getValue();
				String fileName = msgClassName + javaClassSuffix;
				VelocityUtils.write(this.msg_vmFile, ctx, outPath+"/"+fileName, msgConfig.getCharsetName());
				log.info("成功生成 {} . 字符集：{}", fileName, msgConfig.getCharsetName());
			}
		}
	}
}
