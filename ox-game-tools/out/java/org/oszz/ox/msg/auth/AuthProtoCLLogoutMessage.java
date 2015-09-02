package org.oszz.ox.msg.auth;

import org.oszz.ox.core.message.AbstractMessage;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 玩家主动退出CL<br>
 * Auto Generator, Don't Modify .
 */
public class AuthProtoCLLogoutMessage extends AbstractMessage {

	public AuthProtoCLLogoutMessage() {
		
	}

	public AuthProtoCLLogoutMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return org.oszz.ox.msg.MessageCodeForLoginServer.AUTH_PROTO_C_L_LOGOUT;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.msg.auth.AuthProto.CLLogout.class;
	}

}
