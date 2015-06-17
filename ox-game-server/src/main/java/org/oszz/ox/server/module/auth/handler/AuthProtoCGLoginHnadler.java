package org.oszz.ox.server.module.auth.handler;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.server.module.auth.msg.AuthProto;
import org.oszz.ox.server.module.info.InfoMessageBuilder;
import org.oszz.ox.server.module.info.msg.InfoProto;

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
		
//		player.sendMessage(message);
		message = InfoMessageBuilder.buildGCErrorPrompt("登陆失败");
		System.out.println(message);
		player.sendMessage(message);
		
	}
	
}
