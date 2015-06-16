package org.oszz.ox.server.base.conf;




/**
 * 服务端配置
 * @author ZZ
 *
 */
public class ServerConfig extends BaseConfig {
	
	private int serverType;//服务类型
	private boolean isDebug;//是否是debug 0-false,1-true
	private String version;//服务器版本
	private String serverId;//服务器ID
	
	private String language;//语言版本：中文、英文
	private String charset;//字符集
	
	private int asynThreadSize;//异步服务的线程池大小
	private int sceneNum;//场景线程的数量
	
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

	public int getAsynThreadSize() {
		return asynThreadSize;
	}

	public void setAsynThreadSize(int asynThreadSize) {
		this.asynThreadSize = asynThreadSize;
	}

	public int getSceneNum() {
		return sceneNum;
	}

	public void setSceneNum(int sceneNum) {
		this.sceneNum = sceneNum;
	}
}
