package org.oszz.ox.common.utils;

import java.text.DecimalFormat;

/**
 * 数学工具
 * @author ZZ
 *
 */
public class MathUtils {

	private static DecimalFormat df = new DecimalFormat("#");//转换成整型
	
	/**
	 * 将scientific notation(科学计数法)的数字转成数字的字符串
	 * @author ZZ
	 * @param num 科学计数法的数字
	 * @return 返回科学计数法数字对应的字符串
	 */
	public static String parseString(double num){
		return df.format(num);
	}
}
