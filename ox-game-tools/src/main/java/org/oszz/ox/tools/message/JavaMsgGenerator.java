package org.oszz.ox.tools.message;

public class JavaMsgGenerator extends AbstractMessageGenerator {

	public JavaMsgGenerator(MessageConfig msgConfig) {
		super(msgConfig);
	}

	//protoc -I=E:/file/work/docs/技术文档/protoFile/message --java_out=E:/file/work/docs/技术文档/protoFile/message/java  E:/file/work/docs/技术文档/protoFile/message/equip.proto
	@Override
	public void generate() {
		String protocPath = msgConfig.getProtocPath();
		String inputPath = msgConfig.getInputPath();
		String outputPath = msgConfig.getOutputPath();
		
	}

}
