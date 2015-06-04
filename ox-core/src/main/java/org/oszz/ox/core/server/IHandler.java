package org.oszz.ox.core.server;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.filter.IFilterChain;

/**
 * 请求来了的处理者接口
 * @author ZZ
 *
 */
public interface IHandler {
	
	public String getCharsetName();

	public <T> T getServerHandler();
	
	public <T> void handle(IPlayer palyer, T para);
	
	public void setDebug(boolean isDebug);
	
	public void setFilterChain(IFilterChain filterChain);
}
