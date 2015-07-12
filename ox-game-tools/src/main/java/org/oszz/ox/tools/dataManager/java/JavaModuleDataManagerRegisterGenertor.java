package org.oszz.ox.tools.dataManager.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.oszz.ox.tools.dataManager.AbstractModuleDataManagerGenertor;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.module.conf.ModuleXMLConfig;
import org.oszz.ox.tools.module.conf.ModulesXMLConfig;
import org.oszz.ox.tools.utils.VelocityUtils;

public class JavaModuleDataManagerRegisterGenertor extends
		AbstractModuleDataManagerGenertor {
	private String vm_file;

	public JavaModuleDataManagerRegisterGenertor(ModuleConfig moduleConfig, ModulesXMLConfig modulesXMLConfig,
			String vm_file) {
		super(moduleConfig, modulesXMLConfig);
		this.vm_file = vm_file;
	}

	@Override
	public void generate() {
		String outputPath = this.getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
		List<ModuleXMLConfig> modules = modulesXMLConfig.getModuleXMLCoifigs();
		List<String> mapClasses = new ArrayList<String>();
		for(ModuleXMLConfig module : modules){
			String className = module.getDataManagerClassName();
			String packageName = module.getModulePackage();
			
			String classAllName = packageName + SystemProperty.PACKAGE_SEPARATOR.getValue() + className + SystemProperty.CLASS_SUFFIX.getValue();
			mapClasses.add(classAllName);
		}
		writeFile(outputPath, mapClasses);

	}
	private void writeFile(String outputPath, List<String> mapClasses){
		VelocityContext ctx = new VelocityContext();
		String packageName = ToolsConstant.MAPPING_PACKAGE_NAME;
		ctx.put("mapClasses", mapClasses);
		ctx.put("packageName", packageName);
		
		outputPath = this.getAbsoluteOutputPath(outputPath, packageName);
		
		VelocityUtils.write(this.vm_file, ctx, outputPath + MODULE_DATA_MANAGER_CLASS_MAPPING_FILE_NAME, moduleConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", MODULE_DATA_MANAGER_CLASS_MAPPING_FILE_NAME, moduleConfig.getCharsetName());

	}

}
