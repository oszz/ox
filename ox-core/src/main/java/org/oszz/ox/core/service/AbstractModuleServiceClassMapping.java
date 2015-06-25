package org.oszz.ox.core.service;

public abstract class AbstractModuleServiceClassMapping implements
		IModuleServiceClassMapping {

	@Override
	public void put(Class<? extends IService> serviceClazz) {
		ModuleServiceClassHolder.getInstance().put(serviceClazz);
	}

}
