package org.oszz.ox.core.service;


public interface IModuleServiceClassMapping {

	public void init();

	public void put(Class<? extends IService> serviceClazz);
}
