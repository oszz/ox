package org.oszz.ox.db.dao;

import org.hibernate.Session;



/**
 * 
 * @author ZZ
 *
 */
public interface IDaoSession {

	public <T> T getSession();
	
	public void beginTransaction(Session session);
	public void commit(Session session);
	public void rollback(Session session);
	public void closeSession(Session session);
}
