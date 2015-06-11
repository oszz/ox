package org.oszz.ox.tools.message;

import java.io.File;

import org.oszz.ox.common.utils.FilePathUtils;

/**
 * Proto类文件的生成器
 * @author ZZ
 *
 */
public abstract class AbstractMessageGenerator implements IMessageGenerator {
	
	protected MessageConfig msgConfig;
	
	public AbstractMessageGenerator(MessageConfig msgConfig){
		this.msgConfig = msgConfig;
	}

	@Override
	public String getAbsoluteInputPath() {
		return FilePathUtils.getAbsolutePathForRead(msgConfig.getInputPath());
	}
	
	@Override
	public String getAbsoluteJavaOutputPath() {
		return FilePathUtils.getAbsolutePathForWrite(msgConfig.getJavaOutputPath());
	}
	
	@Override
	public File[] getProtoFiles() {
		String protoFileDirStr = getAbsoluteInputPath();
		File protoFileDir = new File(protoFileDirStr);
		File[] protoFiles = null;
		if(protoFileDir.isDirectory()){
			protoFiles = protoFileDir.listFiles();
		}
		return protoFiles;
	}
	
	
}
