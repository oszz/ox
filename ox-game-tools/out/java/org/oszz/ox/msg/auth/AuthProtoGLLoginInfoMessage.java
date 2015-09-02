package org.oszz.ox.msg.auth;

import org.oszz.ox.core.message.AbstractMessage;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 玩家登陆后返回的消息GL<br>
 * Auto Generator, Don't Modify .
 */
public class AuthProtoGLLoginInfoMessage extends AbstractMessage {

	public AuthProtoGLLoginInfoMessage() {
		
	}

	public AuthProtoGLLoginInfoMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return org.oszz.ox.msg.MessageCodeForLoginServer.AUTH_PROTO_G_L_LOGIN_INFO;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.msg.auth.AuthProto.GLLoginInfo.class;
	}

}
