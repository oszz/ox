package org.oszz.ox.core.message;

import com.google.protobuf.MessageLite;

public interface IMessageHandler {

	public void handle(MessageLite msgLite);
}
