package org.oszz.ox.core.message;

import net.sf.json.JSONObject;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/**
 * 消息接口
 * @author ZZ
 *
 */
public interface IMessage {

	/**
	 * 返回消息编码
	 * @author ZZ
	 * @return 返回消息编码
	 */
	public short getCode();
	
	/**
	 * 将byte数组按 {@link GeneratedMessage} 类型转成protoBuf类
	 * @author ZZ
	 * @param bytes byte数组
	 * @param clazz {@link GeneratedMessage} 类型
	 */
	public void toProtobufMessage(byte[] bytes, Class<? extends GeneratedMessage> clazz);
	
	/**
	 * 返回消息的 {@link Byte} 数组<br>
	 * 这个二进制数据包含了消息编码、消息长度、protoBuf类bytes
	 * @author ZZ
	 * @return 返回消息的 {@link Byte} 数组<br>
	 */
	public byte[] toBytes();
	
	/**
	 * 返回消息包含的protoBuf类 {@link Message}
	 * @author ZZ
	 * @return 返回消息包含的protoBuf类
	 */
	public Message getProtobufMessage();
	
	/**
	 * 按 {@link GeneratedMessage} 类型返回protoBuf类
	 * @author ZZ
	 * @param clazz {@link GeneratedMessage} 类型
	 * @return 返回protoBuf类
	 */
	public <T extends GeneratedMessage> T getProtobufMessage(Class<T> clazz);
	
	/**
	 * 返回消息包含的protoBuf类的类型： {@link GeneratedMessage}
	 * @author ZZ
	 * @return 返回消息包含的protoBuf类的类型
	 */
	public Class<? extends GeneratedMessage> getProtobufMessageClass();
	
	public void toProtobufMessage(JSONObject json, Class<? extends GeneratedMessage> clazz) throws Exception;
	
	public String toStringForBrowser();
	
	public JSONObject toJson();
}
