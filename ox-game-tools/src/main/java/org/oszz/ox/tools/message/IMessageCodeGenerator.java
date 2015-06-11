package org.oszz.ox.tools.message;

import java.util.List;

/**
 * MessageCode相关的类文件生成器接口
 * @author ZZ
 *
 */
public interface IMessageCodeGenerator extends IMessageGenerator {
	
	/**
	 * 返回MessageCode的配置
	 * @author ZZ
	 * @return 返回MessageCode的配置
	 */
	public List<MessageCodeConf> getMsgCodeConfs();
}
