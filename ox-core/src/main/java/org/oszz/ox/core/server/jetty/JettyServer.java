package org.oszz.ox.core.server.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SessionIdManager;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.session.SessionHandler;
import org.oszz.ox.core.conf.DefaultConfig;
import org.oszz.ox.core.server.IHandler;
import org.oszz.ox.core.server.IServer;
import org.oszz.ox.core.server.ISessionFactory;

public class JettyServer implements IServer{
	
	private Server server;
	
	private IHandler handler;
	
	private boolean isDebug = false;
	
//	private SessionIdManager sessionIdManager;
	
//	private SessionManager sessionManager;
	
	private SessionHandler sessions;
	
//	private ISessionFactory sessionFactory;
	
	public JettyServer(){
		this(Boolean.FALSE, DefaultConfig.CHARSET.getValue());
	}
	
	
	public JettyServer(boolean isDebug, String charsetName){
		server = new Server();
		this.isDebug = isDebug;
		
		
        
	}

	@Override
	public void setHandler(IHandler handler) {
		this.handler = handler;
	}

	@Override
	public void start() throws Exception {
		handler.setDebug(this.isDebug);

        sessions.setHandler(handler.getServerHandler());
		server.start();
		server.join();
	}

	@Override
	public void stop() throws Exception {
		server.stop();
	}

	@Override
	public void restart() throws Exception {
		stop();
		start();
	}


	@Override
	public void addContext(int port, String... contextPaths) {
		ServerConnector connector = new ServerConnector(server);  
		connector.setPort(port);
		server.addConnector(connector); 
		ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
		for(String contextPath : contextPaths){
			ContextHandler context = new ContextHandler(contextPath);
			context.setHandler(sessions);
			contextHandlerCollection.addHandler(context);
		}
		server.setHandler(contextHandlerCollection);
	}


	@Override
	public void setSessionFactory(ISessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
		IJettySessionFactory jettySessionFactory = (IJettySessionFactory)sessionFactory; 
		
		SessionIdManager sessionIdManager = jettySessionFactory.getSessionIdManager();
        server.setSessionIdManager(sessionIdManager);
		
		// Create the SessionHandler (wrapper) to handle the sessions
        SessionManager sessionManager = jettySessionFactory.getSessionManager();
        sessions = new SessionHandler(sessionManager);
	}

}
