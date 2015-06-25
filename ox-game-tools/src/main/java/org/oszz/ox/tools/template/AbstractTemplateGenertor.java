package org.oszz.ox.tools.template;

import java.util.List;

import org.oszz.ox.tools.generator.AbstractGenerator;
import org.oszz.ox.tools.template.conf.TemplateConfig;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTemplateGenertor extends AbstractGenerator implements
		ITemplateGenertor {
	protected static final Logger log = LoggerFactory.getLogger("JavaTemplateGenertor");
	
	protected static final String TEMPLATE_DATA_CLASS_MAPPING_FILE_NAME = "TemplateDataClassMapping.java";
	
	protected TemplateConfig tempConfig;
	
	protected List<TemplateDataConfig> tempDataConfigs;
	
	public AbstractTemplateGenertor(TemplateConfig tempConfig, List<TemplateDataConfig> tempDataConfigs){
		this.tempConfig = tempConfig;
		this.tempDataConfigs = tempDataConfigs;
	}

}
