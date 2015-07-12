package org.oszz.ox.server.base.dom;

import org.oszz.ox.core.regist.AbstractModuleServiceRegister;

/**
 * 模块service类的class注册类<br>
 * Auto Generator, Don't Modify .
 */
public class ModuleServiceRegister extends AbstractModuleServiceRegister {

	@Override
	public void init() {
		this.regist(org.oszz.ox.server.module.auth.AuthService.class);
	
		this.regist(org.oszz.ox.server.module.info.InfoService.class);
	
	}
}
