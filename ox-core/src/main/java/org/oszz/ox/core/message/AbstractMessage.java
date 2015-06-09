package org.oszz.ox.core.message;

import com.google.protobuf.MessageLite;

public abstract class AbstractMessage implements IMessage{

	private short code;
	private MessageLite msgLite;
	
	public AbstractMessage(short code, MessageLite msgLite){
		this.code = code;
		this.msgLite = msgLite;
	}
	
	@Override
	public short getCode() {
		return this.code;
	}
	
	@Override
	public MessageLite getProtobufMessage() {
		return this.msgLite;
	}
}
