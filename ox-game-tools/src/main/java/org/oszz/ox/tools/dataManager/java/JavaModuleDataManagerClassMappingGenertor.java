package org.oszz.ox.tools.dataManager.java;

import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.FileUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.dataManager.AbstractModuleDataManagerGenertor;
import org.oszz.ox.tools.dataManager.conf.DataManagerConfig;
import org.oszz.ox.tools.module.conf.ModuleCoifig;
import org.oszz.ox.tools.utils.VelocityUtils;

public class JavaModuleDataManagerClassMappingGenertor extends
		AbstractModuleDataManagerGenertor {
	
	private String vm_file;

	public JavaModuleDataManagerClassMappingGenertor(DataManagerConfig dataManagerConfig, ModuleCoifig moduleConfig, 
			String vm_file) {
		super(dataManagerConfig, moduleConfig);
		this.vm_file = vm_file;
	}

	@Override
	public void generate() {
//		String outputPath = this.getAbsoluteJavaOutputPath(dataManagerConfig.getJavaOutputPath());
//		List<Module> modules = moduleConfig.getModules();
//		List<String> mapClasses = new ArrayList<String>();
//		for(Module module : modules){
//			String className = module.getDataManagerClassName();
//			String packageName = module.getPackageName();
//			
//			String classAllName = packageName + SystemProperty.PACKAGE_SEPARATOR.getValue() + className + SystemProperty.CLASS_SUFFIX.getValue();
//			mapClasses.add(classAllName);
//		}
//		writeFile(outputPath, mapClasses);

	}
	private void writeFile(String outputPath, List<String> mapClasses){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("mapClasses", mapClasses);
		
		outputPath = FileUtils.getDirIfExists(outputPath) + SystemProperty.FILE_SEPARATOR.getValue();
		
		VelocityUtils.write(this.vm_file, ctx, outputPath + MODULE_DATA_MANAGER_CLASS_MAPPING_FILE_NAME, dataManagerConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", MODULE_DATA_MANAGER_CLASS_MAPPING_FILE_NAME, dataManagerConfig.getCharsetName());

	}

}
