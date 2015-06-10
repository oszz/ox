package org.oszz.ox.tools.message;

import java.io.File;

import org.oszz.ox.tools.inter.IGenerator;

/**
 * 消息生成器
 * @author ZZ
 *
 */
public interface IMessageGenerator extends IGenerator{

	/**
	 * 返回Proto文件的绝对目录名
	 * @author ZZ
	 * @return
	 */
	public String getAbsoluteInputPath();
	
	/**
	 * 返回生成文件的绝对目录名
	 * @author ZZ
	 * @return
	 */
	public String getAbsoluteJavaOutputPath();
	
	/**
	 * 返回所有的proto文件
	 * @author ZZ
	 * @return
	 */
	public File[] getProtoFiles();
}
