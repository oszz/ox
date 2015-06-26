package org.oszz.ox.tools.dataManager;

import org.oszz.ox.tools.dataManager.conf.DataManagerConfig;
import org.oszz.ox.tools.generator.AbstractGenerator;
import org.oszz.ox.tools.module.conf.ModuleCoifig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractModuleDataManagerGenertor extends AbstractGenerator
		implements IModuleDataManagerGenertor {
	protected static final Logger log = LoggerFactory.getLogger("ServiceGenertor");
	
	protected static final String MODULE_DATA_MANAGER_CLASS_MAPPING_FILE_NAME = "ModuleDataManagerClassMapping.java";
	

	protected DataManagerConfig dataManagerConfig;
	protected ModuleCoifig moduleConfig;

	public AbstractModuleDataManagerGenertor(DataManagerConfig dataManagerConfig, ModuleCoifig moduleConfig){
		this.dataManagerConfig = dataManagerConfig;
		this.moduleConfig = moduleConfig;
	}
	
}
