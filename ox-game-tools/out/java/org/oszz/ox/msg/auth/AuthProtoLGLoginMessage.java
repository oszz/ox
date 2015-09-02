package org.oszz.ox.msg.auth;

import org.oszz.ox.core.message.AbstractMessage;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 玩家登陆LG<br>
 * Auto Generator, Don't Modify .
 */
public class AuthProtoLGLoginMessage extends AbstractMessage {

	public AuthProtoLGLoginMessage() {
		
	}

	public AuthProtoLGLoginMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return org.oszz.ox.msg.MessageCodeForLoginServer.AUTH_PROTO_L_G_LOGIN;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.msg.auth.AuthProto.LGLogin.class;
	}

}
