package org.oszz.ox.core.data;

/**
 * 抽象的模块数据管理映射类
 * @author ZZ
 *
 */
public abstract class AbstractModuleDataManagerClassMapping implements
		IModuleDataManagerClassMapping {

	@Override
	public void put(Class<? extends IHumanDataManager> dataManagerClazz) {
		ModuleDataManagerClassHolder.getInstance().put(dataManagerClazz);
	}

}
