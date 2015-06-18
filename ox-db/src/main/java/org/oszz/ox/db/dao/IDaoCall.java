package org.oszz.ox.db.dao;

import org.hibernate.Session;


public interface IDaoCall {

	/**
	 * 增删改查
 	 * Create Read Update Delete
	 * @author ZZ
	 * @param session
	 */
	public <T> T crud(Session session);
}
