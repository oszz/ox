package org.oszz.ox.core.message;

import org.oszz.ox.core.IPlayer;

/**
 * 消息处理类的接口
 * @author ZZ
 *
 */
public interface IMessageHandler {

	/**
	 * 处理来自客户端的消息
	 * @author ZZ
	 * @param player 玩家
	 * @param message 消息
	 */
	public void handle(IPlayer player, IMessage message);
}
