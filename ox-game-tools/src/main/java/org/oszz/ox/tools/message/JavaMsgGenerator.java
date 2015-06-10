package org.oszz.ox.tools.message;

import java.io.File;
import java.io.IOException;


public class JavaMsgGenerator extends AbstractMessageGenerator {

	public JavaMsgGenerator(MessageConfig msgConfig) {
		super(msgConfig);
	}

	//protoc -I=E:/file/work/docs/技术文档/protoFile/message --java_out=E:/file/work/docs/技术文档/protoFile/message/java  E:/file/work/docs/技术文档/protoFile/message/equip.proto
	@Override
	public void generate() {
		String protocPath = this.msgConfig.getProtocPath();
		String inputPath = getAbsoluteInputPath();
		String outPath = getAbsoluteJavaOutputPath();
		
		System.out.println("protoc路径: " + protocPath);
		System.out.println("proto文件目录: " + inputPath);
		System.out.println("生成的java类输出目录: " + outPath);
		
		File[] protoFiles = getProtoFiles();
		
		Runtime runtime = Runtime.getRuntime();
		
		for(File protoFile : protoFiles){
			String protoFilePath = protoFile.getAbsolutePath();
			System.out.println("处理: " + protoFile.getName());
			String javaProtocCommond = ProtocCommandConstants.JAVA_PROTOC_COMMOND;
			javaProtocCommond = javaProtocCommond.replace(ProtocCommandConstants.PROTOC_EXE_REPLACE, protocPath);
			javaProtocCommond = javaProtocCommond.replace(ProtocCommandConstants.INPUT_PATH_REPLACE, inputPath);
			javaProtocCommond = javaProtocCommond.replace(ProtocCommandConstants.OUT_PATH_REPLACE, outPath);
			javaProtocCommond = javaProtocCommond.replace(ProtocCommandConstants.PROTO_FILE_REPLACE, protoFilePath);
			
			try {
				System.out.println("执行: " + javaProtocCommond);
				runtime.exec(javaProtocCommond);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
