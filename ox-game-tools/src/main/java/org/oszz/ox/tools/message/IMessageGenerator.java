package org.oszz.ox.tools.message;

import org.oszz.ox.tools.generator.IGenerator;
import org.oszz.ox.tools.generator.IGeneratorPathManager;

/**
 * 消息生成器
 * @author ZZ
 *
 */
public interface IMessageGenerator extends IGenerator, IGeneratorPathManager{

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
