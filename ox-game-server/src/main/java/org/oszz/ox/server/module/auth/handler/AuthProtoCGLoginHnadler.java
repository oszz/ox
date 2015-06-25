package org.oszz.ox.server.module.auth.handler;

import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.server.base.Globals;
import org.oszz.ox.server.base.cache.CacheService;
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
		String openId = cgLogin.getOpenId();
		System.out.println("openId: " + openId);
		
		CacheService cacheService = Globals.getService(CacheService.class);
		cacheService.login(openId);
		
//		player.sendMessage(message);
		message = InfoMessageBuilder.buildGCErrorPrompt("登陆成功");
		player.sendMessage(message);
	}
	
}
