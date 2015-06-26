package org.oszz.ox.tools.service;

import org.oszz.ox.tools.generator.AbstractGenerator;
import org.oszz.ox.tools.module.conf.ModuleCoifig;
import org.oszz.ox.tools.service.conf.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractServiceGenertor extends AbstractGenerator
		implements IServiceGenertor {
	protected static final Logger log = LoggerFactory.getLogger("ServiceGenertor");
	
	protected static final String MODULE_SERVICE_CLASS_MAPPING_FILE_NAME = "ModuleServiceClassMapping.java";
	

	protected ServiceConfig serviceConfig;
	protected ModuleCoifig moduleConfig;

	public AbstractServiceGenertor(ServiceConfig serviceConfig, ModuleCoifig moduleConfig){
		this.serviceConfig = serviceConfig;
		this.moduleConfig = moduleConfig;
	}
	
}
