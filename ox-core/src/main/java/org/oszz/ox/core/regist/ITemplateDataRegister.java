package org.oszz.ox.core.regist;

import org.oszz.ox.core.template.ITemplateData;

public interface ITemplateDataRegister extends IRegister<ITemplateData> {
	
	public void regist(Class<? extends ITemplateData> templateDataClazz);
}
