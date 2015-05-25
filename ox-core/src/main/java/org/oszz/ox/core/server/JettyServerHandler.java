package org.oszz.ox.core.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class JettyServerHandler extends AbstractHandler implements IHandler{

	@Override
	public void handle(String target, Request baseRequest, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
	}

}
