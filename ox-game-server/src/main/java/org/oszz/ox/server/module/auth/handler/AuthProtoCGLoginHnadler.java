package org.oszz.ox.server.module.auth.handler;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;

/**
 * 玩家登陆 <br>
 * Handler Auto Generator
 */
public class AuthProtoCGLoginHnadler implements IMessageHandler{

	@Override
	public void handle(IPlayer player, IMessage message){
		System.out.println(player);
		System.out.println(message);
	}
	
}
