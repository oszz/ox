package org.oszz.ox.db.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.oszz.ox.db.dao.IDao;
import org.oszz.ox.db.dao.IDaoCall;
import org.oszz.ox.db.entity.BaseEntity;
import org.oszz.ox.db.service.hibernate.HibernateDaoService;

/**
 * 抽象的BaseDao实现，所有的DAO应该继承该类
 * @author ZZ
 *
 */
public abstract class HibernateBaseDaoSupport implements IDao {
	
	protected final HibernateDaoService hibernateDaoService;
	
	public HibernateBaseDaoSupport(){
		hibernateDaoService = new HibernateDaoService();
	}

	@Override
	public void saveOrUpdate(BaseEntity baseEntity) {
		hibernateDaoService.call(new IDaoCall() {
			@SuppressWarnings("unchecked")
			@Override
			public Boolean crud(Session session) {
				session.saveOrUpdate(baseEntity);
				return true;
			}
		});

	}

	@Override
	public List<BaseEntity> findAll(Class<? extends BaseEntity> baseEntityClazz) {
		List<BaseEntity> entitys = hibernateDaoService.call(new IDaoCall() {
			@SuppressWarnings("unchecked")
			@Override
			public List<BaseEntity> crud(Session session) {
				Criteria criteria = session.createCriteria(baseEntityClazz);
				return criteria.list();
			}
		});
		return entitys;

	}

	@Override
	public void saveOrUpdateAll(List<BaseEntity> baseEntitys) {
		for(BaseEntity baseEntity : baseEntitys){
			saveOrUpdate(baseEntity);
		}
	}

}
