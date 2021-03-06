package org.oszz.ox.tools.template.conf;

import org.oszz.ox.common.utils.NameUtils;

public class TemplateField {

	private String type;//字段类型
	private String name;//字段名字
	private String upperName;//首字母大写
	private String desc;
	
	public TemplateField(String name){
		this.name = name;
		this.upperName = NameUtils.upperFirstChar(name);
	}
	public TemplateField(String type, String name){
		this.type = type;
		this.name = name;
		this.upperName = NameUtils.upperFirstChar(name);
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUpperName() {
		return upperName;
	}
	public void setUpperName(String upperName) {
		this.upperName = upperName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
//		System.out.println(SystemProperty.LINE_SEPARATOR.getValue());
//		System.out.println(SystemProperty.LINE_SEPARATOR.getValue() + SystemProperty.TABLE_CHAR.getValue() + "*");
//		System.out.println(desc.indexOf("\n"));
		this.desc = desc.replace("\n", "<br>\n\t * ");;
	}
}
