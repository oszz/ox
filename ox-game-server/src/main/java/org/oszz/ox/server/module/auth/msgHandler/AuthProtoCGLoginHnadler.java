package org.oszz.ox.server.module.auth.msgHandler;

import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.server.module.info.message.InfoProto;
import org.oszz.ox.server.module.info.message.InfoProtoGCErrorPromptMessage;

/**
 * 玩家登陆 <br>
 * Handler Auto Generator
 */
public class AuthProtoCGLoginHnadler implements IMessageHandler{

	@Override
	public void handle(IPlayer player, IMessage message){
		//TODO Handler Auto Generator
		System.out.println(message);
		
		InfoProto.GCErrorPrompt protoMsg = InfoProto.GCErrorPrompt.newBuilder().setContents("登陆成功").build();
		
		IMessage gcp = new InfoProtoGCErrorPromptMessage(protoMsg);
		player.sendMessage(gcp);
	}
	
}