package org.oszz.ox.core.filter;

import javax.servlet.http.HttpServletRequest;

import org.oszz.ox.core.message.IMessage;

/**
 * HTTP请求过滤器接口
 * @author ZZ
 *
 */
public interface IFilter {

	/**
	 * 过滤一个HTTP请求，并返回该请求对应的消息类
	 * @author ZZ
	 * @param request HTTP请求
	 * @return 返回该请求对应的消息类
	 */
	public IMessage doInputFilter(HttpServletRequest request);
	
	public void setDebug(boolean isDebug);
}
