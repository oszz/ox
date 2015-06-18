package org.oszz.ox.db.dao;

public interface IDaoHolder {

	public <T extends IDao> T getDao(Class<T> calzz);
}
