package org.oszz.ox.core.processer;

import java.util.concurrent.LinkedBlockingQueue;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;
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
	public void putMessage(IMessageHandler msgHandler, IPlayer palyer,
			IMessage message) {
		ProcesserMessage pm = new ProcesserMessage(msgHandler, palyer, message);
		msgQueue.offer(pm);
	}
	
	class ProcesserMessage{
		private IMessageHandler msgHandler;
		private IPlayer palyer;
		private IMessage message;
		
		public ProcesserMessage(IMessageHandler msgHandler, IPlayer palyer,
				IMessage message){
			this.msgHandler = msgHandler;
			this.palyer = palyer;
			this.message =  message;
		}
		
		public IMessageHandler getMsgHandler() {
			return msgHandler;
		}
		public void setMsgHandler(IMessageHandler msgHandler) {
			this.msgHandler = msgHandler;
		}
		public IPlayer getPalyer() {
			return palyer;
		}
		public void setPalyer(IPlayer palyer) {
			this.palyer = palyer;
		}
		public IMessage getMessage() {
			return message;
		}
		public void setMessage(IMessage message) {
			this.message = message;
		}
	}

}
