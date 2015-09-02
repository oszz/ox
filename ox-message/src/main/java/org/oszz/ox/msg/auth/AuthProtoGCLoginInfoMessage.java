package org.oszz.ox.msg.auth;

import org.oszz.ox.core.message.AbstractMessage;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 玩家登陆后返回的消息<br>
 * Auto Generator, Don't Modify .
 */
public class AuthProtoGCLoginInfoMessage extends AbstractMessage {

	public AuthProtoGCLoginInfoMessage() {
		
	}

	public AuthProtoGCLoginInfoMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return org.oszz.ox.msg.MessageCodeForGameServer.AUTH_PROTO_G_C_LOGIN_INFO;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.msg.auth.AuthProto.GCLoginInfo.class;
	}

}
