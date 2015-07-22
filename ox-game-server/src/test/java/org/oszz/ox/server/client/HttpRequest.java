package org.oszz.ox.server.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.oszz.ox.common.utils.HttpUtils;
import org.oszz.ox.core.message.IMessage;

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

	public static final String URL = "http://127.0.0.1:9973"; 
	
	/**
	 * 账号认证
	 * @author ZZ
	 * @param id
	 * @param pwd
	 * @throws IOException
	 */
	public void auth(String openId) throws IOException{
//		AuthProto.CGLogin.Builder cgLoginBuilder = AuthProto.CGLogin.newBuilder();
//		cgLoginBuilder.setOpenId(openId);
//		AuthProto.CGLogin cgLogin = cgLoginBuilder.build();
//		IMessage cgLoginMessage = new AuthProtoCGLoginMessage(cgLogin);
//		HttpUtils.read(URL, HttpUtils.POST_REQ, cgLoginMessage);
	}
	
	public void authGet(String openId) throws IOException{
		Map<String, String> paraMaps = new HashMap<String, String>();
		paraMaps.put("code", "1");
		paraMaps.put("openId", openId);
		
		byte[] respBytes =  HttpUtils.sendGetRequest(URL, paraMaps);
		System.out.println(new String(respBytes, "UTF-8"));
	}
	
}
