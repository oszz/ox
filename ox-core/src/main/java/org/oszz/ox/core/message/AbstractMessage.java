package org.oszz.ox.core.message;

import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.StringUtils;
import org.oszz.ox.core.conf.DefaultConfig;
import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.core.server.IAsynResponseProcesser;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.Builder;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;

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
	
//	@SuppressWarnings("rawtypes")
//	@Override
//	public void toProtobufMessage(Map<String, String> paraMaps,
//			Class<? extends GeneratedMessage> clazz) throws Exception {
//		Builder builder = (Builder)ClassUtils.invokeStaticMethod(clazz, DefaultConfig.PROTO_BUF_NEW_BUILDER_METHOD_NAME.getValue());
//		for(Map.Entry<String, String> paraEntry : paraMaps.entrySet()){
//			String fieldName = paraEntry.getKey();//属性名称
//			String value = paraEntry.getValue();//属性名称
//			Method setterMethod = ClassUtils.getSetterMethod(builder.getClass(), fieldName);
//			Object paraObj = ClassUtils.getMethodNeedValue(setterMethod, value);
//			ClassUtils.invokeMethod(builder, setterMethod, paraObj);
//		}
//		this.protoMsg = builder.build();
//		System.out.println(this);
//	}
	
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
