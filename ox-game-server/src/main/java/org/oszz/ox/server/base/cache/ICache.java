package org.oszz.ox.server.base.cache;

import java.util.List;

import org.oszz.ox.db.entity.BaseEntity;

public interface ICache {

	/**
	 * 放入缓存内容
	 * @author ZZ
	 * @param cacheKey 缓存Key
	 * @param cacheContent 缓存内容
	 */
	public <T extends BaseEntity> void put(CacheKey cacheKey, T entity);
	
	/**
	 * 放入缓存内容
	 * @author ZZ
	 * @param cacheKey 缓存Key
	 * @param cacheContent 缓存内容
	 */
	public <T extends BaseEntity> void put(CacheKey cacheKey, List<T> entitys);
	
	/**
	 * 根据缓存Key,返回缓存内容
	 * @author ZZ
	 * @param cacheKey 缓存Key
	 */
	public Object get(CacheKey cacheKey);
}
