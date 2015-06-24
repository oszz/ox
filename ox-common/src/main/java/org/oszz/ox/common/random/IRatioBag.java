package org.oszz.ox.common.random;

import java.util.List;

/**
 * 随机包
 * @author ZZ
 *
 */
public interface IRatioBag<T> {
	
	
	/**
	 * 返回所有的box
	 * @author ZZ
	 * @return 返回所有的box
	 */
	public List<ItemRatioBox<T>> getAllRatioBoxes();
	

	/**
	 * 根据比率，随机一个box出来
	 * @author ZZ
	 * @return 返回一个随机的box
	 */
	public ItemRatioBox<T> randomOne();
	
	/**
	 * 返回多个随机但不重复的box
	 * @author ZZ
	 * @param count 随机个数
	 * @return 返回多个随机但不重复的box
	 */
	public List<ItemRatioBox<T>> randomNonRepeatMultiple(int count);
	
	/**
	 * 返回多个随机但可重复的box
	 * @author ZZ
	 * @param count 随机个数
	 * @return 返回多个随机但可重复的box
	 */
	public List<ItemRatioBox<T>> randomCanRepeatMultiple(int count);
	
	
	/**
	 * 返回多个随机但不重复box中的对象
	 * @author ZZ
	 * @param count 随机个数
	 * @return 返回多个随机但不重复box中的对象
	 */
	public List<T> randomNonRepeatMultiple2Class(int count);
	
	/**
	 * 返回多个随机但可重复box中的对象
	 * @author ZZ
	 * @param count 随机个数
	 * @return 返回多个随机但可重复box中的对象
	 */
	public List<T> randomCanRepeatMultiple2Class(int count);
	
	/**
	 * 根据比率，随机一个T
	 * @author ZZ
	 * @return 返回一个随机的对象
	 */
	public T randomOne2Class();
}
