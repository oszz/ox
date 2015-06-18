package org.oszz.ox.server.base.handler;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.MessageProcesserType;
import org.oszz.ox.core.server.jetty.AbstractJettyHandler;
import org.oszz.ox.core.session.ISession;
import org.oszz.ox.server.base.Globals;
import org.oszz.ox.server.base.processer.ProcesserService;

@SuppressWarnings("unchecked")
public class OXServerHandler extends AbstractJettyHandler {

	public OXServerHandler(){
		super();
	}
	public OXServerHandler(int timeoutSeconds){
		super(timeoutSeconds);
	}
	
	@Override
	public void handle(ISession session, IMessage message) {
		IPlayer player = session.getPlayer();
//		player.sendMessage(null);
		MessageProcesserType msgProType = message.getMessageProcesserType();
		if(msgProType == MessageProcesserType.ASYN){
			Globals.getService(ProcesserService.class).getAsynProcesser().putMessage(player, message);
		}else if(msgProType == MessageProcesserType.SCENE){
			
		}else if(msgProType == MessageProcesserType.WORLD){
			Globals.getService(ProcesserService.class).getWorldProcesser().putMessage(player, message);
		}
	}

}
