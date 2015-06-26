package org.oszz.ox.tools.service.java;

import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.FileUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.module.conf.ModuleCoifig;
import org.oszz.ox.tools.service.AbstractServiceGenertor;
import org.oszz.ox.tools.service.conf.ServiceConfig;
import org.oszz.ox.tools.utils.VelocityUtils;

public class JavaModuleServiceClassMappingGenertor extends
		AbstractServiceGenertor {
	
	private String service_class_mapping_java_vm_file;

	public JavaModuleServiceClassMappingGenertor(ServiceConfig serviceConfig, ModuleCoifig moduleConfig, 
			String service_class_mapping_java_vm_file) {
		super(serviceConfig, moduleConfig);
		this.service_class_mapping_java_vm_file = service_class_mapping_java_vm_file;
	}

	@Override
	public void generate() {
//		String outputPath = this.getAbsoluteJavaOutputPath(serviceConfig.getJavaOutputPath());
//		List<Module> modules = moduleConfig.getModules();
//		List<String> mapClasses = new ArrayList<String>();
//		for(Module module : modules){
//			String className = module.getServiceClassName();
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
		
		VelocityUtils.write(this.service_class_mapping_java_vm_file, ctx, outputPath + MODULE_SERVICE_CLASS_MAPPING_FILE_NAME, serviceConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", MODULE_SERVICE_CLASS_MAPPING_FILE_NAME, serviceConfig.getCharsetName());

	}

}
