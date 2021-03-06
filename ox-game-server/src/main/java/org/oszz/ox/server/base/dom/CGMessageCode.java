package org.oszz.ox.server.base.dom;

public interface CGMessageCode {
	/**
	 * 玩家登陆
	 */
	public static final short AUTH_PROTO_C_G_LOGIN = 0x0001;
	/**
	 * 玩家登陆后返回的消息
	 */
	public static final short AUTH_PROTO_G_C_LOGIN_INFO = 0x0002;
	/**
	 * 玩家主动退出
	 */
	public static final short AUTH_PROTO_C_G_LOGOUT = 0x0003;
	/**
	 * 错误信息提示
	 */
	public static final short INFO_PROTO_G_C_ERROR_PROMPT = 0x0004;
}
