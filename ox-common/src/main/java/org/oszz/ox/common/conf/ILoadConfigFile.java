package org.oszz.ox.common.conf;

import java.io.File;

/**
 * 加载配置文件的接口
 * @author ZZ
 *
 */
public interface ILoadConfigFile<T> {

	/**
	 * 加载配置文件
	 * @author ZZ
	 * @param filePath 配置文件路径
	 * @return 返回文件对应的类对象
	 */
	public T load(String filePath);
	
	/**
	 * 加载配置文件 
	 * @author ZZ
	 * @param file 配置文件
	 * @return 返回文件对应的类对象
	 */
	public T load(File file);
	
}
