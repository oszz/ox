package org.oszz.ox.core.template;

import java.util.List;

import org.oszz.ox.core.holder.ClassHodler;
import org.oszz.ox.core.holder.IHodler;

public final class TemplateDataClassHolder {
	
	private IHodler<Class<? extends ITemplateData>> hodler;
	private TemplateDataClassHolder(){
		hodler = new ClassHodler<Class<? extends ITemplateData>>();
	}
	
	public static TemplateDataClassHolder getInstance(){
		return InnerClass.instance;
	}

	public void put(Class<? extends ITemplateData> tempDataClazz) {
		hodler.put(tempDataClazz);
	}

	public List<Class<? extends ITemplateData>> getAllTemplateClass() {
		return hodler.getAllClasses();
	}
	
	private static class InnerClass {
		public static final TemplateDataClassHolder instance = new TemplateDataClassHolder();
	}

}
