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
import org.oszz.ox.core.filter.IFilterChain;
import org.oszz.ox.core.session.GSSession;

/**
 * jetty's server的请求处理者
 * @author ZZ
 *
 */
public class JettyServerHandler extends AbstractHandler{
	
	private String charset = null;
	
	private IFilterChain filterChain;
	
	private boolean isDebug;
	
	
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
	
	protected void setFilterChain(IFilterChain filterChain) {
		this.filterChain = filterChain;
	}
	
	protected void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
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
		
//		HttpSession httpSession = request.getSession(true);
		HttpSession httpSession = request.getSession(true);
		System.out.println(httpSession.getId());
//		String playerKey = HttpSessionKey.PLAYER.getValue();
//		IPlayer player = (IPlayer)httpSession.getAttribute(playerKey);
//		if(player == null){//说明是初始
//			player = new Player();
//			player.setHttpSession(httpSession);
//			httpSession.setAttribute(playerKey, player);
//		}
		String gsSessionKey = HttpSessionKey.GS_SESSION.getValue();
		GSSession gsSession = (GSSession)httpSession.getAttribute(gsSessionKey);
		if(gsSession == null){
			gsSession = new GSSession();
			gsSession.setHttpSession(httpSession);
			httpSession.setAttribute(gsSessionKey, gsSession);
		}
		
		
		if(DefaultConfig.HTTP_GET_REQUEST.getValue().equalsIgnoreCase(methodName)){
			if(isDebug){//是debug状态，才接受get请求
				filterChain.doInputFilter(gsSession, request, response);
			}
		}else if(DefaultConfig.HTTP_POST_REQUEST.getValue().equalsIgnoreCase(methodName)){
			filterChain.doInputFilter(gsSession, request, response);
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
