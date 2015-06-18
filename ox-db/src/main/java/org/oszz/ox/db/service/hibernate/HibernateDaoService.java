package org.oszz.ox.db.service.hibernate;

import org.hibernate.Session;
import org.oszz.ox.db.dao.IDaoCall;
import org.oszz.ox.db.dao.hibernate.HibernateDaoSession;
import org.oszz.ox.db.service.IDaoService;

public class HibernateDaoService implements IDaoService {

	private HibernateDaoSession hibernateDaoSession;
	
	public HibernateDaoService(){
		hibernateDaoSession = new HibernateDaoSession();
	}
	
	public boolean init() {
		return true;
	}


	@Override
	public <T> T call(IDaoCall daoCall) {
		T t = null;
		Session session = hibernateDaoSession.getSession();
		try {
			hibernateDaoSession.beginTransaction(session);
			t = daoCall.crud(session);
			hibernateDaoSession.commit(session);
		} catch (Exception e) {
			hibernateDaoSession.rollback(session);
			e.printStackTrace();
		} finally {
			hibernateDaoSession.closeSession(session);
		}
		return t;
	}
}
