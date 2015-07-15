package org.oszz.ox.login.base.conf;

import org.oszz.ox.core.conf.BaseConfig;

/**
 * Jetty服务配置
 * @author ZZ
 *
 */
public class JettyServerConfig  extends BaseConfig {

	private int port;//端口
	private int sessionTimeout=600;//Jetty的session超时时间（单位秒）
	private int responseTimeout=10;//Jetty的response处理超时时间（单位秒）
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getSessionTimeout() {
		return sessionTimeout;
	}
	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	public int getResponseTimeout() {
		return responseTimeout;
	}
	public void setResponseTimeout(int responseTimeout) {
		this.responseTimeout = responseTimeout;
	}
	
	
}
