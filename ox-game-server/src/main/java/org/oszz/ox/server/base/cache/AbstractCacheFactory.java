package org.oszz.ox.server.base.cache;

public class AbstractCacheFactory implements ICacheFactory {
	
	protected ICache cache;
	
	@Override
	public ICache getCache() {
		return cache;
	}
}
