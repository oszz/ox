package org.oszz.ox.tools.conf;

import java.util.List;
import java.util.Map;

import org.oszz.ox.tools.conf.msg.Message;
import org.oszz.ox.tools.constant.MessageTypeCodeConfig;

public interface IConfigManager {

	/**
	 * 初始化
	 * @author ZZ
	 * @param msgXMLFilePath 消息的XML配置
	 */
	public void init(String msgXMLFilePath);
	
	/**
	 * 返回消息列表<br>
	 * 注：一定要先初始化，调用{@link #init(String)}
	 * @author ZZ
	 * @return 返回消息列表
	 */
	public List<Message> getMessages();
	
	/**
	 * 返回已经分好类的消息列表,按消息类型（GC\LG\CG 等）分类<br>
	 * 注：一定要先初始化，调用{@link #init(String)}
	 * @author ZZ
	 * @return 返回消息列表
	 */
	public Map<MessageTypeCodeConfig, List<Message>> getMessagesByType();
	
	/**
	 * 返回已经分好类的消息列表,按业务类型分类<br>
	 * 注：一定要先初始化，调用{@link #init(String)}
	 * @author ZZ
	 * @return 返回消息列表
	 */
	public Map<String, List<Message>> getMessagesByProto();
}
