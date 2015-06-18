package org.oszz.ox.db.dao.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.oszz.ox.db.dao.IDao;
import org.oszz.ox.db.dao.IDaoHolder;

public class HibernateDaoHolder implements IDaoHolder {
	
	private Map<Class<? extends IDao>, IDao> daos;

	public HibernateDaoHolder(){
		daos = new HashMap<Class<? extends IDao>, IDao>();
		init();
	}
	
	private void init(){
		IDao accountDao = new AccountDao();
		daos.put(AccountDao.class, accountDao);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IDao> T getDao(Class<T> calzz) {
		return (T)daos.get(calzz);
	}

}
