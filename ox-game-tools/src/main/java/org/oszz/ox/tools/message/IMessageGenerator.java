package org.oszz.ox.tools.message;

import java.io.File;
import java.util.List;

import org.oszz.ox.tools.inter.IGenerator;

/**
 * 消息生成器
 * @author ZZ
 *
 */
public interface IMessageGenerator extends IGenerator{

	public String getAbsoluteInputPath();
	
	public String getAbsoluteOutputPath();
	
	public List<File> getProtoFiles();
}
