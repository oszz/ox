package org.oszz.ox.core.server;


/**
 * 服务器的接口（JETTY、 MINA等）
 * @author ZZ
 *
 */
public interface IServer {

	public void setHandler(IRequestHandler requestHandler);
	
	public void start() throws Exception ;
	
	public void stop()  throws Exception;
	
	public void restart()  throws Exception;
	
	public void addContext(int port, String... contextPaths);
	
	public void setSessionFactory(ISessionFactory sessionFactory);
}
