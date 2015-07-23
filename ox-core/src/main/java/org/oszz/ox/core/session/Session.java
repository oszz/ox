package org.oszz.ox.core.session;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.oszz.ox.core.conf.DefaultConfig;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.core.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Game Server's session
 * @author ZZ
 *
 */
public class Session implements ISession {
	private static final Logger log = LoggerFactory.getLogger("GSSession");
	
	private HttpSession httpSession;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private IPlayer player;
	
	public Session(){
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
	public void send(IMessage message) {
		String methodName = this.request.getMethod();
		if(DefaultConfig.HTTP_GET_REQUEST.getValue().equalsIgnoreCase(methodName)){
			writeGet(message);
		}else if(DefaultConfig.HTTP_POST_REQUEST.getValue().equalsIgnoreCase(methodName)){
			writePost(message);
		}
		
		
	}
	private void writeGet(IMessage message){
		PrintWriter pw = null;
		try {
			pw = this.response.getWriter();
			pw.print(message.toStringForBrowser());
//			pw.print(message.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("response:{},发送消息出错{} .",response, message);
		}finally{
			if(pw != null) {
				pw.close();
			}
		}
		
	}
	
	private void writePost(IMessage message){
		OutputStream out = null;
		try {
			out = this.response.getOutputStream();
			out.write(message.toBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("response:{},发送消息出错{} .",response, message);
		} finally {
			if(out != null){try {out.close();} catch (IOException e) {}}
		}
	}

}
