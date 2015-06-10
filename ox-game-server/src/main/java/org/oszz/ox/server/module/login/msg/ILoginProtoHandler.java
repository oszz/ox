package org.oszz.ox.server.module.login.msg;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessage;

public interface ILoginProtoHandler {

	public void cgLoginHandle(IPlayer player, IMessage message);
}
