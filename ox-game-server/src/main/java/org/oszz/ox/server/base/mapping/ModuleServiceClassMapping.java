package org.oszz.ox.server.base.mapping;

import org.oszz.ox.core.service.AbstractModuleServiceClassMapping;

/**
 * 模块service类的class持有者<br>
 * Auto Generator, Don't Modify .
 */
public class ModuleServiceClassMapping extends AbstractModuleServiceClassMapping {

	@Override
	public void init() {
		this.put(org.oszz.ox.server.module.info.InfoService.class);
	}
}
