package org.oszz.ox.core.server;

import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.session.ISession;

/**
 * 请求来了的处理者接口
 * @author ZZ
 *
 */
public interface IHandler {
	
	public String getCharsetName();

	public <T> T getServerHandler();
	
	public void handle(ISession session, IMessage message);
	
	public void setDebug(boolean isDebug);
}
