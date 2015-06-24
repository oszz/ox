package org.oszz.ox.common.structure;

import java.util.Map;

/**
 * LRU缓存移除最老对象的监听接口
 * @author ZZ
 *
 */
public interface ILRURemoveEldestListener {

	 /**
     * 当移除的最老对象对象时，会调用该方法
     * @param eldest 最老的对象
     */
	@SuppressWarnings("rawtypes") 
    public void remove(Map.Entry eldest);
}
