package org.oszz.ox.core.server;

import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.player.IPlayer;

/**
 * 请求来了的处理者接口
 * @author ZZ
 *
 */
public interface IRequestHandler {
	
	public String getCharsetName();

	public void requestHandle(IPlayer player, IMessage message);
	
	public void setDebug(boolean isDebug);
}
