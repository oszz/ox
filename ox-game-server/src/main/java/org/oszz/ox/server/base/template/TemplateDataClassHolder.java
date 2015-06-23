package org.oszz.ox.server.base.template;

import org.oszz.ox.core.template.AbstractTemplateDataClassHolder;

public class TemplateDataClassHolder extends AbstractTemplateDataClassHolder {

	@Override
	public void init() {
		this.put(org.oszz.ox.server.module.info.temp.BattleBuffTemplate.class);
		
	}

}
