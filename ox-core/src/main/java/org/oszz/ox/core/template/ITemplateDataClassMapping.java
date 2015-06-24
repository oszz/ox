package org.oszz.ox.core.template;


public interface ITemplateDataClassMapping {
	
	public void init();

	public void put(Class<? extends ITemplateData> tempDataClazz);
}
