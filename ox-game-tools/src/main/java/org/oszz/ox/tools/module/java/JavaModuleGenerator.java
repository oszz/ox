package org.oszz.ox.tools.module.java;

import java.util.ArrayList;
import java.util.List;

import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.module.AbstractModuleGenerator;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.module.conf.ModuleXMLConfig;
import org.oszz.ox.tools.module.conf.ModulesXMLConfig;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;

public class JavaModuleGenerator extends AbstractModuleGenerator{
	
	public JavaModuleGenerator(ModuleConfig moduleConfig, ModulesXMLConfig modulesXMLConfig) {
		super(moduleConfig, modulesXMLConfig);
		
	}

	@Override
	public void generate() {
		//所有的messgeCode配置
		List<MessageCodeConfig> totalMsgCodeConfigs = new ArrayList<MessageCodeConfig>();
		//所有的Template数据配置
		List<TemplateDataConfig> totalTempDataConfigs = new ArrayList<TemplateDataConfig>();
		
		List<ModuleXMLConfig> moduleXMLConfigs = modulesXMLConfig.getModuleXMLCoifigs();
		for(ModuleXMLConfig moduleXMLConfig : moduleXMLConfigs){
			totalMsgCodeConfigs.addAll(moduleXMLConfig.getMsgCodeConfigs());
			totalTempDataConfigs.addAll(moduleXMLConfig.getTempDataConfigs());
		}
	}

}
