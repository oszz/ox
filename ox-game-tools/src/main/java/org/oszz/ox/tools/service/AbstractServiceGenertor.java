package org.oszz.ox.tools.service;

import org.oszz.ox.tools.generator.AbstractGenerator;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.module.conf.ModulesXMLConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractServiceGenertor extends AbstractGenerator
		implements IServiceGenertor {
	protected static final Logger log = LoggerFactory.getLogger("ServiceGenertor");
	
	protected static final String MODULE_SERVICE_CLASS_MAPPING_FILE_NAME = "ModuleServiceRegister.java";
	

	protected ModuleConfig moduleConfig;
	protected ModulesXMLConfig modulesXMLConfig;

	public AbstractServiceGenertor(ModuleConfig moduleConfig, ModulesXMLConfig modulesXMLConfig){
		this.moduleConfig = moduleConfig;
		this.modulesXMLConfig = modulesXMLConfig;
	}
	
}
