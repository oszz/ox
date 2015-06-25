package org.oszz.ox.core.data;


/**
 * 模块的数据管理映射类
 * @author ZZ
 *
 */
public interface IModuleDataManagerClassMapping {

	/**
	 * 初始化
	 * @author ZZ
	 */
	public void init();

	/**
	 * 将模块的数据管理类放入列表
	 * @author ZZ
	 * @param dataManagerClazz 模块的数据管理类
	 */
	public void put(Class<? extends IHumanDataManager> dataManagerClazz);
}
