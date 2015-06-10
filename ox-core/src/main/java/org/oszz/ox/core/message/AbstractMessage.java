package org.oszz.ox.core.message;

import com.google.protobuf.GeneratedMessage;

public abstract class AbstractMessage implements IMessage{

	private short code;
	private GeneratedMessage generatedMessage;
	
	public AbstractMessage(short code, GeneratedMessage generatedMessage){
		this.code = code;
		this.generatedMessage = generatedMessage;
	}
	
	@Override
	public short getCode() {
		return this.code;
	}
	
	@Override
	public GeneratedMessage getProtobufMessage() {
		return this.generatedMessage;
	}
}
