package org.oszz.ox.common.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU缓存
 * @author ZZ
 *
 * @param <K> key
 * @param <V> value
 */
public class LRUCache<K,V> {
	
	private static final float hashTableLoadFactor = 0.75f;  
	  
	private LinkedHashMap<K,V> map; //存放对象的map 
	private int cacheSize; //缓存的大小
	
	/**
	 * 构建一个LRU缓存
	 * @param cacheSize 缓存的大小
	 */
	public LRUCache (int cacheSize) {  
		this.cacheSize = cacheSize;  
        int hashTableCapacity = (int)Math.ceil(cacheSize / hashTableLoadFactor) + 1;  
        map = new LinkedHashMap<K,V>(hashTableCapacity, hashTableLoadFactor, true);
    }  
	
	/**
	 * 构建一个LRU缓存
	 * @param cacheSize 缓存的大小
	 * @param lruRemoveEldestListener LRU缓存移除最老对象的监听类，当有数据被移除时会调用该类的  {@link ILRURemoveEldestListener#remove(java.util.Map.Entry)}
	 */
	public LRUCache (int cacheSize, ILRURemoveEldestListener lruRemoveEldestListener) {  
        this.cacheSize = cacheSize;  
        int hashTableCapacity = (int)Math.ceil(cacheSize / hashTableLoadFactor) + 1;  
        map = new LinkedHashMap<K,V>(hashTableCapacity, hashTableLoadFactor, true) {  
           // (an anonymous inner class)  
           private static final long serialVersionUID = 1;  
           @Override 
           protected boolean removeEldestEntry (Map.Entry<K,V> eldest) { 
                 boolean flag = size() > LRUCache.this.cacheSize;
                 if(flag){
                	 lruRemoveEldestListener.remove(eldest);
                 }
                 return flag; 
              }
           }; 
        }  

    public synchronized V get (K key) {  
       return map.get(key); 
    }  

    public synchronized void put (K key, V value) {  
       map.put (key, value); 
    }  

    public synchronized void remove (K key) {  
       map.remove(key); 
    }

    public synchronized void putAll (Map<K, V> fromMap) {  
        map.putAll(fromMap);
    }  

    public synchronized void clear() {  
       map.clear(); 
    }  

    public synchronized int usedEntries() {  
       return map.size(); 
    }  

    public synchronized Collection<Map.Entry<K,V>> getAll() {  
       return new ArrayList<Map.Entry<K,V>>(map.entrySet()); 
    }  
}
