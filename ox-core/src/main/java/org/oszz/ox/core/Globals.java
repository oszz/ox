package org.oszz.ox.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.core.conf.BaseConfig;
import org.oszz.ox.core.service.IModuleServiceRegister;
import org.oszz.ox.core.service.IService;
import org.oszz.ox.core.service.ISystemService;

/**
 * 这里的方法都是静态的
 * @author ZZ
 *
 */
public class Globals {
	
	/**
	 * 配置类的集合
	 */
	private static Map<Class<? extends BaseConfig>, BaseConfig> configs;
	
	/**
	 * 配置类的集合
	 */
	private static Map<Class<? extends IService>, IService> services;
	
	/**
	 * 配置类的列表
	 */
	private static List<Class<? extends BaseConfig>> configClassList;
	
	/**
	 * service类的列表
	 */
	private static List<IService> serviceClassList;
	
	private static IModuleServiceRegister moduleServiceRegister;
	
	public static void addConfigClass(Class<? extends BaseConfig> configClass){
		configClassList.add(configClass);
	}
	
	public static void addAllConfigClasses(List<Class<? extends BaseConfig>> configClasses){
		configClassList.addAll(configClasses);
	}
	
	public static void addService(IService service){
		serviceClassList.add(service);
	}
	
	public static void addAllService(List<IService> services){
		serviceClassList.addAll(services);
	}
	
	public static void setModuleServiceRegister(IModuleServiceRegister serviceRegister){
		moduleServiceRegister = serviceRegister;
	}
	
	public static void create(){
		configClassList = new ArrayList<Class<? extends BaseConfig>>();
		serviceClassList = new ArrayList<IService>();
		configs = new HashMap<Class<? extends BaseConfig>, BaseConfig>();
		services = new HashMap<Class<? extends IService>, IService>();
	}
	
	
	/**
	 * 初始化配置
	 * @author ZZ
	 * @param configFilePath 配置文件的路径
	 */
	public static void initConfig(String configFilePath){
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(configFilePath);
		for(Class<? extends BaseConfig> clazz : configClassList){
			BaseConfig configObj = lpf.load(confProps, clazz);
			configs.put(clazz, configObj);
		}
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
	 * 初始化服务
	 * @author ZZ
	 */
	public static void initService(){
		moduleServiceRegister.init();
		for(IService service : serviceClassList){
			if(service.create() && service.init()){
				service.onInitialized();
				if(service instanceof ISystemService){//如果是系统服务就启动
					((ISystemService)service).start();
				}
				services.put(service.getClass(), service);
				//TODO log 某某服务初始化成功
			}else{
				throw new RuntimeException("service初始化失败: " + service);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IService> T getService(Class<T> clazz){
		return (T)services.get(clazz);
	}

}
