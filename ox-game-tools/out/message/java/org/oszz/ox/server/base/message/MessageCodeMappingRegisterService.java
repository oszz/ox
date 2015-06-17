package org.oszz.ox.server.base.message;

import org.oszz.ox.core.message.MessageCodeMapping;
import org.oszz.ox.core.message.MessageProcesserType;

/**
 * 消息编码与消息类、消息处理类的对应关系<br>
 * Auto Generator, Don't Modify .
 * @author ZZ
 *
 */
public class MessageCodeMappingRegisterService {

	public void init(){
		//玩家登陆
		MessageCodeMapping.getInstance().register(MessageCode.AUTH_PROTO_C_G_LOGIN, org.oszz.ox.server.module.auth.msg.AuthProtoCGLoginMessage.class, new org.oszz.ox.server.module.auth.handler.AuthProtoCGLoginHnadler(), MessageProcesserType.ASYN);
		//玩家主动退出
		MessageCodeMapping.getInstance().register(MessageCode.AUTH_PROTO_C_G_LOGOUT, org.oszz.ox.server.module.auth.msg.AuthProtoCGLogoutMessage.class, new org.oszz.ox.server.module.auth.handler.AuthProtoCGLogoutHnadler(), MessageProcesserType.SCENE);
		}
}
