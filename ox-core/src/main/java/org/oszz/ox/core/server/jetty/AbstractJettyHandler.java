package org.oszz.ox.core.server.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.oszz.ox.core.conf.DefaultConfig;
import org.oszz.ox.core.conf.HttpSessionKey;
import org.oszz.ox.core.filter.DoGetDataFilter;
import org.oszz.ox.core.filter.DoPostDataFilter;
import org.oszz.ox.core.filter.IFilter;
import org.oszz.ox.core.server.IAsynResponseProcesser;
import org.oszz.ox.core.server.IRequestHandler;
import org.oszz.ox.core.session.Session;

/**
 * 抽象的处理类
 * @author ZZ
 *
 */
public abstract class AbstractJettyHandler extends AbstractHandler implements IRequestHandler {
	
	/**
	 * 默认处理超时的秒数
	 */
	private static final int DEFAULT_TIMEOUT_SECONDS = 10;

//	private JettyServerHandler jettyServerHandler;
	
	private String charsetName;
	private int timeoutSeconds;
	
	private IFilter doGetDataFilter; 
	private IFilter doPostDataFilter; 
	
	private boolean isDebug;
	
	public AbstractJettyHandler(){
		this(DefaultConfig.CHARSET.getValue(), DEFAULT_TIMEOUT_SECONDS);
	}
	
	public AbstractJettyHandler(int timeoutSeconds){
		this(DefaultConfig.CHARSET.getValue(), timeoutSeconds);
	}
	
	public AbstractJettyHandler(String charsetName, int timeoutSeconds){
		this.charsetName = charsetName;
//		jettyServerHandler = new JettyServerHandler(charsetName, this, timeoutSeconds);
		doGetDataFilter = new DoGetDataFilter();
		doPostDataFilter = new DoPostDataFilter();
	}
	
	@Override
	public String getCharsetName() {
		return charsetName;
	}
	
	@Override
	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
		doGetDataFilter.setDebug(isDebug);
	}
	
	@Override
	public void handle(String target, Request baseRequest, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		baseRequest.setHandled(true);
		request.setCharacterEncoding(charsetName);
		response.setCharacterEncoding(charsetName);
//		response.setContentType("text/html;charset=UTF-8");
//		response.setStatus(HttpServletResponse.SC_OK);
		//如果是favicon.ico，直接返回
		if(target.indexOf(DefaultConfig.FAVICON_ICO_URL_PATTERN.getValue()) > 0){
			return ;
		}
		
		HttpSession httpSession = request.getSession(true);
		String gsSessionKey = HttpSessionKey.GS_SESSION.getValue();
		Session session = (Session)httpSession.getAttribute(gsSessionKey);
		if(session == null){
			session = new Session();
			session.setHttpSession(httpSession);
			session.setResponse(response);
			session.setRequest(request);
			
			httpSession.setAttribute(gsSessionKey, session);
		}
		IAsynResponseProcesser iar = new JettyAsynResponseProcesser(session,doGetDataFilter,doPostDataFilter,isDebug, this);
		iar.setTimeout(timeoutSeconds);
		iar.asynHandle();
	}
}
