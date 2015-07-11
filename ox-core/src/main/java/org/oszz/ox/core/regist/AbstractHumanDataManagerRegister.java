package org.oszz.ox.core.regist;

import org.oszz.ox.core.Globals;
import org.oszz.ox.core.data.IHumanDataManager;

public abstract class AbstractHumanDataManagerRegister implements
		IHumanDataManagerRegister {

	@Override
	public void regist(Class<? extends IHumanDataManager> clazz) {
		Globals.addHumanDataManagerClass(clazz);
	}
}
