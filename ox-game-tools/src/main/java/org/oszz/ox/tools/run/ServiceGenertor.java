package org.oszz.ox.tools.run;

import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.tools.module.conf.ModuleCoifig;
import org.oszz.ox.tools.service.IServiceGenertor;
import org.oszz.ox.tools.service.conf.ServiceConfig;
import org.oszz.ox.tools.service.java.JavaModuleServiceClassMappingGenertor;
import org.oszz.ox.tools.service.java.JavaModuleServiceGenertor;

public class ServiceGenertor {
	/**
	 * module的配置
	 */
	private static final String MODULE_CONFIG_FILE_PAHT = "module.properties";
	
	/**
	 * service的配置
	 */
	private static final String CONIG_FILE_PAHT = "service/service.properties";
	/**
	 * 生成serivce类的模板
	 */
	private static final String  SERVICE_JAVA_VM_FILE = "service/vm/serviceForJava.vm";

	/**
	 * 生成serivce类映射的模板
	 */
	private static final String  SERVICE_CLASS_MAPPING_JAVA_VM_FILE = "service/vm/serivceClassMappingForJava.vm";
	
	
	public static void main(String[] args) {
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(CONIG_FILE_PAHT);
		
		//加载配置
		ServiceConfig serviceConfig = lpf.load(confProps, ServiceConfig.class);
		
		Properties moduleConfigProps = lpf.load(MODULE_CONFIG_FILE_PAHT);
		ModuleCoifig moduleConfig = new ModuleCoifig();
//		moduleConfig.load(moduleConfigProps);
		
		generatorJava(serviceConfig, moduleConfig);
	}
	
	private static void generatorJava(ServiceConfig serviceConfig, ModuleCoifig moduleConfig) {
		IServiceGenertor javaModuleServiceGenertor = new JavaModuleServiceGenertor(serviceConfig, moduleConfig, SERVICE_JAVA_VM_FILE);
		javaModuleServiceGenertor.generate();
		
		IServiceGenertor javaModuleServiceClassMappingGenertor = new JavaModuleServiceClassMappingGenertor(serviceConfig, moduleConfig, SERVICE_CLASS_MAPPING_JAVA_VM_FILE);
		javaModuleServiceClassMappingGenertor.generate();
	}
}
