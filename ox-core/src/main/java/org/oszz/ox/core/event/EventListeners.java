package org.oszz.ox.core.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件的监听者们
 * @author ZZ
 *
 */
public class EventListeners {

	private Map<String, List<IEventHandler>> listeners;
	
	public EventListeners(){
		listeners = new HashMap<String, List<IEventHandler>>();
	}
	
	public List<IEventHandler> get(String key){
		return listeners.get(key);
	}
	
	public void add(String key, IEventHandler handler){
		List<IEventHandler> listenerList = listeners.get(key);
		if(listenerList == null){
			listenerList = new ArrayList<IEventHandler>();
			listeners.put(key, listenerList);
		}
		listenerList.add(handler);
	}
	
	public void add(String key, List<IEventHandler> handlers){
		List<IEventHandler> lls = listeners.get(key);
		if(lls == null){
			lls = new ArrayList<IEventHandler>();
			listeners.put(key, lls);
		}
		lls.addAll(handlers);
	}
}
