package org.oszz.ox.core.message;

import com.google.protobuf.MessageLite;

/**
 * 消息接口
 * @author ZZ
 *
 */
public interface IMessage {

	/**
	 * 执行
	 * @author ZZ
	 */
	public void execute();
	
	
	public short getCode();
	
	public int getLength();
	
	public MessageLite getProtobufMessage();
}
