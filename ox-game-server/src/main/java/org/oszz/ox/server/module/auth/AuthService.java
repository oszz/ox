package org.oszz.ox.server.module.auth;

import org.oszz.ox.core.service.AbstractService;
import org.oszz.ox.db.entity.AccountEntity;
import org.oszz.ox.server.base.Globals;
import org.oszz.ox.server.base.cache.CacheService;
import org.oszz.ox.server.base.cache.ICacheService;

/**
 * 账号认证服务
 * @author ZZ
 *
 */
public class AuthService extends AbstractService {
	
	private ICacheService cacheService;
	
	@Override
	public boolean init() {
		cacheService = Globals.getService(CacheService.class);
		return super.init();
	}
	
	public AccountEntity login(String openId){
//		cacheService.
		return null;
	}
}
