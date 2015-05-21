package org.oszz.ox.core.event;

import java.util.List;

/**
 * 事件监听者
 * @author ZZ
 *
 */
public interface IEventListener {

	/**
	 * 监听一条事件
	 * @author ZZ
	 * @param key 事件key
	 * @param handler 一个事件处理者
	 */
	public <T> void listen(String key, IEventHandler handler);
	
	/**
	 * 监听一条事件
	 * @author ZZ
	 * @param key 事件key
	 * @param handlers 多个事件处理者
	 */
	public <T> void listen(String key, List<IEventHandler> handlers);
}
