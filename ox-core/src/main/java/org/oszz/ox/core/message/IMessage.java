package org.oszz.ox.core.message;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 消息接口
 * @author ZZ
 *
 */
public interface IMessage {

	public short getCode();
	
	public void toProtobufMessage(byte[] bytes, Class<? extends GeneratedMessage> clazz);
	
	public byte[] toBytes();
	
	public Message getProtobufMessage();
	
	public <T> T getProtobufMessage(Class<T> calzz);

}
