package org.oszz.ox.core.message;

import com.google.protobuf.MessageLite;

public abstract class AbstractMessage implements IMessage{

	private short code;
	private MessageLite messageLite;
	
	@Override
	public short getCode() {
		return this.code;
	}
	
	@Override
	public MessageLite toProtobufMessage(byte[] bytes, Class<? extends MessageLite> clazz) {
		
		return this.messageLite;
	}
}
