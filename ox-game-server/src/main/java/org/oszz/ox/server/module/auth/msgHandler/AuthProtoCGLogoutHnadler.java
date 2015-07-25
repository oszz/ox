package org.oszz.ox.server.module.auth.msgHandler;

import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.server.module.info.message.InfoProto;
import org.oszz.ox.server.module.info.message.InfoProtoGCErrorPromptMessage;

/**
 * 玩家主动退出 <br>
 * Handler Auto Generator
 */
public class AuthProtoCGLogoutHnadler implements IMessageHandler{

	@Override
	public void handle(IPlayer player, IMessage message){
		InfoProto.GCErrorPrompt gcep = InfoProto.GCErrorPrompt.newBuilder().setContents("登出，，，，成功").build();
		System.out.println("dddddddddddddddddddd");
		InfoProtoGCErrorPromptMessage ipepm = new InfoProtoGCErrorPromptMessage(gcep);
		player.sendMessage(ipepm);
		player.sendMessage(message);		
	}
	
}
