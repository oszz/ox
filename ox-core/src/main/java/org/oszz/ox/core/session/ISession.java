package org.oszz.ox.core.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.oszz.ox.core.IPlayer;

public interface ISession {
	
	public void setHttpSession(HttpSession httpSession);

	public HttpServletRequest getRequest();
	public void setRequest(HttpServletRequest request);
	
	public HttpServletResponse getResponse();
	public void setResponse(HttpServletResponse response);
	
	
	public IPlayer getPlayer();
	
}
