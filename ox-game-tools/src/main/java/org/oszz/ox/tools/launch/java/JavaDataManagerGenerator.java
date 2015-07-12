package org.oszz.ox.tools.launch.java;

import org.oszz.ox.tools.dataManager.IModuleDataManagerGenertor;
import org.oszz.ox.tools.dataManager.java.JavaModuleDataManagerRegisterGenertor;
import org.oszz.ox.tools.dataManager.java.JavaModuleDataManagerGenertor;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.module.conf.ModulesXMLConfig;

public class JavaDataManagerGenerator {
	
	/**
	 * 生成dataManager类的模板
	 */
	private static final String  DATA_MANAGER_JAVA_VM_FILE = "vm/java/dataManager.vm";

	/**
	 * 生成dataManager类映射的模板
	 */
	private static final String  DATA_MANAGER_CLASS_REGISTER_JAVA_VM_FILE = "vm/java/humanDataManagerRegister.vm";
	
	public static void generator(ModuleConfig moduleConfig, ModulesXMLConfig modulesXMLConfig) {
		IModuleDataManagerGenertor javaModuleDataManagerGenertor = new JavaModuleDataManagerGenertor(moduleConfig, modulesXMLConfig, DATA_MANAGER_JAVA_VM_FILE);
		javaModuleDataManagerGenertor.generate();
		
		IModuleDataManagerGenertor javaModuleDataManagerClassMappingGenertor = new JavaModuleDataManagerRegisterGenertor(moduleConfig, modulesXMLConfig, DATA_MANAGER_CLASS_REGISTER_JAVA_VM_FILE);
		javaModuleDataManagerClassMappingGenertor.generate();
	}
}
