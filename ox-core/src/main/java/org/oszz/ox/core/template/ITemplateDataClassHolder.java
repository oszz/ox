package org.oszz.ox.core.template;

import java.util.List;

public interface ITemplateDataClassHolder {
	
	public void init();

	public void put(Class<? extends ITemplateData> tempDataClazz);
	
	public List<Class<? extends ITemplateData>> getAllTemplateClass();
}
