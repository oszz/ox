package org.oszz.ox.server.base.conf;

import org.oszz.ox.common.utils.ClassUtils;



/**
 * 服务端配置
 * @author ZZ
 *
 */
public class ServerConfig {
	
	private int serverType;//服务类型
	private boolean isDebug;//是否是debug 0-false,1-true
	private String version;//服务器版本
	private String serverId;//服务器ID
	
	private String language;//语言版本：中文、英文
	private String charset;
	

	public int getServerType() {
		return serverType;
	}

	public void setServerType(int serverType) {
		this.serverType = serverType;
	}


	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Override
	public String toString() {
		return ClassUtils.toString(this);
	}
}
