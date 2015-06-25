package org.oszz.ox.tools.template.conf;

public class TemplateDataClassHolderConfig {

	private String name;//完整类路径名  例：org.ox.dd.Temp.class
	private String comments;//完整类路径名  例：org.ox.dd.Temp.class
	
	public TemplateDataClassHolderConfig(String clazzName,String comments){
		this.name = clazzName;
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
