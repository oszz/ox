package org.oszz.ox.gs.handler;

import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.core.player.IPlayer;

/**
 * Handler Auto Generator, Don't modify.
 */
public interface AuthProtoHnadler extends IMessageHandler{
	
	/**
	 * 玩家登陆
	 */
	public void cGLoginHnadler(IPlayer player, IMessage message);
	/**
	 * 玩家主动退出
	 */
	public void cGLogoutHnadler(IPlayer player, IMessage message);
		
}
