package org.oszz.ox.tools.generator;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FileUtils;
import org.oszz.ox.common.utils.SystemProperty;

public abstract class AbstractGenerator implements IGenerator {

	@Override
	public String getAbsoluteInputPath(String inputPath) {
		return FileUtils.getAbsolutePathForRead(inputPath);
	}
	
	@Override
	public String getAbsoluteJavaOutputPath(String javaOutputPath) {
		return FileUtils.getAbsolutePathForWrite(javaOutputPath);
	}
	
	@Override
	public String getAbsoluteOutputPath(String outputPath, String packageName) {
		String packagePath = ClassUtils.packageName2Path(packageName);
		outputPath += SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
		outputPath = FileUtils.getDirIfExists(outputPath) + SystemProperty.FILE_SEPARATOR.getValue();
		return outputPath;
	}	
}
