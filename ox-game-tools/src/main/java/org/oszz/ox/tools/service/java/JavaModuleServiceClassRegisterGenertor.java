package org.oszz.ox.tools.service.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.module.conf.ModuleXMLConfig;
import org.oszz.ox.tools.module.conf.ModulesXMLConfig;
import org.oszz.ox.tools.service.AbstractServiceGenertor;
import org.oszz.ox.tools.utils.VelocityUtils;

public class JavaModuleServiceClassRegisterGenertor extends
		AbstractServiceGenertor {
	private String service_class_mapping_java_vm_file;

	public JavaModuleServiceClassRegisterGenertor(ModuleConfig moduleConfig, ModulesXMLConfig modulesXMLConfig, 
			String service_class_mapping_java_vm_file) {
		super(moduleConfig, modulesXMLConfig);
		this.service_class_mapping_java_vm_file = service_class_mapping_java_vm_file;
	}

	@Override
	public void generate() {
		String outputPath = this.getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
		List<ModuleXMLConfig> modules = modulesXMLConfig.getModuleXMLCoifigs();
		List<String> mapClasses = new ArrayList<String>();
		for(ModuleXMLConfig module : modules){
			String className = module.getServiceClassName();
			String packageName = module.getModulePackage();
			
			String classAllName = packageName + SystemProperty.PACKAGE_SEPARATOR.getValue() + className + SystemProperty.CLASS_SUFFIX.getValue();
			mapClasses.add(classAllName);
		}
		writeFile(outputPath, mapClasses);

	}
	private void writeFile(String outputPath, List<String> mapClasses){
		VelocityContext ctx = new VelocityContext();
		String packageName = ToolsConstant.MAPPING_PACKAGE_NAME;
		ctx.put("packageName", packageName);
		ctx.put("mapClasses", mapClasses);
		
		outputPath = this.getAbsoluteOutputPath(outputPath, packageName);
		VelocityUtils.write(this.service_class_mapping_java_vm_file, ctx, outputPath + MODULE_SERVICE_CLASS_MAPPING_FILE_NAME, moduleConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", MODULE_SERVICE_CLASS_MAPPING_FILE_NAME, moduleConfig.getCharsetName());

	}

}
