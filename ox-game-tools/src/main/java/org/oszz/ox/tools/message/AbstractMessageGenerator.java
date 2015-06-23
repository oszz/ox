package org.oszz.ox.tools.message;

import java.io.File;

import org.oszz.ox.tools.generator.AbstractGenerator;
import org.oszz.ox.tools.message.conf.MessageConfig;

/**
 * Proto类文件的生成器
 * @author ZZ
 *
 */
public abstract class AbstractMessageGenerator extends AbstractGenerator implements IMessageGenerator {
	
	protected MessageConfig msgConfig;
	
	public AbstractMessageGenerator(MessageConfig msgConfig){
		this.msgConfig = msgConfig;
	}

	@Override
	public File[] getProtoFiles() {
		String protoFileDirStr = this.getAbsoluteInputPath(msgConfig.getInputPath());
		File protoFileDir = new File(protoFileDirStr);
		File[] protoFiles = null;
		if(protoFileDir.isDirectory()){
			protoFiles = protoFileDir.listFiles();
		}
		return protoFiles;
	}
	
	
}
