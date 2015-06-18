package org.oszz.ox.core.server.jetty;

import org.oszz.ox.core.conf.DefaultConfig;
import org.oszz.ox.core.server.IHandler;

/**
 * 抽象的处理类
 * @author ZZ
 *
 */
public abstract class AbstractJettyHandler implements IHandler {
	
	/**
	 * 默认处理超时的秒数
	 */
	private static final int DEFAULT_TIMEOUT_SECONDS = 10;

	private JettyServerHandler jettyServerHandler;
	
	private String charsetName;
	
	public AbstractJettyHandler(){
		this(DefaultConfig.CHARSET.getValue(), DEFAULT_TIMEOUT_SECONDS);
	}
	
	public AbstractJettyHandler(int timeoutSeconds){
		this(DefaultConfig.CHARSET.getValue(), timeoutSeconds);
	}
	
	public AbstractJettyHandler(String charsetName, int timeoutSeconds){
		this.charsetName = charsetName;
		jettyServerHandler = new JettyServerHandler(charsetName, this, timeoutSeconds);
	}
	
	@Override
	public String getCharsetName() {
		return charsetName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JettyServerHandler getServerHandler() {
		return jettyServerHandler;
	}
	
	@Override
	public void setDebug(boolean isDebug) {
		jettyServerHandler.setDebug(isDebug);
	}
}
