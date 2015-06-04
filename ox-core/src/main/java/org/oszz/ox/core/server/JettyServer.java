package org.oszz.ox.core.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SessionIdManager;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.oszz.ox.core.filter.DefaultFilterChain;
import org.oszz.ox.core.filter.IFilterChain;
import org.oszz.ox.core.filter.ProtocolCodecFilter;
import org.oszz.ox.core.filter.codec.protobuf.ProtobufCodecFactory;
import org.oszz.ox.core.filter.codec.request.doGet.DoGetCodecFactory;
import org.oszz.ox.core.filter.codec.request.doPost.DoPostCodecFactory;

public class JettyServer implements IServer{
	
	private Server server;
	
	private IHandler handler;
	
	private boolean isDebug = false;
	
	private SessionIdManager sessionIdManager;
	
	private SessionManager sessionManager;
	
	private SessionHandler sessions;
	
	private IFilterChain filterChain;
	
	
	public JettyServer(){
		this(Boolean.FALSE);
	}
	
	
	public JettyServer(boolean isDebug){
		server = new Server();
		this.isDebug = isDebug;
		
		sessionIdManager = new HashSessionIdManager();
        server.setSessionIdManager(sessionIdManager);
		
		// Create the SessionHandler (wrapper) to handle the sessions
        sessionManager = new HashSessionManager();
        sessions = new SessionHandler(sessionManager);
        
        filterChain = new DefaultFilterChain();
        
        initFilterChain();
	}
	
	private void initFilterChain(){
		ProtocolCodecFilter doGetCodecFilter = new ProtocolCodecFilter(new DoGetCodecFactory(""));
		ProtocolCodecFilter doPostCodecFilter = new ProtocolCodecFilter(new DoPostCodecFactory(""));
		ProtocolCodecFilter protobufCodecFilter = new ProtocolCodecFilter(new ProtobufCodecFactory(""));
		
		filterChain.addFilterAtLast(doGetCodecFilter);
		filterChain.addFilterAtLast(doPostCodecFilter);
		filterChain.addFilterAtLast(protobufCodecFilter);
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
	public IFilterChain getFilterChain() {
		return this.filterChain;
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
//			server.setHandler(context);
			contextHandlerCollection.addHandler(context);
		}
//		server.setHandler(contextHandlerCollection);
//		contextHandlerCollection.addHandler(sessions);
		server.setHandler(contextHandlerCollection);
	}

}
