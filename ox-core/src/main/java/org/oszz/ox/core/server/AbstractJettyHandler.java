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
	
	public AbstractJettyHandler(){
		this(DefaultConfig.CHARSET.getValue());
	}
	
	public AbstractJettyHandler(String charsetName){
		this.charsetName = charsetName;
		jettyServerHandler = new JettyServerHandler(charsetName);
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
	
}