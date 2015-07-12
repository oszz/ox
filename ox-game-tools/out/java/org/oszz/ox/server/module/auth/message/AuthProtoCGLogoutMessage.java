package org.oszz.ox.server.module.auth.message;

import org.oszz.ox.core.message.AbstractMessage;
import org.oszz.ox.server.base.dom.MessageCode;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 玩家主动退出<br>
 * Auto Generator, Don't Modify .
 * @author ZZ
 *
 */
public class AuthProtoCGLogoutMessage extends AbstractMessage {

	public AuthProtoCGLogoutMessage() {
		
	}

	public AuthProtoCGLogoutMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return MessageCode.AUTH_PROTO_C_G_LOGOUT;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.server.module.auth.message.AuthProto.CGLogout.class;
	}

}
