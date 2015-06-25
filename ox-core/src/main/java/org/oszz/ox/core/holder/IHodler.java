package org.oszz.ox.core.holder;

import java.util.List;

/**
 * 类的持有者接口
 * @author ZZ
 *
 */
public interface IHodler<T> {

	public void put(T clazz);
	
	public List<T> getAllClasses();
}
