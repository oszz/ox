package org.oszz.ox.server.base.utils;

import org.oszz.ox.server.base.Globals;
import org.oszz.ox.server.base.conf.ServerConfig;

/**
 * 账号id工具类
 * @author ZZ
 *
 */
public class AccountIdUtils {

	/**
	 * 返回一个唯一的账号id
	 * @author ZZ
	 * @return
	 */
	public static String getId(){
		ServerConfig serverCongfig = Globals.getCofing(ServerConfig.class);
		String serverId = serverCongfig.getServerId();
		String id = serverId + System.currentTimeMillis();//先这样计算着
		return id;
	}
}
