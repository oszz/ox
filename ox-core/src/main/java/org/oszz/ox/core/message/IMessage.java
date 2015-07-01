package org.oszz.ox.core.message;

import net.sf.json.JSONObject;

import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.core.server.IAsynResponseProcesser;

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
	
	/**
	 * 设置消息的处理类
	 * @author ZZ
	 * @param msgHandler 消息的处理类
	 */
	public void setMsgHandler(IMessageHandler msgHandler);
	
	/**
	 * 执行消息<br>
	 * 其实是调用了{@link IMessageHandler#handle(IPlayer, IMessage)} 方法
	 * @author ZZ
	 * @param player 玩家
	 */
	public void execute(IPlayer player);
	
	/**
	 * 设置处理该消息的线程类型
	 * @author ZZ
	 * @param messageProcesserType 线程类型
	 */
	public void setMessageProcesserType(MessageProcesserType messageProcesserType);

	/**
	 * 返回处理该消息的线程类型
	 * @author ZZ
	 * @return 返回处理该消息的线程类型
	 */
	public MessageProcesserType getMessageProcesserType();

	/**
	 * 设置消息的异步响应处理器<br>
	 * 异步响应处理器:用于回调，写数据到客户端
	 * @author ZZ
	 * @param asynRespPro 异步响应处理器
	 */
	public void setAsynResponseProcesser(IAsynResponseProcesser asynRespPro);
	
	/**
	 * 返回消息的异步响应处理器<br>
	 * 异步响应处理器:用于回调，写数据到客户端
	 * @author ZZ
	 * @return 返回消息的异步响应处理器
	 */
	public IAsynResponseProcesser getAsynResponseProcesser();
	
//	public void toProtobufMessage(Map<String, String> paraMaps, Class<? extends GeneratedMessage> clazz) throws Exception;
	
	public void toProtobufMessage(JSONObject json, Class<? extends GeneratedMessage> clazz) throws Exception;
	
	public String toStringForBrowser();
}
