package org.oszz.ox.core.data;

import java.util.List;

import org.oszz.ox.core.holder.ClassHodler;
import org.oszz.ox.core.holder.IHodler;

/**
 * 模块的数据管理类的持有者（单例类）
 * @author ZZ
 *
 */
public final class ModuleDataManagerClassHolder {
	
	/**
	 * 模块的数据管理类的存放
	 */
	private IHodler<Class<? extends IHumanDataManager>> hodler;
	
	private ModuleDataManagerClassHolder(){
		hodler = new ClassHodler<Class<? extends IHumanDataManager>>();
	}
	
	/**
	 * 返回模块的数据管理类的持有者的单实例
	 * @author ZZ
	 * @return 返回模块的数据管理类的持有者的单实例
	 */
	public static ModuleDataManagerClassHolder getInstance(){
		return InnerClass.instance;
	}

	/**
	 * 将模块的数据管理类放入列表
	 * @author ZZ
	 * @param dataManagerClazz 模块的数据管理类
	 */
	public void put(Class<? extends IHumanDataManager> dataManagerClazz) {
		hodler.put(dataManagerClazz);
	}

	/**
	 * 返回所有的模块数据管理类
	 * @author ZZ
	 * @return 返回所有的模块数据管理类
	 */
	public List<Class<? extends IHumanDataManager>> getAllClasses() {
		return hodler.getAllClasses();
	}
	
	private static class InnerClass {
		public static final ModuleDataManagerClassHolder instance = new ModuleDataManagerClassHolder();
	}

}
