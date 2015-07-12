package org.oszz.ox.tools.dataManager;

import org.oszz.ox.tools.generator.AbstractGenerator;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.module.conf.ModulesXMLConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractModuleDataManagerGenertor extends AbstractGenerator
		implements IModuleDataManagerGenertor {
	protected static final Logger log = LoggerFactory.getLogger("DataManagerGenertor");
	
	protected static final String MODULE_DATA_MANAGER_CLASS_MAPPING_FILE_NAME = "HumanDataManagerRegister.java";
	
	protected ModuleConfig moduleConfig;
	protected ModulesXMLConfig modulesXMLConfig;

	public AbstractModuleDataManagerGenertor(ModuleConfig moduleConfig, ModulesXMLConfig modulesXMLConfig){
		this.moduleConfig = moduleConfig;
		this.modulesXMLConfig = modulesXMLConfig;
	}
	
}
