package org.oszz.ox.server.base.cache;

import org.oszz.ox.core.service.IService;
import org.oszz.ox.db.entity.AccountEntity;

public interface ICacheService extends IService{

	public void setCacheFactory(ICacheFactory cacheFactory);
	
	
	public AccountEntity login(String openId);
}
