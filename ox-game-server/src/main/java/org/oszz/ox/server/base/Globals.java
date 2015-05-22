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
		
		ServerConfig sc = lpf.load(confProps, ServerConfig.class);
		LoggerWritor.info(GameLogger.SYSTEM, "load config:" + sc);
		JettyServerConfig jsc  = lpf.load(confProps, JettyServerConfig.class);
		LoggerWritor.info(GameLogger.SYSTEM, "load config:" + jsc);
		MinaServerConfig msc = lpf.load(confProps, MinaServerConfig.class);
		LoggerWritor.info(GameLogger.SYSTEM, "load config:" + msc);
		DBConfig dbc = lpf.load(confProps, DBConfig.class);
		LoggerWritor.info(GameLogger.SYSTEM, "load config:" + dbc);
		
		configs.put(ServerConfig.class, sc);
		configs.put(JettyServerConfig.class, jsc);
		configs.put(MinaServerConfig.class, msc);
		configs.put(DBConfig.class, dbc);
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
