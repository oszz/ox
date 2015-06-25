package org.oszz.ox.tools.dataManager.java;

import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FilePathUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.dataManager.AbstractModuleDataManagerGenertor;
import org.oszz.ox.tools.dataManager.conf.DataManagerConfig;
import org.oszz.ox.tools.module.ModuleCoifig;
import org.oszz.ox.tools.module.ModuleCoifig.Module;
import org.oszz.ox.tools.utils.VelocityUtils;

public class JavaModuleDataManagerGenertor extends AbstractModuleDataManagerGenertor {
	
	private String vm_file;

	public JavaModuleDataManagerGenertor(DataManagerConfig dataManagerConfig, ModuleCoifig moduleConfig, 
			String  vm_file) {
		super(dataManagerConfig, moduleConfig);
		this.vm_file = vm_file;
	}

	@Override
	public void generate() {
		String outputPath = this.getAbsoluteJavaOutputPath(dataManagerConfig.getJavaOutputPath());
		 List<Module> modules = moduleConfig.getModules();
		for(Module module : modules){
			String className = module.getDataManagerClassName();
			String packageName = module.getPackageName();
			
			writeFile(outputPath, packageName, className);
		}

	}
	
	private void writeFile(String outputPath, String packageName, String className){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("packageName", packageName);
		ctx.put("className", className);
		
		String fileName = className + SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		String packagePath = ClassUtils.packageName2Path(packageName);
		
		outputPath += "/" + packagePath ;
		outputPath = FilePathUtils.getDirIfExists(outputPath) + "/";
		
		VelocityUtils.write(this.vm_file, ctx, outputPath + fileName, dataManagerConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", fileName, dataManagerConfig.getCharsetName());
	}

}
