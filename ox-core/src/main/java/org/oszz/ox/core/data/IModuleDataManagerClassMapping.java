package org.oszz.ox.core.data;


public interface IModuleDataManagerClassMapping {

	public void init();

	public void put(Class<? extends IHumanDataManager> dataManagerClazz);
}
