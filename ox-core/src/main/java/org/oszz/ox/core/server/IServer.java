package org.oszz.ox.core.server;

/**
 * 服务器的接口（JETTY、 MINA等）
 * @author ZZ
 *
 */
public interface IServer {

	public void setPort(int port);
	
	public void setHandler(IHandler handler);
	
	public void start();
	
	public void stop();
	
	public void restart();
	
}
