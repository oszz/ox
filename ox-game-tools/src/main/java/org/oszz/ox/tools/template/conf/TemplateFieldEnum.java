package org.oszz.ox.tools.template.conf;

public enum TemplateFieldEnum {
	
	/**
	 * int32
	 */
	INT_32("int32", "int"),
	/**
	 * int64
	 */
	INT_64("int64", "long"),
	
	/**
	 * float
	 */
	FLOAT("float", "double"),
	/**
	 * double
	 */
	DOUBLE("double", "double"),
	

	/**
	 * String
	 */
	STRING("String", "String"),
	
	;

	
	private String typeStrFromTemplate;
	private String typeStrForJava;
	
	private TemplateFieldEnum(String typeStrFromTemplate, String typeStrForJava){
		this.typeStrFromTemplate = typeStrFromTemplate;
		this.typeStrForJava = typeStrForJava;
	}
	
	public String getTypeStrFromTemplate() {
		return typeStrFromTemplate;
	}
	public String getTypeStrForJava() {
		return typeStrForJava;
	}

	/**
	 * 获得java的数据类型
	 * @author ZZ
	 * @param typeStrFromTemplate
	 * @return
	 */
	public static String getTypeForJava(String typeStrFromTemplate){
		TemplateFieldEnum[] tfes = TemplateFieldEnum.values();
		String type = null;
		for(TemplateFieldEnum tfe :  tfes){
			if(tfe.getTypeStrFromTemplate().equalsIgnoreCase(typeStrFromTemplate)){
				type = tfe.typeStrForJava;
			}
		}
		return type;
	}
}
