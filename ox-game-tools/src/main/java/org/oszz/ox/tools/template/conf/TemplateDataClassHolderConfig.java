package org.oszz.ox.tools.template.conf;

public class TemplateDataClassHolderConfig {

	private String name;//完整类路径名  例：org.ox.dd.Temp.class
	
	public TemplateDataClassHolderConfig(String clazzName){
		this.name = clazzName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
