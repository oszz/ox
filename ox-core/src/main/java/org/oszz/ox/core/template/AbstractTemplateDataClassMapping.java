package org.oszz.ox.core.template;


public abstract class AbstractTemplateDataClassMapping implements
		ITemplateDataClassMapping {

	@Override
	public void put(Class<? extends ITemplateData> tempDataClazz) {
		TemplateDataClassHolder.getInstance().put(tempDataClazz);
	}


}
