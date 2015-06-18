package org.oszz.ox.common.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> {
	
	private static final float hashTableLoadFactor = 0.75f;  
	  
	private LinkedHashMap<K,V> map;  
	private int cacheSize; 
	
	public LRUCache (int cacheSize) {  
		this.cacheSize = cacheSize;  
        int hashTableCapacity = (int)Math.ceil(cacheSize / hashTableLoadFactor) + 1;  
        map = new LinkedHashMap<K,V>(hashTableCapacity, hashTableLoadFactor, true);
    }  
	
	
	public LRUCache (int cacheSize, LRURemoveEldestListener listener) {  
        this.cacheSize = cacheSize;  
        int hashTableCapacity = (int)Math.ceil(cacheSize / hashTableLoadFactor) + 1;  
        map = new LinkedHashMap<K,V>(hashTableCapacity, hashTableLoadFactor, true) {  
           // (an anonymous inner class)  
           private static final long serialVersionUID = 1;  
           @Override 
           protected boolean removeEldestEntry (Map.Entry<K,V> eldest) { 
                 boolean flag = size() > LRUCache.this.cacheSize;
                 if(flag){
                     listener.remove(eldest);
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

    /**
     * 当移除最老时的监听接口
     * @author ZZ
     *
     */
    public static interface LRURemoveEldestListener {
        /**
         * 被移除的最老对象
         * @param eldest 最老的对象
         */
    	@SuppressWarnings("rawtypes") 
        public void remove(Map.Entry eldest);
    }
}
