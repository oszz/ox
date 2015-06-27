package org.oszz.ox.tools.template.java;

import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FileUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.template.AbstractTemplateGenertor;
import org.oszz.ox.tools.template.ITemplateGenertor;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;
import org.oszz.ox.tools.utils.VelocityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据模板生成器
 * @author ZZ
 *
 */
public class JavaTemplateGenertor extends AbstractTemplateGenertor implements ITemplateGenertor{
	protected static final Logger log = LoggerFactory.getLogger("JavaTemplateGenertor");
	
	private String template_vmFile;//模板类
	
	public JavaTemplateGenertor(ModuleConfig moduleConfig, List<TemplateDataConfig> tempDataConfigs,
			String template_vmFile){
		super(moduleConfig, tempDataConfigs);
		this.template_vmFile = template_vmFile;
	}

	@Override
	public void generate() {
		String outputPath = this.getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
		for(TemplateDataConfig tempDataConf : tempDataConfigs){
			if(tempDataConf.isGenerator()){
				String excelName = tempDataConf.getExcelName();
				String comments = tempDataConf.getComments();
				
				String packageName = tempDataConf.getPackageName();
				String abstractClassName = tempDataConf.getAbstractClassName();
				String className = tempDataConf.getClassName();
				
				writeFile(outputPath, excelName, packageName, className, abstractClassName, comments);
			}
		}
	}
	
	private void writeFile(String outputPath, String excelName, String packageName, String className, String abstractClassName, String comments){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("excelName", excelName);
		ctx.put("packageName", packageName);
		ctx.put("comments", comments);
		ctx.put("className", className);
		ctx.put("abstractClassName", abstractClassName);
		
		String fileName = className + SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		String packagePath = ClassUtils.packageName2Path(packageName);
		
		outputPath += "/" + packagePath ;
		outputPath = FileUtils.getDirIfExists(outputPath) + "/";
		
		VelocityUtils.write(this.template_vmFile, ctx, outputPath + fileName, moduleConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", fileName, moduleConfig.getCharsetName());
	}

}
