package org.oszz.ox.msg.auth;

import org.oszz.ox.core.message.AbstractMessage;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 玩家主动退出<br>
 * Auto Generator, Don't Modify .
 */
public class AuthProtoCGLogoutMessage extends AbstractMessage {

	public AuthProtoCGLogoutMessage() {
		
	}

	public AuthProtoCGLogoutMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return org.oszz.ox.msg.MessageCodeForGameServer.AUTH_PROTO_C_G_LOGOUT;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.msg.auth.AuthProto.CGLogout.class;
	}

}
