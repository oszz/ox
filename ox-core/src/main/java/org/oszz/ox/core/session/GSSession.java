package org.oszz.ox.core.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.Player;

/**
 * Game Server's session
 * @author ZZ
 *
 */
public class GSSession implements ISession {
	
	private HttpSession httpSession;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private IPlayer player;
	
	public GSSession(){
		player = new Player();
	}

	@Override
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	@Override
	public HttpServletRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(HttpServletRequest request) {
		this.request = request;

	}

	@Override
	public HttpServletResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public IPlayer getPlayer() {
		return this.player;
	}

}
