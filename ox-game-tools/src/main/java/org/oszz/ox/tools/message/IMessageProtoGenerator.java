package org.oszz.ox.tools.message;

import java.io.File;

import org.oszz.ox.tools.generator.IGenerator;
import org.oszz.ox.tools.generator.IGeneratorPathManager;

public interface IMessageProtoGenerator extends IGenerator, IGeneratorPathManager{

	/**
	 * 返回所有的proto文件
	 * @author ZZ
	 * @return
	 */
	public File[] getProtoFiles();
}
