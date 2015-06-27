package org.oszz.ox.tools.generator;

public interface IGenerator {
	
	/**
	 * 返回输入文件的绝对目录名
	 * @author ZZ
	 * @return
	 */
	public String getAbsoluteInputPath(String inputPath);
	
	/**
	 * 返回生成文件的绝对目录名
	 * @author ZZ
	 * @return
	 */
	public String getAbsoluteJavaOutputPath(String javaOutputPath);
	
	/**
	 * 返回生成文件的绝对目录名
	 * @author ZZ
	 * @return
	 */
	public String getAbsoluteOutputPath(String outputPath, String packageName);

	public void generate();
}
