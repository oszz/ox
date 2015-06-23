package org.oszz.ox.tools.generator;

import org.oszz.ox.common.utils.FilePathUtils;

public abstract class AbstractGenerator implements IGenerator {

	@Override
	public String getAbsoluteInputPath(String inputPath) {
		return FilePathUtils.getAbsolutePathForRead(inputPath);
	}
	
	@Override
	public String getAbsoluteJavaOutputPath(String javaOutputPath) {
		return FilePathUtils.getAbsolutePathForWrite(javaOutputPath);
	}
}
