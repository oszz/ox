package org.oszz.ox.core.regist;

import org.oszz.ox.core.service.IModuleService;

public interface IModuleServiceRegister extends IRegister<IModuleService> {
	
	public void regist(Class<? extends IModuleService> moduleServiceClazz);
}
