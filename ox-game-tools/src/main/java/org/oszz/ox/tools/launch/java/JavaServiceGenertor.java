package org.oszz.ox.tools.launch.java;

import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.module.conf.ModulesXMLConfig;
import org.oszz.ox.tools.service.IServiceGenertor;
import org.oszz.ox.tools.service.java.JavaModuleServiceClassMappingGenertor;
import org.oszz.ox.tools.service.java.JavaModuleServiceGenertor;

public class JavaServiceGenertor {

	/**
	 * 生成serivce类的模板
	 */
	private static final String  SERVICE_JAVA_VM_FILE = "vm/java/service.vm";

	/**
	 * 生成serivce类映射的模板
	 */
	private static final String  SERVICE_CLASS_MAPPING_JAVA_VM_FILE = "vm/java/serivceClassMapping.vm";
	
	public static void generator(ModuleConfig moduleConfig, ModulesXMLConfig modulesXMLConfig) {
		IServiceGenertor javaModuleServiceGenertor = new JavaModuleServiceGenertor(moduleConfig, modulesXMLConfig, SERVICE_JAVA_VM_FILE);
		javaModuleServiceGenertor.generate();
		
		IServiceGenertor javaModuleServiceClassMappingGenertor = new JavaModuleServiceClassMappingGenertor(moduleConfig, modulesXMLConfig, SERVICE_CLASS_MAPPING_JAVA_VM_FILE);
		javaModuleServiceClassMappingGenertor.generate();
	}
}
