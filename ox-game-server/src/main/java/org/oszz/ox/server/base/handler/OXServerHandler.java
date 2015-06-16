package org.oszz.ox.server.base.handler;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.server.AbstractJettyHandler;

@SuppressWarnings("unchecked")
public class OXServerHandler extends AbstractJettyHandler {

	public OXServerHandler(){
		super();
	}
	public OXServerHandler(String charsetName){
		super(charsetName);
	}
	@Override
	public <T> void handle(IPlayer palyer, T para) {
		// TODO Auto-generated method stub
		
	}

}
