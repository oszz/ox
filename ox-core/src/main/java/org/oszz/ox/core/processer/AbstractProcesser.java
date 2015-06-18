package org.oszz.ox.core.processer;

import java.util.concurrent.LinkedBlockingQueue;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessage;
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
	public void putMessage(IPlayer player,IMessage message) {
		ProcesserMessage pm = new ProcesserMessage(player, message);
		msgQueue.offer(pm);
	}
	
	class ProcesserMessage{
		private IPlayer player;
		private IMessage message;
		
		public ProcesserMessage(IPlayer player,IMessage message){
			this.player = player;
			this.message =  message;
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
	}
}
