package org.oszz.ox.core.message;

import java.nio.ByteBuffer;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.core.conf.DefaultConfig;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.Builder;
import com.google.protobuf.Message;

public class OXMessage implements IMessage{

	protected short code;
	protected Message protoMsg;
	
	public OXMessage(){
	}
	
	public OXMessage(Short code){
		this.code = code;
	}
	
	public OXMessage(Short code, Message protoMsg){
		this.code = code;
		this.protoMsg = protoMsg;
	}
	
	@Override
	public short getCode() {
		return this.code;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void toProtobufMessage(byte[] bytes, Class<? extends GeneratedMessage> clazz) {
		try {
			Builder builder = (Builder)ClassUtils.invokeStaticMethod(clazz, DefaultConfig.PROTO_BUF_NEW_BUILDER_METHOD_NAME.getValue(), null);
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
		byteBuff.putShort(this.code);
		byteBuff.putInt(protoMsgLength);
		byteBuff.put(protoMsgBytes);
		
		return byteBuff.array();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getProtobufMessage(Class<T> calzz) {
		return (T)getProtobufMessage();
	}
}
