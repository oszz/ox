package org.oszz.ox.core.processer;

import org.oszz.ox.core.Player;
import org.oszz.ox.core.message.IMessage;

/**
 * 消息处理器
 * @author ZZ
 *
 */
public interface IProcesser {

	public void handle(Player player, IMessage message);
}
