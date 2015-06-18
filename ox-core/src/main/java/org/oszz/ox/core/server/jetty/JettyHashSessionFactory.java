package org.oszz.ox.core.server.jetty;

import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;

public class JettyHashSessionFactory extends AbstractJettySessionFactory{
	
	public JettyHashSessionFactory(){
		this.sessionIdManager = new HashSessionIdManager();
		this.sessionManager = new HashSessionManager();
	}
}
