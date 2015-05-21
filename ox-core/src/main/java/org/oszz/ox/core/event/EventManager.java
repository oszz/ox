package org.oszz.ox.core.event;

import java.util.List;


/**
 * 事件派发者
 * @author ZZ
 *
 */
public final class EventManager {
	
	private EventListeners eventListeners;
	
	private EventManager(){
		eventListeners = new EventListeners();
	}
	
	public static EventManager getInstance(){
		return InnerClass.instance;
	}

	/**
	 * 派发一条事件
	 * @author ZZ
	 * @param event 事件
	 */
	public <T> void dispatch(Event<T> event) {
		List<IEventHandler> handlers = eventListeners.get(event.getKey());
		if(handlers != null && handlers.size() != 0){
			for(IEventHandler handler : handlers){
				handler.handler(event.getPara());
			}
		}
		
	}
	
	public <T> void addListener(Event<T> event, IEventHandler handler){
		eventListeners.add(event.getKey(), handler);
	}
	public <T> void addListener(Event<T> event, List<IEventHandler> handlers){
		eventListeners.add(event.getKey(), handlers);
	}
	
	private static class InnerClass {
		private final static EventManager instance = new EventManager();
	}

}
