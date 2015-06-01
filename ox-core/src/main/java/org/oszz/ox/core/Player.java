package org.oszz.ox.core;

import javax.servlet.http.HttpSession;

public class Player implements IPlayer {
	
	private HttpSession httpSession;

	@Override
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

}
