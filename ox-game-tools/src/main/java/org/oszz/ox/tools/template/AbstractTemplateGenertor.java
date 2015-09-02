package org.oszz.ox.tools.template;

import java.util.List;

import org.oszz.ox.tools.generator.GeneratorPathManagerAdapter;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTemplateGenertor extends GeneratorPathManagerAdapter implements
		ITemplateGenertor {
	protected static final Logger log = LoggerFactory.getLogger("JavaTemplateGenertor");
	
	protected static final String TEMPLATE_DATA_CLASS_MAPPING_FILE_NAME = "TemplateDataRegister.java";
	
	protected ModuleConfig moduleConfig;
	
	protected List<TemplateDataConfig> tempDataConfigs;
	
	public AbstractTemplateGenertor(ModuleConfig moduleConfig, List<TemplateDataConfig> tempDataConfigs){
		this.moduleConfig = moduleConfig;
		this.tempDataConfigs = tempDataConfigs;
	}

}
