package org.oszz.ox.tools.template.conf;

import java.util.List;

/**
 * 模板数据的配置
 * @author ZZ
 *
 */
public class TemplateDataConfig {

	private String excelName;//"BattleBuff.xlsx" 
	private String classAllName;//"org.test.BattleBuff" 
	private boolean isGenerator;//"true"
	private String comments;//注释
	private List<TemplateField> tempFields;
	
	public TemplateDataConfig(String excelName, String classAllName, boolean isGenerator, String comments){
		this.excelName = excelName;
		this.classAllName = classAllName;
		this.isGenerator = isGenerator;
		this.comments = comments;
		
	}
	
	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public String getClassAllName() {
		return classAllName;
	}
	public void setClassAllName(String classAllName) {
		this.classAllName = classAllName;
	}
	public boolean isGenerator() {
		return isGenerator;
	}
	public void setGenerator(boolean isGenerator) {
		this.isGenerator = isGenerator;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<TemplateField> getTempFields() {
		return tempFields;
	}

	public void setTempFields(List<TemplateField> tempFields) {
		this.tempFields = tempFields;
	}
}
