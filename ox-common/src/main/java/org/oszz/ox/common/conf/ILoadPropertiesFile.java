package org.oszz.ox.common.conf;

import java.util.Properties;

/**
 * 加载Properties文件
 * @author ZZ
 *
 * @param <T>
 */
public interface ILoadPropertiesFile<T> extends ILoadConfigFile<T> {

	/**
	 * 加载Properties文件
	 * @author ZZ
	 * @param properties 文件
	 * @return 返回文件对应的类对象
	 */
	public T load(Properties properties);
}
