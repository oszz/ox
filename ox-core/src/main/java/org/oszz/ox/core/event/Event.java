package org.oszz.ox.core.event;

/**
 * 事件
 * @author ZZ
 *
 */
public class Event<T> {

	private String key;//事件key
	private T para;//事件参数
	
	/**
	 * 构建一条事件
	 * @param key 事件key
	 */
	public Event(String key){
		this(key, null);
	}
	
	/**
	 * 构建一条事件
	 * @param key 事件key
	 * @param para 事件参数 
	 */
	public Event(String key, T para){
		this.key = key;
		this.para = para;
	}

	/**
	 * 返回事件key
	 * @author ZZ
	 * @return 返回事件key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * 返回事件参数对象
	 * @author ZZ
	 * @return 返回事件参数对象
	 */
	public T getPara() {
		return para;
	}

	/**
	 * 设置事件参数
	 * @author ZZ
	 * @param para 事件参数
	 */
	public void setPara(T para) {
		this.para = para;
	}
	
	
}
