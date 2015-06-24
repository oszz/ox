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
	
	private int dataSheetIndex;//数据sheet的索引位置

	private int typeRowIndex;//类型所在的行索引
	private int nameRowIndex;//名字所在的行索引
	private int descRowIndex;//描述所在的行索引
	private int dataStartRowIndex;//数据开始 的行索引
	
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
	public int getDataSheetIndex() {
		return dataSheetIndex;
	}
	public void setDataSheetIndex(int dataSheetIndex) {
		this.dataSheetIndex = dataSheetIndex;
	}
	public int getTypeRowIndex() {
		return typeRowIndex;
	}
	public void setTypeRowIndex(int typeRowIndex) {
		this.typeRowIndex = typeRowIndex;
	}
	public int getNameRowIndex() {
		return nameRowIndex;
	}
	public void setNameRowIndex(int nameRowIndex) {
		this.nameRowIndex = nameRowIndex;
	}
	public int getDescRowIndex() {
		return descRowIndex;
	}
	public void setDescRowIndex(int descRowIndex) {
		this.descRowIndex = descRowIndex;
	}
	public int getDataStartRowIndex() {
		return dataStartRowIndex;
	}
	public void setDataStartRowIndex(int dataStartRowIndex) {
		this.dataStartRowIndex = dataStartRowIndex;
	}
}
