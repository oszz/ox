package org.oszz.ox.core;

import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.session.ISession;

/**
 * 玩家
 * @author ZZ
 *
 */
public interface IPlayer {
	
	/**
	 * 返回玩家id
	 * @author ZZ
	 * @return
	 */
	public String getId();

	/**
	 * 设置session
	 * @author ZZ
	 * @param session
	 */
	public void setSession(ISession session);
	
	/**
	 * 玩家是否在线
	 * @author ZZ
	 * @return 如果在线,则返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public boolean isOnLine();
	
	public void sendMessage(IMessage message);
}
