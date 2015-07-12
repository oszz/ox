package org.oszz.ox.server.base.dom;

import org.oszz.ox.core.regist.AbstractTemplateDataRegister;

/**
 * 模板数据类的class映射类<br>
 * Auto Generator, Don't Modify .
 */
public class TemplateDataRegister extends AbstractTemplateDataRegister{

	@Override
	public void init() {
		//战斗Buff
		this.regist(org.oszz.ox.server.module.auth.template.BattleBuffTemplate.class);
	
	}

}