package org.oszz.ox.core.regist;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.core.Globals;
import org.oszz.ox.core.service.IModuleService;

public abstract class AbstractModuleServiceRegister implements IModuleServiceRegister {

	@Override
	public void regist(Class<? extends IModuleService> moduleServiceClazz) {
		IModuleService moduleService = ClassUtils.newInstance(moduleServiceClazz);
		Globals.addService(moduleService);
	}

}
