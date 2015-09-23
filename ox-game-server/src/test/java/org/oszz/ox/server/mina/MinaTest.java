package org.oszz.ox.server.mina;

import org.oszz.ox.core.server.mina.client.MinaClient;
import org.oszz.ox.server.module.auth.message.AuthProto;

public class MinaTest {

	public static void main(String[] args) throws Exception {
		MinaClient mc = new MinaClient("127.0.0.1", 1111, "utf-8");
		mc.connect();
		
		AuthProto.CGLogin.Builder cgLoginBuilder = AuthProto.CGLogin.newBuilder();
		cgLoginBuilder.setOpenId("openId开发的");
		AuthProto.CGLogin cgLogin = cgLoginBuilder.build();
//		IMessage cgLoginMessage = new AuthProtoCGLoginMessage(cgLogin);
		
//		mc.send(cgLoginMessage);
	}
}
