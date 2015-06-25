package org.oszz.ox.core.holder;

import java.util.List;

/**
 * 类或对象的持有者接口
 * @author ZZ
 *
 */
public interface IHodler<T> {

	/**
	 * 放入对象
	 * @author ZZ
	 * @param t 对象
	 */
	public void put(T t);
	
	/**
	 * 返回所有的对象列表
	 * @author ZZ
	 * @return 返回所有的对象列表
	 */
	public List<T> getAllClasses();
}
