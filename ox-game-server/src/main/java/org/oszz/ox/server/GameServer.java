package org.oszz.ox.server;

import org.oszz.ox.core.server.IServer;
import org.oszz.ox.core.server.jetty.IJettySessionFactory;
import org.oszz.ox.core.server.jetty.JettyHashSessionFactory;
import org.oszz.ox.core.server.jetty.JettyServer;
import org.oszz.ox.server.base.Globals;
import org.oszz.ox.server.base.conf.JettyServerConfig;
import org.oszz.ox.server.base.conf.ServerConfig;
import org.oszz.ox.server.base.handler.OXServerHandler;

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

	public static void main(String[] args) throws Exception {
		init();
		start();
	}
	private static void init(){
		Globals.init(SERVER_CONFIG_FILE);
	}
	
	private static void start() throws Exception {
		startJettyServer();
	}
	
	private static void startJettyServer() throws Exception {
		JettyServerConfig jsConfig = Globals.getCofing(JettyServerConfig.class);
		ServerConfig serverCongfig = Globals.getCofing(ServerConfig.class);
		
		int port = jsConfig.getPort();
		IServer jsServer = new JettyServer(serverCongfig.isDebug(), serverCongfig.getCharset());
		IJettySessionFactory jettySessionFactory = new JettyHashSessionFactory();
		jsServer.setSessionFactory(jettySessionFactory);//一定要先setSessionFactory
		
		jsServer.addContext(port, new String[]{"/"});
		
		OXServerHandler oxsHandler = new OXServerHandler(jsConfig.getResponseTimeout());
		jsServer.setHandler(oxsHandler);
		jsServer.start();
	}
}
