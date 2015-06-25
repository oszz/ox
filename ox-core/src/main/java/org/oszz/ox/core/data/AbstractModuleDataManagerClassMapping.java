package org.oszz.ox.core.data;

public abstract class AbstractModuleDataManagerClassMapping implements
		IModuleDataManagerClassMapping {

	@Override
	public void put(Class<? extends IHumanDataManager> dataManagerClazz) {
		ModuleDataManagerClassHolder.getInstance().put(dataManagerClazz);
	}

}
