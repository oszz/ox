package org.oszz.ox.db.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.oszz.ox.db.dao.IDaoSession;
import org.oszz.ox.db.utils.HibernateUtils;

public class HibernateDaoSession implements IDaoSession {
	
	private SessionFactory sessionFactory;
	
	public HibernateDaoSession(){
		sessionFactory = HibernateUtils.getSessionFactory();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Session getSession() {
		return sessionFactory.openSession();
	}

	@Override
	public void beginTransaction(Session session) {
		session.getTransaction().begin();
	}

	@Override
	public void commit(Session session) {
		Transaction tx = session.getTransaction();
		if(tx != null ){
			tx.commit();
		}
	}

	@Override
	public void rollback(Session session) {
		Transaction tx = session.getTransaction();
		if(tx != null){
			tx.rollback();
		}
		
	}

	@Override
	public void closeSession(Session session) {
		if(session.isOpen()){
			session.close();
		}
	}

}
