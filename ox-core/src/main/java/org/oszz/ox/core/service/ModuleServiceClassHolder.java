package org.oszz.ox.core.service;

import java.util.List;

import org.oszz.ox.core.holder.ClassHodler;
import org.oszz.ox.core.holder.IHodler;

public final class ModuleServiceClassHolder {
	
	private IHodler<Class<? extends IService>> hodler;
	
	private ModuleServiceClassHolder(){
		hodler = new ClassHodler<Class<? extends IService>>();
	}
	
	public static ModuleServiceClassHolder getInstance(){
		return InnerClass.instance;
	}

	public void put(Class<? extends IService> serviceClazz) {
		hodler.put(serviceClazz);
	}

	public List<Class<? extends IService>> getAllClasses() {
		return hodler.getAllClasses();
	}
	
	private static class InnerClass {
		public static final ModuleServiceClassHolder instance = new ModuleServiceClassHolder();
	}

}
