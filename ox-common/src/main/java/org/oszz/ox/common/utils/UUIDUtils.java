package org.oszz.ox.common.utils;

import java.util.UUID;
/**
 * UUID生成工具
 * @see java.util.UUID
 * @author ZZ
 *
 */
public class UUIDUtils {

	/**
	 * 返回一个UUID字符串(全部大写)
	 * @author ZZ
	 * @return 返回一个UUID字符串
	 */
	public static String builder(){
		String uuid = UUID.randomUUID().toString();
		return uuid.replace(SystemProperty.SHORT_LINE_CHAR.getValue(), SystemProperty.EMPTY_STRING.getValue()).toUpperCase();
	}
}
