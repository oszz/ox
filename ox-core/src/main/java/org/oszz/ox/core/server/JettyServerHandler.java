package org.oszz.ox.core.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.Player;
import org.oszz.ox.core.conf.DefaultConfig;
import org.oszz.ox.core.conf.HttpSessionKey;

/**
 * jetty's server的请求处理者
 * @author ZZ
 *
 */
public class JettyServerHandler extends AbstractHandler{
	
	private String charset = null;
	
	
	protected JettyServerHandler(){
		this(DefaultConfig.CHARSET.getValue());
	}

	protected JettyServerHandler(String charset){
		this.charset = charset;
	}
	protected String getCharset() {
		return charset;
	}
	protected void setCharset(String charset) {
		this.charset = charset;
	}
	@Override
	public void handle(String target, Request baseRequest, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
		System.out.println(baseRequest);
		
		baseRequest.setHandled(true);
		String charset = getCharset();
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		String methodName = request.getMethod();
		
		
		HttpSession httpSession = request.getSession(true);
		String playerKey = HttpSessionKey.PLAYER.getValue();
		IPlayer player = (IPlayer)httpSession.getAttribute(playerKey);
		if(player == null){//说明是初始
			player = new Player();
			player.setHttpSession(httpSession);
			httpSession.setAttribute(playerKey, player);
		}
		
		
//		final Continuation continuation = ContinuationSupport.getContinuation(request); 
//		if (continuation.isInitial()) {  
//			continuation.suspend();
//			if(DefaultConfig.HTTP_GET_REQUEST.getValue().equalsIgnoreCase(methodName)){
//				
//			}else if(DefaultConfig.HTTP_POST_REQUEST.getValue().equalsIgnoreCase(methodName)){
//				
//			}
//			continuation.resume();
//			 
//			return; // or continuation.undispatch();  
//		}
	}

}
