package org.oszz.ox.core.message;

import java.nio.ByteBuffer;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.core.conf.DefaultConfig;
import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.core.server.IAsynResponseProcesser;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.Builder;
import com.google.protobuf.Message;

public abstract class AbstractMessage implements IMessage{

	protected Message protoMsg;
	
	private IMessageHandler msgHandler;
	
	private MessageProcesserType messageProcesserType;
	
	private IAsynResponseProcesser asynRespPro;
	
	public AbstractMessage(){
	}
	
	public AbstractMessage(Message protoMsg){
		this.protoMsg = protoMsg;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void toProtobufMessage(byte[] bytes, Class<? extends GeneratedMessage> clazz) {
		try {
			Builder builder = (Builder)ClassUtils.invokeStaticMethod(clazz, DefaultConfig.PROTO_BUF_NEW_BUILDER_METHOD_NAME.getValue());
			this.protoMsg = builder.mergeFrom(bytes).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public Message getProtobufMessage() {
		return this.protoMsg;
	}
	
	@Override
	public byte[] toBytes() {
		byte[] protoMsgBytes = protoMsg.toByteArray();
		int protoMsgLength = protoMsgBytes.length;
		
		int capacity = protoMsgLength + 4 + 2;//4是length的字节数，2是code的字节数
		ByteBuffer byteBuff = ByteBuffer.allocate(capacity);
		byteBuff.putShort(this.getCode());
		byteBuff.putInt(protoMsgLength);
		byteBuff.put(protoMsgBytes);
		
		return byteBuff.array();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends GeneratedMessage> T getProtobufMessage(Class<T> clazz) {
		return (T)this.getProtobufMessage();
	}
	
	@Override
	public void setMsgHandler(IMessageHandler msgHandler) {
		this.msgHandler = msgHandler;
	}
	
	@Override
	public void execute(IPlayer player) {
		this.msgHandler.handle(player, this);
	}
	
	@Override
	public MessageProcesserType getMessageProcesserType() {
		return this.messageProcesserType;
	}
	
	@Override
	public void setMessageProcesserType(
			MessageProcesserType messageProcesserType) {
		this.messageProcesserType = messageProcesserType;
	}
	
	@Override
	public IAsynResponseProcesser getAsynResponseProcesser() {
		return asynRespPro;
	}
	
	@Override
	public void setAsynResponseProcesser(IAsynResponseProcesser asynRespPro) {
		this.asynRespPro = asynRespPro;
	}
}
