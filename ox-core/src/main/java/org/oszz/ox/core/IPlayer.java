package org.oszz.ox.core;

import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.session.ISession;

/**
 * 玩家
 * @author ZZ
 *
 */
public interface IPlayer {
	
	public String getId();

	public void setSession(ISession session);
	
	public boolean isOnLine();
	
	public void sendMessage(IMessage message);
}
