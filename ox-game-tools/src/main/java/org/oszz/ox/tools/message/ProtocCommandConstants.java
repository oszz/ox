package org.oszz.ox.tools.message;

/**
 * Protoc的命令执行常量
 * @author ZZ
 *
 */
public interface ProtocCommandConstants {
	
	//protoc -I=E:/file/work/docs/技术文档/protoFile/message --java_out=E:/file/work/docs/技术文档/protoFile/message/java  E:/file/work/docs/技术文档/protoFile/message/equip.proto
	
	/**
	 * Protoc命令的替换符
	 */
	public static final String PROTOC_EXE_REPLACE = "!protoc_exe_replace!";
	
	/**
	 * proto文件输入路径的替换符
	 */
	public static final String INPUT_PATH_REPLACE = "!input_path_replace!";
	
	/**
	 * 生成类文件的输出路径的替换符
	 */
	public static final String OUT_PATH_REPLACE = "!out_path_replace!";
	
	/**
	 * proto文件的替换符
	 */
	public static final String PROTO_FILE_REPLACE = "!proto_file_replace!";

	/**
	 * 基础的Protoc命令
	 */
	public static final String BASE_PROTOC_COMMOND = PROTOC_EXE_REPLACE + " -I=" + INPUT_PATH_REPLACE;
	
	/**
	 * 生成JAVA类文件的Protoc命令
	 */
	public static final String JAVA_PROTOC_COMMOND = BASE_PROTOC_COMMOND + " --java_out=" + OUT_PATH_REPLACE +" " + PROTO_FILE_REPLACE;
}
