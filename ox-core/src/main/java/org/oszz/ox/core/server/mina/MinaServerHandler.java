package org.oszz.ox.core.server.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.core.server.IRequestHandler;

public class MinaServerHandler implements IRequestHandler{
	
	private IoHandlerAdapter ioHandlerAdapter;
	
	public MinaServerHandler(IoHandlerAdapter ioHandlerAdapter){
		this.ioHandlerAdapter = ioHandlerAdapter;
	}

	@Override
	public String getCharsetName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setDebug(boolean isDebug) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestHandle(IPlayer player, IMessage message) {
		// TODO Auto-generated method stub
		
	}

}
