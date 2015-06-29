package org.oszz.ox.tools.dataManager.java;

import java.io.File;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FileUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.dataManager.AbstractModuleDataManagerGenertor;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.module.conf.ModuleXMLConfig;
import org.oszz.ox.tools.module.conf.ModulesXMLConfig;
import org.oszz.ox.tools.utils.VelocityUtils;

public class JavaModuleDataManagerGenertor extends AbstractModuleDataManagerGenertor {
	
	private String vm_file;

	public JavaModuleDataManagerGenertor(ModuleConfig moduleConfig, ModulesXMLConfig modulesXMLConfig, 
			String  vm_file) {
		super(moduleConfig, modulesXMLConfig);
		this.vm_file = vm_file;
	}

	@Override
	public void generate() {
		String outputPath = this.getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
		List<ModuleXMLConfig> modules = modulesXMLConfig.getModuleXMLCoifigs();
		for(ModuleXMLConfig module : modules){
			String className = module.getDataManagerClassName();
			String packageName = module.getModulePackage();
			if(module.isGenerator()){
				String fileName = className + SystemProperty.JAVA_CLASS_SUFFIX.getValue();
				String packagePath = ClassUtils.packageName2Path(packageName);
				
				String fileOutputPath = outputPath + SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
				fileOutputPath = FileUtils.getDirIfExists(fileOutputPath) + SystemProperty.FILE_SEPARATOR.getValue();
				File file = new File(fileOutputPath + fileName);
				if(!file.exists()){//如果dataManager的类存在，则不用再生成dataManager
					writeFile(packageName, className, file);
				}
			}
		}

	}
	
	private void writeFile(String packageName, String className, File file){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("packageName", packageName);
		ctx.put("className", className);
		
		VelocityUtils.write(this.vm_file, ctx, file.getAbsolutePath(), moduleConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", file.getName(), moduleConfig.getCharsetName());
	}

}
