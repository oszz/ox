package org.oszz.ox.tools.conf;

import org.oszz.ox.common.utils.ClassUtils;

/**
 * 游戏工具的相关配置
 * @author ZZ
 *
 */
public class Config {

	private String javaOutputPath;
	private String charsetName = "UTF-8";

	private String protocPath;//=E:/program/protoc-2.5.0-win32/protoc.exe
	private String protoBufFilePath;//=proto

	private String excelPath;//=E:/file/github/ox/ox-resource/I18N/zh_CN
			 	
	private int dataSheetIndex=0;//数据sheet的索引位置
				
	private int typeRowIndex=1;//类型所在的行索引
	private int nameRowIndex=2;//名字所在的行索引
	private int descRowIndex=3;//描述所在的行索引
	private int dataStartRowIndex=4;//数据开始 的行索引
	
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
	public String getProtocPath() {
		return protocPath;
	}
	public void setProtocPath(String protocPath) {
		this.protocPath = protocPath;
	}
	public String getProtoBufFilePath() {
		return protoBufFilePath;
	}
	public void setProtoBufFilePath(String protoBufFilePath) {
		this.protoBufFilePath = protoBufFilePath;
	}
	public String getExcelPath() {
		return excelPath;
	}
	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
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
	
	@Override
	public String toString() {
		return ClassUtils.toString(this);
	}
}
