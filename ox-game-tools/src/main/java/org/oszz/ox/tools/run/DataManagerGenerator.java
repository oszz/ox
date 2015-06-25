package org.oszz.ox.tools.run;

import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.tools.dataManager.IModuleDataManagerGenertor;
import org.oszz.ox.tools.dataManager.conf.DataManagerConfig;
import org.oszz.ox.tools.dataManager.java.JavaModuleDataManagerClassMappingGenertor;
import org.oszz.ox.tools.dataManager.java.JavaModuleDataManagerGenertor;
import org.oszz.ox.tools.module.ModuleCoifig;

public class DataManagerGenerator {

	/**
	 * module的配置
	 */
	private static final String MODULE_CONFIG_FILE_PAHT = "module.properties";
	
	/**
	 * dataManager的配置
	 */
	private static final String CONIG_FILE_PAHT = "dataManager/dataManager.properties";
	/**
	 * 生成dataManager类的模板
	 */
	private static final String  DATA_MANAGER_JAVA_VM_FILE = "dataManager/vm/dataManagerForJava.vm";

	/**
	 * 生成dataManager类映射的模板
	 */
	private static final String  DATA_MANAGER_CLASS_MAPPING_JAVA_VM_FILE = "dataManager/vm/dataManagerClassMappingForJava.vm";
	
	
	public static void main(String[] args) {
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(CONIG_FILE_PAHT);
		
		//加载配置
		DataManagerConfig dataManagerConfig = lpf.load(confProps, DataManagerConfig.class);
		
		Properties moduleConfigProps = lpf.load(MODULE_CONFIG_FILE_PAHT);
		ModuleCoifig moduleConfig = new ModuleCoifig();
		moduleConfig.load(moduleConfigProps);
		
		generatorJava(dataManagerConfig, moduleConfig);
	}
	
	private static void generatorJava(DataManagerConfig dataManagerConfig, ModuleCoifig moduleConfig) {
		IModuleDataManagerGenertor javaModuleDataManagerGenertor = new JavaModuleDataManagerGenertor(dataManagerConfig, moduleConfig, DATA_MANAGER_JAVA_VM_FILE);
		javaModuleDataManagerGenertor.generate();
		
		IModuleDataManagerGenertor javaModuleDataManagerClassMappingGenertor = new JavaModuleDataManagerClassMappingGenertor(dataManagerConfig, moduleConfig, DATA_MANAGER_CLASS_MAPPING_JAVA_VM_FILE);
		javaModuleDataManagerClassMappingGenertor.generate();
	}

}
