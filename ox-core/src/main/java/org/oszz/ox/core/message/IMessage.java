package org.oszz.ox.core.message;

import org.oszz.ox.core.IPlayer;

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
	
	public <T extends GeneratedMessage> T getProtobufMessage(Class<T> clazz);
	
	public Class<? extends GeneratedMessage> getProtobufMessageClass();
	
	public void setMsgHandler(IMessageHandler msgHandler);
	
	public void execute(IPlayer player);
	
	public void setMessageProcesserType(MessageProcesserType messageProcesserType);

	public MessageProcesserType getMessageProcesserType();

}
