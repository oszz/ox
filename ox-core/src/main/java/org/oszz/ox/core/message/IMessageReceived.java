package org.oszz.ox.core.message;

import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.core.server.IAsynResponseProcesser;

public interface IMessageReceived extends IMessage {

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
	
	/**
	 * 返回处理该消息的方法名称
	 * @author ZZ
	 * @return
	 */
	public String getHandlerMethodName();
}
