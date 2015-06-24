package org.oszz.ox.core.template;

import java.util.ArrayList;
import java.util.List;

public final class TemplateDataClassHolder implements
		ITemplateDataClassHolder {
	
	private List<Class<? extends ITemplateData>> allTempDataClasses;
	private TemplateDataClassHolder(){
		allTempDataClasses = new ArrayList<Class<? extends ITemplateData>>();
	}
	
	public static TemplateDataClassHolder getInstance(){
		return InnerClass.instance;
	}

	@Override
	public void put(Class<? extends ITemplateData> tempDataClazz) {
		allTempDataClasses.add(tempDataClazz);
	}

	@Override
	public List<Class<? extends ITemplateData>> getAllTemplateClass() {
		return allTempDataClasses;
	}
	
	private static class InnerClass {
		public static final TemplateDataClassHolder instance = new TemplateDataClassHolder();
	}

}
