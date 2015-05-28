package org.oszz.ox.core.server;

import java.util.List;

import org.eclipse.jetty.server.Server;
import org.oszz.ox.core.filter.IFilter;

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
		server.setHandler(handler.getServerHandler());
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
	public void addFilterAtLast(IFilter filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IFilter> getFilterChain() {
		// TODO Auto-generated method stub
		return null;
	}

}
