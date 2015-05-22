package org.oszz.ox.common.conf;

import java.util.Properties;

/**
 * 加载Properties文件
 * @author ZZ
 *
 * @param <T>
 */
public interface ILoadPropertiesFile extends ILoadConfigFile {

	/**
	 * 加载Properties文件
	 * @author ZZ
	 * @param properties 文件
	 * @param clazz 类
	 * @return 返回文件对应的类对象
	 */
	public <T> T load(Properties properties, Class<T> clazz);
	
	/**
	 * 根据配置文件路径，加载并返回 Properties
	 * @author ZZ
	 * @param filePath 配置文件路径
	 * @return 返回 Properties
	 */
	public Properties load(String filePath);
}
