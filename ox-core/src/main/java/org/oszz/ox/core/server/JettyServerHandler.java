package org.oszz.ox.core.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.oszz.ox.core.gsDefault.DefaultConfig;

/**
 * jetty's server的请求处理者
 * @author ZZ
 *
 */
public class JettyServerHandler extends AbstractHandler implements IHandler{
	
	private String charset = null;
	
	public JettyServerHandler(){
		this(DefaultConfig.CHARSET.getValue());
	}

	public JettyServerHandler(String charset){
		this.charset = charset;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
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
	}

}
