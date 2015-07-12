package org.oszz.ox.tools.launch.java;

import java.util.List;

import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.template.ITemplateGenertor;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;
import org.oszz.ox.tools.template.java.JavaAbstractTemplateGenertor;
import org.oszz.ox.tools.template.java.JavaTemplateDataClassRegisterGenertor;
import org.oszz.ox.tools.template.java.JavaTemplateGenertor;

public class JavaTemplateGenerator {
	
	/**
	 * 生成抽象的模板类
	 */
	private static final String  ABSTRACT_TEMPLATE_JAVA_VM_FILE = "vm/java/abstractTemplate.vm";
	
	/**
	 * 生成模板数据类
	 */
	private static final String  TEMPLATE_JAVA_VM_FILE = "vm/java/template.vm";
	
	/**
	 * 生成模板类映射的模板
	 */
	private static final String  TEMP_DATA_CLASS_MAPPING_JAVA_VM_FILE = "vm/java/templateDataRegister.vm";
	
	/**
	 * 生成java相关的代码
	 * @author ZZ
	 * @param tempConfig 模板的相关配置
	 * @param tempDataConfigs 模板数据的相关配置
	 */
	public static void generator(ModuleConfig moduleConfig, List<TemplateDataConfig> tempDataConfigs){
		ITemplateGenertor javaAbstractTemplateGenertor = new JavaAbstractTemplateGenertor(moduleConfig, tempDataConfigs,
				ABSTRACT_TEMPLATE_JAVA_VM_FILE);
		javaAbstractTemplateGenertor.generate();
		
		ITemplateGenertor javaTempGenerator = new JavaTemplateGenertor(moduleConfig, tempDataConfigs,
				TEMPLATE_JAVA_VM_FILE);
		javaTempGenerator.generate();
		
		ITemplateGenertor javaTemplateDataClassHolderGenertor = new JavaTemplateDataClassRegisterGenertor(moduleConfig, tempDataConfigs,
				TEMP_DATA_CLASS_MAPPING_JAVA_VM_FILE);
		javaTemplateDataClassHolderGenertor.generate();
	
	}
}
