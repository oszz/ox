package org.oszz.ox.tools.module;

import org.oszz.ox.tools.generator.AbstractGenerator;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.module.conf.ModulesXMLConfig;

public abstract class AbstractModuleGenerator extends AbstractGenerator implements IModuleGenerator {

	protected ModuleConfig moduleConfig;
	protected ModulesXMLConfig modulesXMLConfig;
	
	public AbstractModuleGenerator(ModuleConfig moduleConfig, ModulesXMLConfig modulesXMLConfig){
		this.moduleConfig = moduleConfig;
		this.modulesXMLConfig = modulesXMLConfig;
	}
}
