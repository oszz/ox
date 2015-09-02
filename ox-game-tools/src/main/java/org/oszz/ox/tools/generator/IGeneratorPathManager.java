package org.oszz.ox.tools.generator;

/**
 * 生成代码文件的路径管理
 * 
 * @author ZZ
 *
 */
public interface IGeneratorPathManager {

	/**
	 * 返回输入文件的绝对目录名
	 * 
	 * @author ZZ
	 * @return
	 */
	public String getAbsoluteInputPath(String inputPath);

	/**
	 * 返回生成文件的绝对目录名
	 * 
	 * @author ZZ
	 * @return
	 */
	public String getAbsoluteJavaOutputPath(String javaOutputPath);

	/**
	 * 返回生成文件的绝对目录名
	 * 
	 * @author ZZ
	 * @return
	 */
	public String getAbsoluteOutputPath(String outputPath, String packageName);
}
