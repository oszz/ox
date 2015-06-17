package org.oszz.ox.core.processer;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.server.req.IAsynRequest;


/**
 * 消息处理线程
 * @author ZZ
 *
 */
public interface IProcesser {

	public void start();
	
	public void stop();
	
	public void putMessage(IPlayer player, IMessage message, IAsynRequest asynReq);
}
