package org.oszz.ox.core.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;

public class JettyServer implements IServer{
	
	private Server server;
	
	private int port;
	
	private IHandler handler;
	
	
	public JettyServer(){
	}
	
	public JettyServer(int port){
		this.port = port;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void setHandler(IHandler handler) {
		this.handler = handler;
	}

	@Override
	public void start() throws Exception {
		server = new Server(this.port);
		HandlerCollection handlers = new HandlerCollection();
		handlers.addHandler((AbstractHandler)handler);
		server.setHandler(handlers);
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

}
