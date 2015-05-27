package org.oszz.ox.core.server;

import java.util.List;

import org.oszz.ox.core.filter.IFilter;

/**
 * 服务器的接口（JETTY、 MINA等）
 * @author ZZ
 *
 */
public interface IServer {

	public void setPort(int port);
	
	public void setHandler(IHandler handler);
	
	public void start() throws Exception;
	
	public void stop()  throws Exception;
	
	public void restart()  throws Exception;
	
	public void addFilterAtLast(IFilter filter);
	
	public List<IFilter> getFilterChain();
}
