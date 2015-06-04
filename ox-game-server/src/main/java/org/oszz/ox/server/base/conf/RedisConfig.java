package org.oszz.ox.server.base.conf;

public class RedisConfig extends BaseConfig {

	private boolean isOpen = false;
	private String ip;
	private int port = 6379;
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	
}
