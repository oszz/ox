package org.oszz.ox.core.server.jetty;

import org.eclipse.jetty.server.SessionIdManager;
import org.eclipse.jetty.server.SessionManager;
import org.oszz.ox.core.server.ISessionFactory;

public interface IJettySessionFactory extends ISessionFactory {

	public SessionIdManager getSessionIdManager();
		
	public SessionManager getSessionManager();
}
