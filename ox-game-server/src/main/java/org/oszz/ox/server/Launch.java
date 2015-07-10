package org.oszz.ox.server;

import org.oszz.ox.server.base.GameServer;

/**
 * GameServer 启动程序
 * @author ZZ
 *
 */
public class Launch {
	/**
	 * Game Server 的配置
	 */
	private static final String SERVER_CONFIG_FILE = "server.properties";

	public static void main(String[] args) throws Exception {
		GameServer gameServer = new GameServer();
		gameServer.init(SERVER_CONFIG_FILE);
		gameServer.start();
	}
}