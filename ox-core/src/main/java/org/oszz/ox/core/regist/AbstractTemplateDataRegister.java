package org.oszz.ox.core.regist;

import org.oszz.ox.core.Globals;
import org.oszz.ox.core.template.ITemplateData;

public abstract class AbstractTemplateDataRegister implements ITemplateDataRegister {

	@Override
	public void regist(Class<? extends ITemplateData> templateDataClazz) {
		Globals.addTemplateDataClass(templateDataClazz);
	}

}
