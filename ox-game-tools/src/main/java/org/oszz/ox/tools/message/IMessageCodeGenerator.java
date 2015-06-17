package org.oszz.ox.tools.message;


/**
 * MessageCode相关的类文件生成器接口
 * @author ZZ
 *
 */
public interface IMessageCodeGenerator extends IMessageGenerator {
	
	/**
	 * 返回完整的类名（带后缀名 .class）
	 * @author ZZ
	 * @param packageName 完整包名
	 * @param className 类名
	 * @return
	 */
	public String getFullClassName(String packageName, String className);
	
	/**
	 * 返回完整的类名（不带后缀名 .class）
	 * @author ZZ
	 * @param packageName 完整包名
	 * @param className 类名
	 * @return
	 */
	public String getFullName(String packageName, String className);
}
