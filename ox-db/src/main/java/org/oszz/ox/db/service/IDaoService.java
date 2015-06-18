package org.oszz.ox.db.service;

import org.oszz.ox.db.dao.IDaoCall;

/**
 * 向DAO提供服务
 * @author ZZ
 *
 */
public interface IDaoService {

	public <T> T call(IDaoCall daoCall);
}
