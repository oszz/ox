package org.oszz.ox.core.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationSupport;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.oszz.ox.core.conf.DefaultConfig;

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
		baseRequest.setHandled(true);
		String charset = getCharset();
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);

		final Continuation continuation = ContinuationSupport.getContinuation(request);  
		
		String methodName = request.getMethod();
		if(DefaultConfig.HTTP_GET_REQUEST.getValue().equalsIgnoreCase(methodName)){
			
		}else if(DefaultConfig.HTTP_POST_REQUEST.getValue().equalsIgnoreCase(methodName)){
			
		}
		
		response.getWriter().write("ssssssss");
		response.getWriter().flush();
	}

}
