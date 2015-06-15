package org.oszz.ox.core.message;

import com.google.protobuf.MessageLite;

/**
 * 消息接口
 * @author ZZ
 *
 */
public interface IMessage {

	public short getCode();
	
	public MessageLite toProtobufMessage(byte[] bytes, Class<? extends MessageLite> clazz);
	
	public byte[] toBytes();

}
