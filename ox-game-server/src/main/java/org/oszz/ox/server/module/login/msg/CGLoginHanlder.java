package org.oszz.ox.server.module.login.msg;

import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.core.message.MessageCodeMapping;
import org.oszz.ox.server.module.login.msg.LoginProto.CGLogin;

import com.google.protobuf.MessageLite;

public class CGLoginHanlder implements IMessageHandler {

	@Override
	public void handle(MessageLite msgLite) {
		Short code = 9;
		MessageCodeMapping.getInstance().register(code, CGLogin.class, this);
	}

}
