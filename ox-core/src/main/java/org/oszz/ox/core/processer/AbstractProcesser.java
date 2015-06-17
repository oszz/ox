package org.oszz.ox.core.processer;

import java.util.concurrent.LinkedBlockingQueue;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.server.req.IAsynRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractProcesser implements IProcesser {
	protected static final Logger log = LoggerFactory.getLogger("processer");
	/**
	 * 消息队列
	 */
	protected LinkedBlockingQueue<ProcesserMessage> msgQueue;
	
	
	public AbstractProcesser(){
		msgQueue = new LinkedBlockingQueue<AbstractProcesser.ProcesserMessage>();
	}
	
	@Override
	public void putMessage(IPlayer player,IMessage message, IAsynRequest asynReq) {
		ProcesserMessage pm = new ProcesserMessage(player, message, asynReq);
		msgQueue.offer(pm);
	}
	
	class ProcesserMessage{
		private IPlayer player;
		private IMessage message;
		private IAsynRequest asynReq;
		
		public ProcesserMessage(IPlayer player,IMessage message, IAsynRequest asynReq){
			this.player = player;
			this.message =  message;
			this.asynReq = asynReq;
		}
		
		public IPlayer getPlayer() {
			return player;
		}

		public void setPlayer(IPlayer player) {
			this.player = player;
		}

		public IMessage getMessage() {
			return message;
		}
		public void setMessage(IMessage message) {
			this.message = message;
		}

		public IAsynRequest getAsynReq() {
			return asynReq;
		}

		public void setAsynReq(IAsynRequest asynReq) {
			this.asynReq = asynReq;
		}
		
	}

}
