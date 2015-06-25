package org.oszz.ox.core.session;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.core.player.Player;

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
		this.player = new Player();
		player.setSession(this);
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

	@Override
	public void sendTest(String message) {
		try {
			this.response.getWriter().write(message);
			this.response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void send(byte[] bytes) {
		OutputStream out = null;
		try {
			out = this.response.getOutputStream();
			out.write(bytes);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null){try {out.close();} catch (IOException e) {}}
		}
	}

}
