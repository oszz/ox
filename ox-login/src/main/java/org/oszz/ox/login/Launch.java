package org.oszz.ox.login;

import org.oszz.ox.login.base.LoginServer;

/**
 * LoginServer 启动程序
 * @author ZZ
 *
 */
public class Launch {
	/**
	 * Game Server 的配置
	 */
	private static final String SERVER_CONFIG_FILE = "server.properties";

	public static void main(String[] args) throws Exception {
		LoginServer loginServer = new LoginServer(SERVER_CONFIG_FILE);
		loginServer.start();
	}
}