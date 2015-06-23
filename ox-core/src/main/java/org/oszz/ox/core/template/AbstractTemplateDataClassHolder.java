package org.oszz.ox.core.template;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTemplateDataClassHolder implements
		ITemplateDataClassHolder {
	
	private List<Class<? extends ITemplateData>> allTempDataClasses;
	public AbstractTemplateDataClassHolder(){
		allTempDataClasses = new ArrayList<Class<? extends ITemplateData>>();
	}

	@Override
	public void put(Class<? extends ITemplateData> tempDataClazz) {
		allTempDataClasses.add(tempDataClazz);
	}

	@Override
	public List<Class<? extends ITemplateData>> getAllTemplateClass() {
		return allTempDataClasses;
	}

}
