package org.oszz.ox.msg.auth;

import org.oszz.ox.core.message.AbstractMessageReceived;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 玩家登陆<br>
 * Auto Generator, Don't Modify .
 */
public class AuthProtoCGLoginMessage extends AbstractMessageReceived {

	public AuthProtoCGLoginMessage() {
		
	}

	public AuthProtoCGLoginMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return org.oszz.ox.gs.MessageCodeForGameServer.AUTH_PROTO_C_G_LOGIN;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.msg.auth.AuthProto.CGLogin.class;
	}

	@Override
	public String getHandlerMethodName() {
		return "cGLoginHnadler";
	}
}
