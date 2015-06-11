package org.oszz.ox.tools.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.oszz.ox.common.utils.NameUtils;
import org.oszz.ox.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMessageCodeGenerator extends AbstractMessageGenerator implements IMessageCodeGenerator {
	protected static final Logger log = LoggerFactory.getLogger("JavaMsgCodeGenerator");
	private static int CODE_START = 0x0001;
	protected static final String JAVA_MESSAGE_CODE_FILE_NAME = "MessageCode.java";
	
	private String messageCodeListPath;
	
	List<MessageCodeConf> messageCodeConfs = null;

	public AbstractMessageCodeGenerator(MessageConfig msgConfig, String messageCodeListPath) {
		super(msgConfig);
		this.messageCodeListPath = messageCodeListPath;
		messageCodeConfs = readCodeList();
	}
	
	@Override
	public List<MessageCodeConf> getMsgCodeConfs() {
		return messageCodeConfs;
	}

	private List<MessageCodeConf> readCodeList() {
		InputStream in = null;
		BufferedReader br = null;
		int lineNum = 0;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(messageCodeListPath);
			br = new BufferedReader(new InputStreamReader(in, msgConfig.getCharsetName()));
			String lineContent = "";
			List<MessageCodeConf> msgCodeConfs = new ArrayList<MessageCodeConf>();
			
			while((lineContent = br.readLine()) != null){
				lineNum++;
				if(!lineContent.startsWith("#") //“#”开头的是注释
						&& !"".equals(lineContent.trim())){
					String codeHex = StringUtils.toHex(CODE_START++);//消息编码
					MessageCodeConf msgCodeConf = getMsgCodeConf(lineContent, codeHex, lineNum);
					msgCodeConfs.add(msgCodeConf);
				}
			}
			return msgCodeConfs;
		}catch (Exception e) {
			log.error("readCodeList 出错. 文件-{}, 错误在第{}行, 原因-{}", messageCodeListPath, lineNum, e);
			throw new RuntimeException(e);
		} finally {
			if(in != null){try {in.close();} catch (IOException e) {}}
			if(br != null){try {br.close();} catch (IOException e) {}}
		}
	}
	
	private MessageCodeConf getMsgCodeConf(String lineContent, String codeHex, int lineNum){
		String[] lineStrs = lineContent.trim().split(":");
		String name = lineStrs[0];
		String constName = NameUtils.getConstName(lineStrs[0]);//常量名
		String packageName = "";
		if(lineStrs.length > 1){
			packageName = lineStrs[1].trim();//包名
		}else{
			throw new RuntimeException("第" + lineNum + "行数据没有配置包名：" + lineContent);
		}
		
		String comments = "";
		if(lineStrs.length > 2){
			comments = lineStrs[2];//注释
		}else{
			comments = constName;
		}
		return new MessageCodeConf(name, constName, packageName, comments, codeHex);
	}

}
