package org.oszz.ox.server.module.auth.handler;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.server.module.auth.msg.AuthProto;

/**
 * 玩家登陆 <br>
 * Handler Auto Generator
 */
public class AuthProtoCGLoginHnadler implements IMessageHandler{

	@Override
	public void handle(IPlayer player, IMessage message){
		//TODO Handler Auto Generator
		AuthProto.CGLogin cgLogin = message.getProtobufMessage(AuthProto.CGLogin.class);
		System.out.println(cgLogin.getOpenId());
		System.out.println("ddddd");
	}
	
}
