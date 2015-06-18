package org.oszz.ox.server.base.cache.map;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.oszz.ox.db.entity.BaseEntity;
import org.oszz.ox.server.base.cache.CacheKey;
import org.oszz.ox.server.base.cache.ICache;

public class MapCache implements ICache {
	
	private Map<String, Object> cache;

	public MapCache(){
		cache = new ConcurrentHashMap<String, Object>();
	}

	@Override
	public Object get(CacheKey cacheKey) {
		return cache.get(cacheKey.value());
	}

	@Override
	public <T extends BaseEntity> void put(CacheKey cacheKey, T entity) {
		cache.put(cacheKey.value(), entity);
	}

	@Override
	public <T extends BaseEntity> void put(CacheKey cacheKey, List<T> entitys) {
		cache.put(cacheKey.value(), entitys);
	}

}
