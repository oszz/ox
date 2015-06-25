package org.oszz.ox.core.data;

import java.util.List;

import org.oszz.ox.core.holder.ClassHodler;
import org.oszz.ox.core.holder.IHodler;

public final class ModuleDataManagerClassHolder {
	
	private IHodler<Class<? extends IHumanDataManager>> hodler;
	
	private ModuleDataManagerClassHolder(){
		hodler = new ClassHodler<Class<? extends IHumanDataManager>>();
	}
	
	public static ModuleDataManagerClassHolder getInstance(){
		return InnerClass.instance;
	}

	public void put(Class<? extends IHumanDataManager> dataManagerClazz) {
		hodler.put(dataManagerClazz);
	}

	public List<Class<? extends IHumanDataManager>> getAllClasses() {
		return hodler.getAllClasses();
	}
	
	private static class InnerClass {
		public static final ModuleDataManagerClassHolder instance = new ModuleDataManagerClassHolder();
	}

}
