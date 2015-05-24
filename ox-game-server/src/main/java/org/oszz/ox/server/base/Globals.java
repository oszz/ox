package org.oszz.ox.server.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.common.log.LoggerWritor;
import org.oszz.ox.server.base.conf.DBConfig;
import org.oszz.ox.server.base.conf.JettyServerConfig;
import org.oszz.ox.server.base.conf.MinaServerConfig;
import org.oszz.ox.server.base.conf.ServerConfig;
import org.oszz.ox.server.base.log.GameLogger;

/**
 * 这里的方法都是静态的
 * @author ZZ
 *
 */
public class Globals {
	
	/**
	 * 配置类的集合
	 */
	private static Map<Class<?>, Object> configs;
	
	/**
	 * 配置类的数组
	 */
	private static Class<?>[] configClasses = new Class<?>[]{
			ServerConfig.class, 
			JettyServerConfig.class,
			MinaServerConfig.class, 
			DBConfig.class}; 
	
	/**
	 * 初始化
	 * @author ZZ
	 */
	public static void init(String configFilePath){
		initConfig(configFilePath);
	}
	
	/**
	 * 初始化配置
	 * @author ZZ
	 * @param configFilePath 配置文件的路径
	 */
	private static void initConfig(String configFilePath){
		LoggerWritor.info(GameLogger.SYSTEM, "init config starting.");
		configs = new HashMap<Class<?>, Object>();
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(configFilePath);
		LoggerWritor.info(GameLogger.SYSTEM, "read config file:" + configFilePath);
		
		for(Class<?> clazz : configClasses){
			Object configObj = lpf.load(confProps, clazz);
			configs.put(clazz, configObj);
			LoggerWritor.info(GameLogger.SYSTEM, "load config:" + configObj);
		}
		LoggerWritor.info(GameLogger.SYSTEM, "init config end.");
	}
	

	/**
	 * 返回配置类
	 * @author ZZ
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getCofing(Class<T> clazz) {
		return (T)configs.get(clazz);
	}

}
