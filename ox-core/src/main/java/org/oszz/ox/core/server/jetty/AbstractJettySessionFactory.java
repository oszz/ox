package org.oszz.ox.core.server.jetty;

import org.eclipse.jetty.server.SessionIdManager;
import org.eclipse.jetty.server.SessionManager;

public abstract class AbstractJettySessionFactory implements
		IJettySessionFactory {
	
	protected SessionIdManager sessionIdManager;
	protected SessionManager sessionManager;
	
	@Override
	public SessionIdManager getSessionIdManager() {
		return sessionIdManager;
	}

	@Override
	public SessionManager getSessionManager() {
		return sessionManager;
	}
}
