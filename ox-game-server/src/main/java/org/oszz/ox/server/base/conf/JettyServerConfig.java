package org.oszz.ox.server.base.conf;

/**
 * Jetty服务配置
 * @author ZZ
 *
 */
public class JettyServerConfig  extends BaseConfig {

	private int port;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
}
