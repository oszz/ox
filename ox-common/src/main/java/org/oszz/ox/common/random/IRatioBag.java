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
	 * @return
	 */
	public List<ItemRatioBox<T>> getAllRatioBoxes();
	

	/**
	 * 根据比率，随机一个box出来
	 * @author ZZ
	 * @return 
	 */
	public ItemRatioBox<T> randomOne();
	
	/**
	 * 随机多个不重复的box出来
	 * @author ZZ
	 * @param count box个数
	 * @return
	 */
	public List<ItemRatioBox<T>> randomNonRepeatMultiple(int count);
	
	/**
	 * 随机多个可重复的box出来
	 * @author ZZ
	 * @param count box个数
	 * @return
	 */
	public List<ItemRatioBox<T>> randomCanRepeatMultiple(int count);
	
	
	/**
	 * 随机多个不重复的box出来
	 * @author ZZ
	 * @param count box个数
	 * @return
	 */
	public List<T> randomNonRepeatMultiple2Class(int count);
	
	/**
	 * 随机多个可重复的box出来
	 * @author ZZ
	 * @param count box个数
	 * @return
	 */
	public List<T> randomCanRepeatMultiple2Class(int count);
	
	/**
	 *  根据比率，随机一个T出来
	 * @author ZZ
	 * @param clazz
	 * @return
	 */
	public T randomOne2Class();
}
