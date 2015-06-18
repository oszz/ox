package org.oszz.ox.db.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.oszz.ox.db.dao.IDaoCall;
import org.oszz.ox.db.entity.AccountEntity;

/**
 * 账号DAO
 * @author ZZ
 *
 */
public class AccountDao extends HibernateBaseDaoSupport {

	public AccountDao(){
		super();
	}
	
	public AccountEntity fiandByOpenId(String openId){
		List<AccountEntity> results = hibernateDaoService.call(new IDaoCall() {
			@SuppressWarnings("unchecked")
			@Override
			public List<AccountEntity> crud(Session session) {
				Criteria criteria = session.createCriteria(AccountEntity.class);
				criteria.add(Restrictions.eq("openId", openId));
				return criteria.list();
			}
		});
		AccountEntity entity = null;
		if(results != null && results.size() != 0){//其实只会查询出来一个
			entity = results.get(0);
		}
		return entity;
	}
}
