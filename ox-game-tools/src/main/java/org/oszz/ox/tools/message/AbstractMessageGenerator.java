package org.oszz.ox.tools.message;

import java.io.File;

import org.oszz.ox.tools.generator.AbstractGenerator;
import org.oszz.ox.tools.module.conf.ModuleConfig;

/**
 * Proto类文件的生成器
 * @author ZZ
 *
 */
public abstract class AbstractMessageGenerator extends AbstractGenerator implements IMessageGenerator {
	
	protected ModuleConfig moduleConfig;
	
	public AbstractMessageGenerator(ModuleConfig moduleConfig){
		this.moduleConfig = moduleConfig;
	}

	@Override
	public File[] getProtoFiles() {
		String protoFileDirStr = this.getAbsoluteInputPath(moduleConfig.getProtoBufFilePath());
		File protoFileDir = new File(protoFileDirStr);
		File[] protoFiles = null;
		if(protoFileDir.isDirectory()){
			protoFiles = protoFileDir.listFiles();
		}
		return protoFiles;
	}
	
	
}
