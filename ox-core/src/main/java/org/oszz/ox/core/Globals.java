package org.oszz.ox.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.core.conf.BaseConfig;
import org.oszz.ox.core.data.IHumanDataManager;
import org.oszz.ox.core.message.MessageCodeMapping;
import org.oszz.ox.core.regist.IHumanDataManagerRegister;
import org.oszz.ox.core.regist.IMessageCodeRegister;
import org.oszz.ox.core.regist.IModuleServiceRegister;
import org.oszz.ox.core.regist.ITemplateDataRegister;
import org.oszz.ox.core.service.IService;
import org.oszz.ox.core.service.ISystemService;
import org.oszz.ox.core.template.ITemplateData;

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
	private static Map<Short, MessageCodeMapping> messageCodeMappings;
	private static List<Class<? extends ITemplateData>> templateDatClassList;
	private static List<Class<? extends IHumanDataManager>> humanDataManagerClassList;
	
	private static IModuleServiceRegister moduleServiceRegister;
	private static IMessageCodeRegister messageCodeRegister;
	private static ITemplateDataRegister templateDataRegister;
	private static IHumanDataManagerRegister humanDataManagerRegister;
	
	
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
	
	public static void setMessageCodeRegister(IMessageCodeRegister msgCodeRegister){
		messageCodeRegister = msgCodeRegister;
	}
	public static void setTemplateDataRegister(ITemplateDataRegister tempDataRegister){
		templateDataRegister = tempDataRegister;
	}
	
	public static void addMessageCodeMapping(MessageCodeMapping messageCodeMapping){
		messageCodeMappings.put(messageCodeMapping.getMsgCode(), messageCodeMapping);
	}
	
	public static void addTemplateDataClass(Class<? extends ITemplateData> tempDataClass){
		templateDatClassList.add(tempDataClass);
	}
	
	public static void addAllTemplateDataClasses(List<Class<? extends ITemplateData>> tempDataClasses){
		templateDatClassList.addAll(tempDataClasses);
	}
	
	public static List<Class<? extends ITemplateData>> getAllTemplateDataClasses(){
		return templateDatClassList;
	}
	
	public static void setHumanDataManagerRegister(IHumanDataManagerRegister humanManagerRegister){
		humanDataManagerRegister = humanManagerRegister;
	}
	
	public static void addHumanDataManagerClass(Class<? extends IHumanDataManager> humanDataManagerClass){
		humanDataManagerClassList.add(humanDataManagerClass);
	}
	
	public static void addAllHumanDataManagerClasses(List<Class<? extends IHumanDataManager>> humanDataManagerClasses){
		humanDataManagerClassList.addAll(humanDataManagerClasses);
	}
	
	public static List<Class<? extends IHumanDataManager>> getAllHumanDataManagerClasses(){
		return humanDataManagerClassList;
	}
	
	public static void create(){
		configClassList = new ArrayList<Class<? extends BaseConfig>>();
		serviceClassList = new ArrayList<IService>();
		configs = new HashMap<Class<? extends BaseConfig>, BaseConfig>();
		services = new HashMap<Class<? extends IService>, IService>();
		messageCodeMappings = new HashMap<Short, MessageCodeMapping>();
		templateDatClassList = new ArrayList<Class<? extends ITemplateData>>();
		humanDataManagerClassList = new ArrayList<Class<? extends IHumanDataManager>>();
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
	
	public static void init(){
		initRegister();
		initService();
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
	private static void initService(){
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
	
	/**
	 * 初始化注册的
	 * @author ZZ
	 */
	private static void initRegister(){
		moduleServiceRegister.init();
		messageCodeRegister.init();
		templateDataRegister.init();
		humanDataManagerRegister.init();
	}
	
	/**
	 * 返回消息编码的映射类
	 * @author ZZ
	 * @param clazz
	 * @return
	 */
	public static MessageCodeMapping getMessageCodeMapping(short msgCode) {
		return messageCodeMappings.get(msgCode);
	}
}
