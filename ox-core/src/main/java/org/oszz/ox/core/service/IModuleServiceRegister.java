package org.oszz.ox.core.service;

public interface IModuleServiceRegister {
	
	public void init();

	public void regist(Class<? extends IModuleService> moduleServiceClazz);
}
