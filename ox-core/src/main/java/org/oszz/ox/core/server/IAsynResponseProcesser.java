package org.oszz.ox.core.server;


/**
 * 异步的响应处理
 * @author ZZ
 *
 */
public interface IAsynResponseProcesser {

	/**
	 * 异步处理
	 * @author ZZ
	 */
	public void asynHandle();
	
	/**
	 * 异步处理完成
	 * @author ZZ
	 */
	public void complete();
	
	
	/**
	 * 设置超时时间()
	 * @author ZZ
	 */
	public void setTimeout(int seconds);
	
	/**
	 * 处理时间超时的处理
	 * @author ZZ
	 */
	public void onTimeoutHandle();
}
