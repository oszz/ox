package org.oszz.ox.core.server.req;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationSupport;
import org.oszz.ox.core.conf.DefaultConfig;
import org.oszz.ox.core.filter.IFilter;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.server.IHandler;
import org.oszz.ox.core.session.GSSession;

public class AsynJettyContinuation implements IAsynRequest{
	
	private final Continuation continuation;
	
//	private final HttpServletRequest request;
	
	private final HttpServletResponse response;
	
	private IMessage message;
	
	private final GSSession gsSession;
	
	private final IHandler handler;
	
	public AsynJettyContinuation(GSSession gsSession, IFilter doGetDataFilter, 
			IFilter doPostDataFilter, boolean isDebug, IHandler handler){
		this.gsSession = gsSession;
		this.handler = handler;
		HttpServletRequest request = gsSession.getRequest();
		this.continuation = ContinuationSupport.getContinuation(request); 
//		this.request = request;
		this.response = gsSession.getResponse();
		continuation.setTimeout(10);
		continuation.suspend(response);
		
		String methodName = request.getMethod();
		
		if(DefaultConfig.HTTP_GET_REQUEST.getValue().equalsIgnoreCase(methodName)){
			if(isDebug){//是debug状态，才接受get请求
				message = doGetDataFilter.doInputFilter(request);
			}
		}else if(DefaultConfig.HTTP_POST_REQUEST.getValue().equalsIgnoreCase(methodName)){
			message = doPostDataFilter.doInputFilter(request);
		}
	}

	@Override
	public void asynHandle() {
		this.handler.handle(gsSession, message, this);
	}

	@Override
	public void callback() {
		continuation.complete();
	}

}
