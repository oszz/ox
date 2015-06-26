package org.oszz.ox.tools.generator;

import org.oszz.ox.common.utils.FileUtils;

public abstract class AbstractGenerator implements IGenerator {

	@Override
	public String getAbsoluteInputPath(String inputPath) {
		return FileUtils.getAbsolutePathForRead(inputPath);
	}
	
	@Override
	public String getAbsoluteJavaOutputPath(String javaOutputPath) {
		return FileUtils.getAbsolutePathForWrite(javaOutputPath);
	}
}
