package org.oszz.ox.tools.template.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FilePathUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.oszz.ox.tools.generator.AbstractGenerator;
import org.oszz.ox.tools.template.ITemplateGenertor;
import org.oszz.ox.tools.template.conf.TemplateConfig;
import org.oszz.ox.tools.template.conf.TemplateDataClassHolderConfig;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;
import org.oszz.ox.tools.utils.VelocityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaTemplateGenertor extends AbstractGenerator implements ITemplateGenertor{
	protected static final Logger log = LoggerFactory.getLogger("JavaTemplateGenertor");
	
	protected static final String TEMPLATE_DATA_CLASS_HOLDER_FILE_NAME = "TemplateDataClassHolder.java";
	
	private TemplateConfig tempConfig;
	
	private List<TemplateDataConfig> tempDataConfigs;
	
	private String abstractTemplate_vmFile;//抽象的模板vm
	private String template_vmFile;//模板类
	private String tempDataClassHolder_vmFile;//模板类
	
	public JavaTemplateGenertor(TemplateConfig tempConfig, List<TemplateDataConfig> tempDataConfigs,
			String abstractTemplate_vmFile, String template_vmFile,
			String tempDataClassHolder_vmFile){
		this.tempConfig = tempConfig;
		this.tempDataConfigs = tempDataConfigs;
		this.abstractTemplate_vmFile = abstractTemplate_vmFile;
		this.template_vmFile = template_vmFile;
		this.tempDataClassHolder_vmFile = tempDataClassHolder_vmFile;
	}

	@Override
	public void generate() {
		String inputPath = this.getAbsoluteInputPath(tempConfig.getInputPath());
		String outputPath = this.getAbsoluteJavaOutputPath(tempConfig.getJavaOutputPath());
		List<TemplateDataClassHolderConfig> dataClasses = new ArrayList<TemplateDataClassHolderConfig>();
		for(TemplateDataConfig tempDataConf : tempDataConfigs){
			String classAllName = tempDataConf.getClassAllName();
			if(tempDataConf.isGenerator()){
				String excelName = tempDataConf.getExcelName();
				String comments = tempDataConf.getComments();
				
				int lastPointIndex = classAllName.lastIndexOf(".");
				String packageName = classAllName.substring(0, lastPointIndex);
				String className = classAllName.substring(lastPointIndex + 1);
				String abstractClassName = ToolsConstant.ABSTRACT_CLASS_NAME_PREFIX + className;
				
				
				generateAbstractTemplate(outputPath, excelName, packageName, abstractClassName, comments);
				generateTemplate(outputPath, excelName, packageName, className, abstractClassName, comments);
			}
			
			String clazzName = classAllName + SystemProperty.CLASS_SUFFIX.getValue();
			dataClasses.add(new TemplateDataClassHolderConfig(clazzName));
		}
		generateTemplateDataClassHolder(outputPath, dataClasses);
	}
	
	private void generateAbstractTemplate(String outputPath, String excelName, String packageName, String abstractClassName, String comments){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("packageName", packageName);
		ctx.put("comments", comments);
		ctx.put("abstractClassName", abstractClassName);
		
		String fileName = abstractClassName + SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		String packagePath = ClassUtils.packageName2Path(packageName);
		
		outputPath += SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
		outputPath = FilePathUtils.getDirIfExists(outputPath) + SystemProperty.FILE_SEPARATOR.getValue();
		
		VelocityUtils.write(this.abstractTemplate_vmFile, ctx, outputPath + fileName, tempConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", fileName, tempConfig.getCharsetName());
	}
	private void generateTemplate(String outputPath, String excelName, String packageName, String className, String abstractClassName, String comments){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("excelName", excelName);
		ctx.put("packageName", packageName);
		ctx.put("comments", comments);
		ctx.put("className", className);
		ctx.put("abstractClassName", abstractClassName);
		
		String fileName = className + SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		String packagePath = ClassUtils.packageName2Path(packageName);
		
		outputPath += SystemProperty.FILE_SEPARATOR.getValue() + packagePath ;
		outputPath = FilePathUtils.getDirIfExists(outputPath) + SystemProperty.FILE_SEPARATOR.getValue();
		
		VelocityUtils.write(this.template_vmFile, ctx, outputPath + fileName, tempConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", fileName, tempConfig.getCharsetName());
	}
	
	private void generateTemplateDataClassHolder(String outputPath, List<TemplateDataClassHolderConfig> dataClasses){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("dataClasses", dataClasses);
		
		outputPath = FilePathUtils.getDirIfExists(outputPath) + SystemProperty.FILE_SEPARATOR.getValue();
		
		VelocityUtils.write(this.tempDataClassHolder_vmFile, ctx, outputPath + TEMPLATE_DATA_CLASS_HOLDER_FILE_NAME, tempConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", TEMPLATE_DATA_CLASS_HOLDER_FILE_NAME, tempConfig.getCharsetName());
	}

}
