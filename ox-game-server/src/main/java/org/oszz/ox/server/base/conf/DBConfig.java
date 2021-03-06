package org.oszz.ox.server.base.conf;

import org.oszz.ox.core.conf.BaseConfig;


/**
 * 数据库配置
 * @author ZZ
 *
 */
public class DBConfig extends BaseConfig {
	private String driver;
	private String url;
	private String username;
	private String password;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
