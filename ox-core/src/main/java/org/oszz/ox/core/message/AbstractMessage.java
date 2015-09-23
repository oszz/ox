package org.oszz.ox.core.message;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.StringUtils;
import org.oszz.ox.core.conf.DefaultConfig;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.Builder;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;

public abstract class AbstractMessage implements IMessage{

	protected Message protoMsg;
	
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
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public void toProtobufMessage(JSONObject json,
			Class<? extends GeneratedMessage> clazz) throws Exception {
		Builder builder = (Builder)ClassUtils.invokeStaticMethod(clazz, DefaultConfig.PROTO_BUF_NEW_BUILDER_METHOD_NAME.getValue());
		JsonFormat.merge(json.toString(), builder);
		this.protoMsg = builder.build();
	}
	
	@Override
	public String toString() {
		return toJson().toString();
	}
	
	@Override
	public String toStringForBrowser() {
		JSONObject json = toJson();
		return json.toString(4, 2);
	}
	
	@Override
	public JSONObject toJson() {
		Message protobufMsg = getProtobufMessage();
		String str = JsonFormat.printToString(protobufMsg);
		String codeHex = StringUtils.toHex(this.getCode());
		
		JSONObject protoMsgJson = JSONObject.fromObject(str);
		
		JSONObject json = new JSONObject();
		json.put("CODE", codeHex);
		json.put("TYPE", ClassUtils.getClassName(protobufMsg.getClass()));
		json.put("MSG", protoMsgJson);
		return json;
	}
}
