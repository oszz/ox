package org.oszz.ox.db.dao;

import java.util.List;

import org.oszz.ox.db.entity.BaseEntity;

/**
 * 基础DAO接口
 * @author ZZ
 *
 */
public interface IDao {

	
	public void saveOrUpdate(BaseEntity baseEntity);
	
	public List<BaseEntity> findAll(Class<? extends BaseEntity> baseEntityClazz);
	
	public void saveOrUpdateAll(List<BaseEntity> baseEntitys);
}
