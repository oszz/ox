package org.oszz.ox.server.base.mapping;

import org.oszz.ox.core.service.AbstractModuleServiceRegister;

/**
 * 模块service类的class注册类<br>
 * Auto Generator, Don't Modify .
 */
public class ModuleServiceRegister extends AbstractModuleServiceRegister {

	@Override
	public void init() {
		this.regist(org.oszz.ox.server.module.info.InfoService.class);
//		this.put(org.oszz.ox.server.module.info.InfoService.class);
	
	}
}
