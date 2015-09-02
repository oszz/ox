package org.oszz.ox.tools.message;

import java.io.File;

import org.oszz.ox.tools.generator.GeneratorPathManagerAdapter;
import org.oszz.ox.tools.module.conf.ModuleConfig;

/**
 * Proto类文件的生成器
 * @author ZZ
 *
 */
public abstract class AbstractMessageGenerator extends GeneratorPathManagerAdapter implements IMessageGenerator {
	
	protected ModuleConfig moduleConfig;
	
	public AbstractMessageGenerator(ModuleConfig moduleConfig){
		this.moduleConfig = moduleConfig;
	}

	
	
}
