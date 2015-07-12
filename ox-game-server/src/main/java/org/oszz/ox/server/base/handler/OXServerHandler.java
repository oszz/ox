package org.oszz.ox.server.base.handler;

import org.oszz.ox.core.Globals;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.MessageProcesserType;
import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.core.processer.ProcesserService;
import org.oszz.ox.core.server.jetty.AbstractJettyHandler;

public class OXServerHandler extends AbstractJettyHandler {

	public OXServerHandler(){
		super();
	}
	public OXServerHandler(int timeoutSeconds){
		super(timeoutSeconds);
	}
	@Override
	public void requestHandle(IPlayer player, IMessage message) {
		MessageProcesserType msgProType = message.getMessageProcesserType();
		if(msgProType == MessageProcesserType.ASYN){
			Globals.getService(ProcesserService.class).getAsynProcesser().putMessage(player, message);
		}else if(msgProType == MessageProcesserType.SCENE){
			
		}else if(msgProType == MessageProcesserType.WORLD){
			Globals.getService(ProcesserService.class).getWorldProcesser().putMessage(player, message);
		}
		
	}

}
