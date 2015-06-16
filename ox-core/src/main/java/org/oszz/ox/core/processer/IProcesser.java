package org.oszz.ox.core.processer;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;


/**
 * 消息处理线程
 * @author ZZ
 *
 */
public interface IProcesser {

	public void start();
	
	public void stop();
	
	public void putMessage(IMessageHandler msgHandler, IPlayer palyer, IMessage message);
}
