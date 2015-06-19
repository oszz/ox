package org.oszz.ox.server.base.cache;

import org.oszz.ox.common.utils.DateUtils;
import org.oszz.ox.db.dao.IDaoHolder;
import org.oszz.ox.db.dao.hibernate.AccountDao;
import org.oszz.ox.db.dao.hibernate.HibernateDaoHolder;
import org.oszz.ox.db.entity.AccountEntity;
import org.oszz.ox.server.base.cache.map.MapCache;
import org.oszz.ox.server.base.utils.AccountIdUtils;

public class CacheService implements ICacheService {
	
	/**
	 * 默认的缓存
	 */
	private static final ICache DEFAULT_CACHE = new MapCache();
	
	private ICache cache;
	
	private IDaoHolder daoHolder;
	
	@Override
	public boolean create() {
		cache = DEFAULT_CACHE;
		return true;
	}

	@Override
	public boolean init() {
		daoHolder = new HibernateDaoHolder();
		return true;
	}

	@Override
	public boolean start() {
		return true;
	}

	@Override
	public boolean restart() {
		return false;
	}

	@Override
	public boolean stop() {
		return false;
	}

	@Override
	public void setCacheFactory(ICacheFactory cacheFactory) {
		this.cache = cacheFactory.getCache();
	}

	@Override
	public AccountEntity login(String openId) {
		cache.get(CacheKey.AUTH_ACCOUNT_LIST);
		
		AccountDao accountDao = daoHolder.getDao(AccountDao.class);
		AccountEntity accountEntity = accountDao.fiandByOpenId(openId);
		if(accountEntity == null){
			accountEntity = new AccountEntity();
			accountEntity.setOpenId(openId);
			accountEntity.setAccountId(AccountIdUtils.getId());
			accountEntity.setCreateTime(DateUtils.getCurrentSeconds());
			
			accountDao.saveOrUpdate(accountEntity);
		}
		return accountEntity;
	}

}
