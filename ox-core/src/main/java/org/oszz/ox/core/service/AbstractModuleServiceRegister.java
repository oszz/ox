package org.oszz.ox.core.service;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.core.Globals;

public abstract class AbstractModuleServiceRegister implements IModuleServiceRegister {

	@Override
	public void regist(Class<? extends IModuleService> moduleServiceClazz) {
		IModuleService moduleService = ClassUtils.newInstance(moduleServiceClazz);
		Globals.addService(moduleService);
	}

}
