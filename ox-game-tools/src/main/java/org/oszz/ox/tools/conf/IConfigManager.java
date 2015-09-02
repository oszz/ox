package org.oszz.ox.tools.conf;

import java.util.List;
import java.util.Map;

import org.oszz.ox.tools.conf.msg.Message;
import org.oszz.ox.tools.constant.MessageCodeFileType;

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
	 * 返回已经分好类的消息列表<br>
	 * 注：一定要先初始化，调用{@link #init(String)}
	 * @author ZZ
	 * @return 返回消息列表
	 */
	public Map<MessageCodeFileType, List<Message>> getCodeFileMessages();
}
