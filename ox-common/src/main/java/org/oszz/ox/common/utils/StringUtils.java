package org.oszz.ox.common.utils;

/**
 * 字符串工具类
 * @author ZZ
 *
 */
public class StringUtils {

	/**
	 * 判断一个字符串是否为空白
	 * @author ZZ
	 * @param str 字符串内容
	 * @return 如果是空白字符串则返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public static boolean isBlank(String str){
		if (str == null)  {
            return true;
        }
        int size = str.length();
        char c;
        for (int i = 0; i < size; i++){
            c = str.charAt(i);
            if (!Character.isWhitespace(c)){
                return false;
            }
        }
        return true;
    }
}
