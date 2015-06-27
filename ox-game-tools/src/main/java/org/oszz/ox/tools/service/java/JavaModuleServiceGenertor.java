package org.oszz.ox.tools.service.java;

import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FileUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.module.conf.ModuleXMLConfig;
import org.oszz.ox.tools.module.conf.ModulesXMLConfig;
import org.oszz.ox.tools.service.AbstractServiceGenertor;
import org.oszz.ox.tools.utils.VelocityUtils;

public class JavaModuleServiceGenertor extends AbstractServiceGenertor {
	
	private String service_java_vm_file;

	public JavaModuleServiceGenertor(ModuleConfig moduleConfig, ModulesXMLConfig modulesXMLConfig, 
			String  service_java_vm_file) {
		super(moduleConfig, modulesXMLConfig);
		this.service_java_vm_file = service_java_vm_file;
	}

	@Override
	public void generate() {
		String outputPath = this.getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
		List<ModuleXMLConfig> modules = modulesXMLConfig.getModuleXMLCoifigs();
		for(ModuleXMLConfig module : modules){
			String className = module.getServiceClassName();
			String packageName = module.getModulePackage();
			if(module.isGenerator()){
				writeFile(outputPath, packageName, className);
			}
		}

	}
	
	private void writeFile(String outputPath, String packageName, String className){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("packageName", packageName);
		ctx.put("className", className);
		
		String fileName = className + SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		String packagePath = ClassUtils.packageName2Path(packageName);
		
		outputPath += "/" + packagePath ;
		outputPath = FileUtils.getDirIfExists(outputPath) + "/";
		
		VelocityUtils.write(this.service_java_vm_file, ctx, outputPath + fileName, moduleConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", fileName, moduleConfig.getCharsetName());
	}

}
