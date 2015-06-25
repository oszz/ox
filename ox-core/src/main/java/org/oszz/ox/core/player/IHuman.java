package org.oszz.ox.core.player;

import org.oszz.ox.core.data.IHumanDataManager;

public interface IHuman {

	public <T extends IHumanDataManager> T getDataManager(Class<T> dataManagerClazz);
}
