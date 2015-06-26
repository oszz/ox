package org.oszz.ox.tools.module;

import org.oszz.ox.tools.generator.AbstractGenerator;
import org.oszz.ox.tools.module.conf.ModuleCoifig;

public abstract class AbstractModuleGenerator extends AbstractGenerator implements IModuleGenerator {

	protected ModuleCoifig moduleCoifig;
	
	public AbstractModuleGenerator(ModuleCoifig moduleCoifig){
		this.moduleCoifig = moduleCoifig;
	}
}
