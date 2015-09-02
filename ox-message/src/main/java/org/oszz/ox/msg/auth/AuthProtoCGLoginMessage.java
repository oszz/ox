package org.oszz.ox.msg.auth;

import org.oszz.ox.core.message.AbstractMessage;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 玩家登陆<br>
 * Auto Generator, Don't Modify .
 */
public class AuthProtoCGLoginMessage extends AbstractMessage {

	public AuthProtoCGLoginMessage() {
		
	}

	public AuthProtoCGLoginMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return org.oszz.ox.msg.MessageCodeForGameServer.AUTH_PROTO_C_G_LOGIN;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.msg.auth.AuthProto.CGLogin.class;
	}

}
