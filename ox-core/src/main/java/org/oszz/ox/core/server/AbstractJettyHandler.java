package org.oszz.ox.core.server;

import org.oszz.ox.core.conf.DefaultConfig;

/**
 * 抽象的处理类
 * @author ZZ
 *
 */
public abstract class AbstractJettyHandler implements IHandler {

	private JettyServerHandler jettyServerHandler;
	
	private String charsetName;
	
//	private boolean isDebug = false;
	
	public AbstractJettyHandler(){
		this(DefaultConfig.CHARSET.getValue());
	}
	
	public AbstractJettyHandler(String charsetName){
		this.charsetName = charsetName;
		jettyServerHandler = new JettyServerHandler(charsetName, this);
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
//		this.isDebug = isDebug;
		jettyServerHandler.setDebug(isDebug);
	}
	
}
