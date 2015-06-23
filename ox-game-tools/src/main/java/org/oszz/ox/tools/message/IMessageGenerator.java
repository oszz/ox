package org.oszz.ox.tools.message;

import java.io.File;

import org.oszz.ox.tools.generator.IGenerator;

/**
 * 消息生成器
 * @author ZZ
 *
 */
public interface IMessageGenerator extends IGenerator{

	/**
	 * 返回所有的proto文件
	 * @author ZZ
	 * @return
	 */
	public File[] getProtoFiles();
}
