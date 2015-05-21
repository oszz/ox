package org.oszz.ox.core.event;

/**
 * 事件处理者接口
 * @author ZZ
 *
 * @param <T>
 */
public interface IEventHandler {

	/**
	 * 处理事件
	 * @author ZZ
	 * @param para 事件参数
	 */
	public <T> void handler(T para);
}
