package org.oszz.ox.core.server.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.oszz.ox.core.conf.HttpSessionKey;
import org.oszz.ox.core.filter.DoGetDataFilter;
import org.oszz.ox.core.filter.DoPostDataFilter;
import org.oszz.ox.core.filter.IFilter;
import org.oszz.ox.core.server.IAsynResponseProcesser;
import org.oszz.ox.core.server.IHandler;
import org.oszz.ox.core.session.GSSession;

/**
 * jetty's server的请求处理者
 * @author ZZ
 *
 */
public class JettyServerHandler extends AbstractHandler{
	
	private String charset = null;
	
	private boolean isDebug;
	
	private IFilter doGetDataFilter; 
	private IFilter doPostDataFilter; 
	
	private IHandler handler;
	
	private int timeoutSeconds;//超时秒数

	protected JettyServerHandler(String charset, IHandler handler, int timeoutSeconds){
		this.charset = charset;
		
		doGetDataFilter = new DoGetDataFilter(isDebug);
		doPostDataFilter = new DoPostDataFilter();
		this.handler = handler;
		this.timeoutSeconds = timeoutSeconds;//超时秒数;
	}
	protected String getCharset() {
		return charset;
	}
	protected void setCharset(String charset) {
		this.charset = charset;
	}
	
	protected void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}
	
	
	@Override
	public void handle(String target, Request baseRequest, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		baseRequest.setHandled(true);
		String charset = getCharset();
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		
		HttpSession httpSession = request.getSession(true);
		String gsSessionKey = HttpSessionKey.GS_SESSION.getValue();
		GSSession gsSession = (GSSession)httpSession.getAttribute(gsSessionKey);
		if(gsSession == null){
			gsSession = new GSSession();
			gsSession.setHttpSession(httpSession);
			gsSession.setResponse(response);
			gsSession.setRequest(request);
			
			httpSession.setAttribute(gsSessionKey, gsSession);
		}
		IAsynResponseProcesser iar = new JettyAsynResponseProcesser(gsSession,doGetDataFilter,doPostDataFilter,isDebug, handler);
		iar.setTimeout(timeoutSeconds);
		iar.asynHandle();
	}

}
