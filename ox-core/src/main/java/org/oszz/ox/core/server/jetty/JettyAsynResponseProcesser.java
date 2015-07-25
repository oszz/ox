package org.oszz.ox.core.server.jetty;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationListener;
import org.eclipse.jetty.continuation.ContinuationSupport;
import org.oszz.ox.core.conf.DefaultConfig;
import org.oszz.ox.core.filter.IFilter;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.server.IAsynResponseProcesser;
import org.oszz.ox.core.server.IRequestHandler;
import org.oszz.ox.core.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jetty的异步处理
 * @author ZZ
 *
 */
public class JettyAsynResponseProcesser implements IAsynResponseProcesser{
	
	private static final Logger log = LoggerFactory.getLogger("JettyAsynResponseProcesser");
	
	private Continuation continuation;
	
//	private final HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private IMessage message;
	
	private final Session session;
	
	private final IRequestHandler requestHandler;
	
//	private int timeoutSeconds;//超时秒数
	
	public JettyAsynResponseProcesser(Session session, IFilter doGetDataFilter, 
			IFilter doPostDataFilter, boolean isDebug, IRequestHandler requestHandler){
		this.session = session;
		this.requestHandler = requestHandler;
		HttpServletRequest request = session.getRequest();
		this.response = session.getResponse();
		
		
		String methodName = request.getMethod();
		
		if(DefaultConfig.HTTP_GET_REQUEST.getValue().equalsIgnoreCase(methodName)){
			if(isDebug){//是debug状态，才接受get请求
				message = doGetDataFilter.doInputFilter(request);
			}
		}else if(DefaultConfig.HTTP_POST_REQUEST.getValue().equalsIgnoreCase(methodName)){
			message = doPostDataFilter.doInputFilter(request);
		}
		if (message != null) {
			this.continuation = ContinuationSupport.getContinuation(request); 
			continuation.suspend(response);
			
			message.setAsynResponseProcesser(this);
			initContinuationListener();
		} else {
			try {
				this.response.getWriter().write("消息错误");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void initContinuationListener(){
		continuation.addContinuationListener(new ContinuationListener() {
			@Override
			public void onTimeout(Continuation arg0) {
				onTimeoutHandle();
			}
			@Override
			public void onComplete(Continuation arg0) {
				
			}
		});
	}

	@Override
	public void asynHandle() {
		if(message != null){
			this.requestHandler.requestHandle(session.getPlayer(), message);
		}
	}

	@Override
	public void complete() {
		continuation.complete();
	}

	@Override
	public void onTimeoutHandle() {
		log.error("异步处理消息-{}时，超时: {}", message, continuation);
	}

	@Override
	public void setTimeout(int seconds) {
		if(continuation != null){
			continuation.setTimeout(seconds * 1000L);//转成毫秒
		}
	}

}
