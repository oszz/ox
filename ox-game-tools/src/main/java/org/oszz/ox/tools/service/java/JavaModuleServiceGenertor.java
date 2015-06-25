package org.oszz.ox.tools.service.java;

import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FilePathUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.module.ModuleCoifig;
import org.oszz.ox.tools.module.ModuleCoifig.Module;
import org.oszz.ox.tools.service.AbstractServiceGenertor;
import org.oszz.ox.tools.service.conf.ServiceConfig;
import org.oszz.ox.tools.utils.VelocityUtils;

public class JavaModuleServiceGenertor extends AbstractServiceGenertor {
	
	private String service_java_vm_file;

	public JavaModuleServiceGenertor(ServiceConfig serviceConfig, ModuleCoifig moduleConfig, 
			String  service_java_vm_file) {
		super(serviceConfig, moduleConfig);
		this.service_java_vm_file = service_java_vm_file;
	}

	@Override
	public void generate() {
		String outputPath = this.getAbsoluteJavaOutputPath(serviceConfig.getJavaOutputPath());
		 List<Module> modules = moduleConfig.getModules();
		for(Module module : modules){
			String className = module.getServiceClassName();
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
		
		VelocityUtils.write(this.service_java_vm_file, ctx, outputPath + fileName, serviceConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", fileName, serviceConfig.getCharsetName());
	}

}
