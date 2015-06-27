package org.oszz.ox.server.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.core.message.IMessageCodeMapping;
import org.oszz.ox.core.service.AllModuleService;
import org.oszz.ox.core.service.IAllModuleService;
import org.oszz.ox.core.service.IModuleServiceClassMapping;
import org.oszz.ox.core.service.IService;
import org.oszz.ox.core.template.ITemplateDataClassMapping;
import org.oszz.ox.core.template.ITemplateService;
import org.oszz.ox.core.template.TemplateService;
import org.oszz.ox.server.base.cache.CacheService;
import org.oszz.ox.server.base.conf.DBConfig;
import org.oszz.ox.server.base.conf.JettyServerConfig;
import org.oszz.ox.server.base.conf.RedisConfig;
import org.oszz.ox.server.base.conf.ServerConfig;
import org.oszz.ox.server.base.conf.TemplateConfig;
import org.oszz.ox.server.base.log.GameLogger;
import org.oszz.ox.server.base.mapping.MessageCodeMapping;
import org.oszz.ox.server.base.mapping.ModuleServiceClassMapping;
import org.oszz.ox.server.base.mapping.TemplateDataClassMapping;
import org.oszz.ox.server.base.processer.ProcesserService;

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
	 * 配置类的集合
	 */
	private static Map<Class<? extends IService>, IService> services;
	
	/**
	 * 配置类的数组
	 */
	private static Class<?>[] configClasses = new Class<?>[]{
			ServerConfig.class, 
			JettyServerConfig.class,
			DBConfig.class,
			RedisConfig.class,
			TemplateConfig.class
			}; 
	
	/**
	 * 初始化
	 * @author ZZ
	 */
	public static void init(String configFilePath){
		//保证顺序
		initConfig(configFilePath);
		initMapping();
		initSystemService();
		initModuleService();
		
		//startService在最后
		startService();
	}
	
	/**
	 * 初始化配置
	 * @author ZZ
	 * @param configFilePath 配置文件的路径
	 */
	private static void initConfig(String configFilePath){
		GameLogger.SYSTEM.info("init config starting.");
		configs = new HashMap<Class<?>, Object>();
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(configFilePath);
		GameLogger.SYSTEM.info("read config file:{}", configFilePath);
		
		for(Class<?> clazz : configClasses){
			Object configObj = lpf.load(confProps, clazz);
			configs.put(clazz, configObj);
			GameLogger.SYSTEM.info("load config:{}", configObj);
		}
		GameLogger.SYSTEM.info("init config end.");
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
	
	/**
	 * 初始化映射关系<br>
	 * 1.初始化消息code的映射关系<br>
	 * 2.初始化模板数据的映射关系
	 * @author ZZ
	 */
	private static void initMapping(){
		IMessageCodeMapping msgCodeMapping = new MessageCodeMapping();
		msgCodeMapping.init();//初始化消息code的映射关系
		
		ITemplateDataClassMapping tempDataMapping = new TemplateDataClassMapping();
		tempDataMapping.init();//初始化模板数据的映射关系
		
		IModuleServiceClassMapping moduleServiceClassMapping = new ModuleServiceClassMapping();
		moduleServiceClassMapping.init();//初始化模块service的映射关系
	}
	
	/**
	 * 初始化系统服务
	 * @author ZZ
	 */
	private static void initSystemService(){
		ServerConfig serverConfig = getCofing(ServerConfig.class);
		TemplateConfig templateConfig = getCofing(TemplateConfig.class);
		
		services = new HashMap<Class<? extends IService>, IService>();
		
		
		ProcesserService processerService = new ProcesserService(serverConfig.getAsynThreadSize(), serverConfig.getSceneNum());
		services.put(ProcesserService.class, processerService);
		
		IService cacheService = new CacheService();
		services.put(CacheService.class, cacheService);
		
		String language = serverConfig.getLanguage();
		String dirPath = templateConfig.getDirPath();
		dirPath = dirPath + SystemProperty.FILE_SEPARATOR.getValue() + language + SystemProperty.FILE_SEPARATOR.getValue();
		ITemplateService tempService = new TemplateService(dirPath, templateConfig);
		services.put(TemplateService.class, tempService);
	}
	
	/**
	 * 初始化模块的服务
	 * @author ZZ
	 */
	private static void initModuleService(){
		IAllModuleService allModuleService = new AllModuleService();
		//create 、 init 必须保证顺序
		allModuleService.create();
		allModuleService.init();
		Map<Class<? extends IService>, IService> allServices = allModuleService.getAllService();
		services.putAll(allServices);
	}
	
	private static void startService(){
		for(Map.Entry<Class<? extends IService>, IService> serviceEntry : services.entrySet()){
			IService service = serviceEntry.getValue();
			if(service.create()){
				 service.init();
			}else{
				GameLogger.SYSTEM.error("service初始化失败: {} ,", service);
				throw new RuntimeException("service初始化失败: " + service);
			}
		}
		
		for(Map.Entry<Class<? extends IService>, IService> serviceEntry : services.entrySet()){
			IService service = serviceEntry.getValue();
			service.onInitialized();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IService> T getService(Class<T> clazz){
		return (T)services.get(clazz);
	}

}
