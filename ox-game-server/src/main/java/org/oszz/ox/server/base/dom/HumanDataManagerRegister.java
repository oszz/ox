package org.oszz.ox.server.base.dom;

import org.oszz.ox.core.regist.AbstractHumanDataManagerRegister;
/**
 * 模块DataManager类的class映射类<br>
 * Auto Generator, Don't Modify .
 */
public class HumanDataManagerRegister extends AbstractHumanDataManagerRegister {

	@Override
	public void init() {
		this.regist(org.oszz.ox.server.module.auth.HumanAuthDataManager.class);
	
		this.regist(org.oszz.ox.server.module.info.HumanInfoDataManager.class);
	
	}
}

