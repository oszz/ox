package org.oszz.ox.tools.message.java;

import java.io.File;
import java.io.IOException;

import org.oszz.ox.tools.message.AbstractMessageGenerator;
import org.oszz.ox.tools.message.ProtocCommandConstants;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息的Proto类生成
 * @author ZZ
 *
 */
public class JavaMsgProtoGenerator extends AbstractMessageGenerator {
	
	private static final Logger log = LoggerFactory.getLogger("JavaMsgGenerator");

	/**
	 * 消息的Proto类生成器
	 * @param msgConfig 消息配置
	 */
	public JavaMsgProtoGenerator(ModuleConfig moduleConfig) {
		super(moduleConfig);
	}

	//protoc -I=E:/file/work/docs/技术文档/protoFile/message --java_out=E:/file/work/docs/技术文档/protoFile/message/java  E:/file/work/docs/技术文档/protoFile/message/equip.proto
	@Override
	public void generate() {
		String protocPath = this.moduleConfig.getProtocPath();
		String inputPath = getAbsoluteInputPath(moduleConfig.getProtoBufFilePath());
		String outPath = getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
		
		log.info("protoc路径: " + protocPath);
		log.info("proto文件目录: " + inputPath);
		log.info("生成的java类输出目录: " + outPath);
		
		File[] protoFiles = getProtoFiles();
		
		Runtime runtime = Runtime.getRuntime();
		
		for(File protoFile : protoFiles){
			String protoFilePath = protoFile.getAbsolutePath();
			log.info("处理: " + protoFile.getName());
			String javaProtocCommond = ProtocCommandConstants.JAVA_PROTOC_COMMOND;
			javaProtocCommond = javaProtocCommond.replace(ProtocCommandConstants.PROTOC_EXE_REPLACE, protocPath);
			javaProtocCommond = javaProtocCommond.replace(ProtocCommandConstants.INPUT_PATH_REPLACE, inputPath);
			javaProtocCommond = javaProtocCommond.replace(ProtocCommandConstants.OUT_PATH_REPLACE, outPath);
			javaProtocCommond = javaProtocCommond.replace(ProtocCommandConstants.PROTO_FILE_REPLACE, protoFilePath);
			
			try {
				runtime.exec(javaProtocCommond);
				log.info("成功执行: " + javaProtocCommond);
			} catch (IOException e) {
				e.printStackTrace();
				log.debug("执行: {} 出错,错误信息：{}" , javaProtocCommond, e);
			}
		}
	}

}
