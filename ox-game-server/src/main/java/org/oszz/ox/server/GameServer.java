package org.oszz.ox.server;

import org.oszz.ox.server.base.Globals;
import org.oszz.ox.server.base.conf.DBConfig;
import org.oszz.ox.server.base.conf.JettyServerConfig;
import org.oszz.ox.server.base.conf.MinaServerConfig;
import org.oszz.ox.server.base.conf.ServerConfig;

/**
 * GameServer 启动程序
 * @author ZZ
 *
 */
public class GameServer {
	/**
	 * Game Server 的配置
	 */
	private static final String SERVER_CONFIG_FILE = "server.properties";

	public static void main(String[] args) {
		init();
	}
	private static void init(){
		Globals.init(SERVER_CONFIG_FILE);
	}
}
