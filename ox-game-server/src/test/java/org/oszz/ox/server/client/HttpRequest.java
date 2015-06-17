package org.oszz.ox.server.client;

import java.io.IOException;

import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.AbstractMessage;
import org.oszz.ox.server.base.message.MessageCode;
import org.oszz.ox.server.module.auth.msg.AuthProto;
import org.oszz.ox.server.module.auth.msg.AuthProtoCGLoginMessage;

public class HttpRequest {
	
	/**
	 * 账号认证
	 */
	private static final int ACCOUNT_AUTH = 0;
	
	/**
	 * 游客认证
	 */
	private static final int GUEST_AUTH = 1;
	
	/**
	 * 账号与游客身份绑定
	 */
	private static final int BIND_ACCOUNT = 2;

	public static final String URL = "http://127.0.0.1:9973/ox"; 
	
	/**
	 * 账号认证
	 * @author ZZ
	 * @param id
	 * @param pwd
	 * @throws IOException
	 */
	public void auth(String openId) throws IOException{
		AuthProto.CGLogin.Builder cgLoginBuilder = AuthProto.CGLogin.newBuilder();
		cgLoginBuilder.setOpenId(openId);
		AuthProto.CGLogin cgLogin = cgLoginBuilder.build();
		IMessage cgLoginMessage = new AuthProtoCGLoginMessage(cgLogin);
		HttpUtils.read(URL, HttpUtils.POST_REQ, cgLoginMessage);
	}
	
}
