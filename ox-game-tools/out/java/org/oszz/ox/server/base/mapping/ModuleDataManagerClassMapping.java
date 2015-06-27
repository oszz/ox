package org.oszz.ox.server.base.mapping;

import org.oszz.ox.core.data.AbstractModuleDataManagerClassMapping;
/**
 * 模块DataManager类的class映射类<br>
 * Auto Generator, Don't Modify .
 */
public class ModuleDataManagerClassMapping extends AbstractModuleDataManagerClassMapping {

	@Override
	public void init() {
		this.put(org.oszz.ox.server.module.auth.HumanAuthDataManager.class);
	
		this.put(org.oszz.ox.server.module.info.HumanInfoDataManager.class);
	
	}
}

