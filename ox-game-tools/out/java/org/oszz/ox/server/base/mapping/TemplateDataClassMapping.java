package org.oszz.ox.server.base.mapping;

import org.oszz.ox.core.template.AbstractTemplateDataClassMapping;

/**
 * 模板数据类的class映射类<br>
 * Auto Generator, Don't Modify .
 */
public class TemplateDataClassMapping extends AbstractTemplateDataClassMapping {

	@Override
	public void init() {
		//战斗Buff
		this.put(org.oszz.ox.server.module.auth.template.BattleBuffTemplate.class);
	
	}

}