package org.oszz.ox.server.module.auth.message;

import org.oszz.ox.core.message.AbstractMessage;
import org.oszz.ox.server.base.dom.MessageCode;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 玩家登陆后返回的消息<br>
 * Auto Generator, Don't Modify .
 * @author ZZ
 *
 */
public class AuthProtoGCLoginInfoMessage extends AbstractMessage {

	public AuthProtoGCLoginInfoMessage() {
		
	}

	public AuthProtoGCLoginInfoMessage(Message protoMsg) {
		super(protoMsg);
	}

	@Override
	public short getCode() {
		return MessageCode.AUTH_PROTO_G_C_LOGIN_INFO;
	}

	@Override
	public Class<? extends GeneratedMessage> getProtobufMessageClass() {
		return org.oszz.ox.server.module.auth.message.AuthProto.GCLoginInfo.class;
	}

}
