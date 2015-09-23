package org.oszz.ox.core.message;

import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.core.server.IAsynResponseProcesser;

public abstract class AbstractMessageReceived extends AbstractMessage implements
		IMessageReceived {
	
	private IMessageHandler msgHandler;
	
	private MessageProcesserType messageProcesserType;
	
	private IAsynResponseProcesser asynRespPro;

	@Override
	public void setMsgHandler(IMessageHandler msgHandler) {
		this.msgHandler = msgHandler;
	}
	
	@Override
	public void execute(IPlayer player) {
		this.msgHandler.handle(player, this);
	}
	
	@Override
	public MessageProcesserType getMessageProcesserType() {
		return this.messageProcesserType;
	}
	
	@Override
	public void setMessageProcesserType(
			MessageProcesserType messageProcesserType) {
		this.messageProcesserType = messageProcesserType;
	}
	
	@Override
	public IAsynResponseProcesser getAsynResponseProcesser() {
		return asynRespPro;
	}
	
	@Override
	public void setAsynResponseProcesser(IAsynResponseProcesser asynRespPro) {
		this.asynRespPro = asynRespPro;
	}
	
}
