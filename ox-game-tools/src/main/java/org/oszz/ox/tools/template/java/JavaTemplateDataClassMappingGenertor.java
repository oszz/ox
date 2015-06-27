package org.oszz.ox.tools.template.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.template.AbstractTemplateGenertor;
import org.oszz.ox.tools.template.ITemplateGenertor;
import org.oszz.ox.tools.template.conf.TemplateDataClassHolderConfig;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;
import org.oszz.ox.tools.utils.VelocityUtils;

/**
 * 数据模板class持有者生成器
 * @author ZZ
 *
 */
public class JavaTemplateDataClassMappingGenertor extends
		AbstractTemplateGenertor implements ITemplateGenertor {
	
	private String tempDataClassMapping_vmFile;
	
	public JavaTemplateDataClassMappingGenertor(ModuleConfig moduleConfig, List<TemplateDataConfig> tempDataConfigs,
			String tempDataClassMapping_vmFile){
		super(moduleConfig, tempDataConfigs);
		this.tempDataClassMapping_vmFile = tempDataClassMapping_vmFile;
	}

	@Override
	public void generate() {
		String outputPath = this.getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
		List<TemplateDataClassHolderConfig> dataClasses = new ArrayList<TemplateDataClassHolderConfig>();
		for(TemplateDataConfig tempDataConf : tempDataConfigs){
			String classAllName = tempDataConf.getClassAllName();
			String clazzName = classAllName + SystemProperty.CLASS_SUFFIX.getValue();
			dataClasses.add(new TemplateDataClassHolderConfig(clazzName, tempDataConf.getComments()));
		}
		writeFile(outputPath, dataClasses);
	}
	
	private void writeFile(String outputPath, List<TemplateDataClassHolderConfig> dataClasses){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("dataClasses", dataClasses);
		
		outputPath = this.getAbsoluteOutputPath(outputPath, ToolsConstant.MAPPING_PACKAGE_NAME);
		VelocityUtils.write(this.tempDataClassMapping_vmFile, ctx, outputPath + TEMPLATE_DATA_CLASS_MAPPING_FILE_NAME, moduleConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", TEMPLATE_DATA_CLASS_MAPPING_FILE_NAME, moduleConfig.getCharsetName());

	}

}
