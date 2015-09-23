package org.oszz.ox.msg.auth;

import org.oszz.ox.core.message.AbstractMessageReceived;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 玩家登陆后返回的消息<br>
 * Auto Generator, Don't Modify .
 */
public class AuthProtoGCLoginInfoMessage extends AbstractMessageReceived {

	public AuthProtoGCLoginInfoMessage() {
		
	}

	public AuthProtoGCLoginInfoMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return org.oszz.ox.gs.MessageCodeForGameServer.AUTH_PROTO_G_C_LOGIN_INFO;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.msg.auth.AuthProto.GCLoginInfo.class;
	}

	@Override
	public String getHandlerMethodName() {
		return "gCLoginInfoHnadler";
	}
}
