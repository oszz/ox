package org.oszz.ox.common.utils;

/**
 * 
 * @author ZZ
 *
 */
public class NameUtils {

	/**
	 * 获得常量名，例：CONST_NAME_EXAMPLE
	 * @author ZZ
	 * @param str
	 * @return
	 */
	public static String getConstName(String str){
		StringBuilder strBuilder = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			if (Character.isUpperCase(c)){//如果是大写
				if(i != 0){//第一个字符，不加下划线
					String underLineChar = SystemProperty.UNDERLINE_CHAR.getValue();
					strBuilder.append(underLineChar);
				}
				
				strBuilder.append(c);
			}else{
				strBuilder.append(upper(c+""));
			}
		}
		return strBuilder.toString();
	}
	
	/**
	 * 大写
	 * @author ZZ
	 * @param str
	 * @return
	 */
	public static String upper(String str){
		return str.toUpperCase();
	}
	
	/**
	 * 返回方法名或参数名(首字母小写)
	 * @author ZZ
	 * @param str
	 * @return
	 */
	public static String getMethodOrParaName(String str){
		str = str.substring(0, 1).toLowerCase() + str.substring(1); //首字母小写
		return str;
	}
	
	/**
	 * 驼峰式的类名
	 * @author ZZ
	 * @param words 单词数组
	 * @return
	 */
	public static String getClassName(String word){
		return getClassName(new String[]{word});
	}
	
	/**
	 * 驼峰式的类名
	 * @author ZZ
	 * @param words 单词数组
	 * @return
	 */
	public static String getClassName(String[] words){
		String className = "";
		if(words != null && words.length != 0){
			for(String word : words){
				className += word.substring(0, 1).toUpperCase() + word.substring(1);
			}
		}
		return className;
	}
	
	/**
	 * 返回首字母大写
	 * @author ZZ
	 * @param str
	 * @return
	 */
	public static String upperFirstChar(String str){
		str = str.substring(0, 1).toUpperCase() + str.substring(1); //首字母大写
		return str;
	}
	
}
