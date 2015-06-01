package org.oszz.ox.server.base.conf;

import org.oszz.ox.common.utils.ClassUtils;
/**
 * Jetty服务配置
 * @author ZZ
 *
 */
public class JettyServerConfig {

	private int port;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return ClassUtils.toString(this);
	}
}
