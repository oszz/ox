package org.oszz.ox.server.base.cache.map;

import org.oszz.ox.server.base.cache.AbstractCacheFactory;

public class MapCacheFactory extends AbstractCacheFactory {
	
	
	public MapCacheFactory(){
		this.cache = new MapCache();
	}

}
