package org.oszz.ox.tools.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.NameUtils;
import org.oszz.ox.tools.utils.VelocityUtils;

public class JavaMsgCodeGenerator extends AbstractMessageGenerator {
	
	private static final String FILE_NAME = "MessageCode.java";

	private String msgCode_vmFile;
	private String messageCodeListPath;
	public JavaMsgCodeGenerator(MessageConfig msgConfig, String msgCode_vmFile, String messageCodeListPath) {
		super(msgConfig);
		this.msgCode_vmFile = msgCode_vmFile;
		this.messageCodeListPath = messageCodeListPath;
		
	}

	@Override
	public void generate() {
		VelocityContext ctx = new VelocityContext();
		List<String> constNames = readCodeList(this.messageCodeListPath);
		ctx.put("constNames", constNames);
				 
		String outPath = this.getAbsoluteJavaOutputPath();
		VelocityUtils.write(this.msgCode_vmFile, ctx, outPath+"/"+FILE_NAME, msgConfig.getCharsetName());
		System.out.println("生成 " + FILE_NAME + " 成功. 字符集：" +  msgConfig.getCharsetName());
	}

	
	public List<String> readCodeList(String filePath) {
		InputStream in = null;
		BufferedReader br = null;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
			br = new BufferedReader(new InputStreamReader(in, msgConfig.getCharsetName()));
			String lineContent = "";
			List<String> constNames = new ArrayList<String>();
			while((lineContent = br.readLine()) != null){
				if(!lineContent.startsWith("#") && !"".equals(lineContent.trim())){
					constNames.add(NameUtils.getConstName(lineContent.trim()));
				}
			}
			return constNames;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(in != null){try {in.close();} catch (IOException e) {}}
			if(br != null){try {br.close();} catch (IOException e) {}}
		}
	}


}
