package org.oszz.ox.server.module.auth.message;

import org.oszz.ox.core.message.AbstractMessage;
import org.oszz.ox.server.base.message.MessageCode;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 玩家登陆<br>
 * Auto Generator, Don't Modify .
 * @author ZZ
 *
 */
public class AuthProtoCGLoginMessage extends AbstractMessage {

	public AuthProtoCGLoginMessage() {
		
	}

	public AuthProtoCGLoginMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return MessageCode.AUTH_PROTO_C_G_LOGIN;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.server.module.auth.message.AuthProto.CGLogin.class;
	}

}
