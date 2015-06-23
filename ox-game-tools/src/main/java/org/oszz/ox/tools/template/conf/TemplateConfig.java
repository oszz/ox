package org.oszz.ox.tools.template.conf;

/**
 * 模板配置类
 * @author ZZ
 *
 */
public class TemplateConfig {

	private String inputPath;//模板数据的路径
	private String javaOutputPath;//生成java模板类输出的路径
	private String charsetName;//字符集
	
	public String getInputPath() {
		return inputPath;
	}
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	public String getJavaOutputPath() {
		return javaOutputPath;
	}
	public void setJavaOutputPath(String javaOutputPath) {
		this.javaOutputPath = javaOutputPath;
	}
	public String getCharsetName() {
		return charsetName;
	}
	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}
}
