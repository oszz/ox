package org.oszz.ox.common.utils;

import java.util.Collection;

/**
 * 集合类的工具类
 * @author ZZ
 *
 */
public class CollectionsUtils {

	/**
	 * 判断一个集合类是否为空<br>
	 * @author ZZ
	 * @param collection
	 * @return 如果该集合为Null或集合中没有元素，则返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public static <T> boolean isNull(Collection<T> collection){
		boolean isNull = true;
		if (collection != null && collection.size() != 0) {
			isNull = false;
		}
		return isNull;
	}
	
	/**
	 * 判断一个数组是否为空<br>
	 * @author ZZ
	 * @param collection
	 * @return 如果该数组为Null或数组中没有元素，则返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public static <T> boolean isNull(Object[] objectArr){
		boolean isNull = true;
		if (objectArr != null && objectArr.length != 0) {
			isNull = false;
		}
		return isNull;
	}
}
