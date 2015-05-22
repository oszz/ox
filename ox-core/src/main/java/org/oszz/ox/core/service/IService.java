package org.oszz.ox.core.service;

/**
 * 服务接口
 * @author ZZ
 *
 */
public interface IService {

	/**
	 * 创建服务
	 * @author ZZ
	 * @return 如果成功返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public boolean create();
	
	/**
	 * 初始化服务
	 * @author ZZ
	 * @return 如果成功返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public boolean init();
	
	/**
	 * 启动服务
	 * @author ZZ
	 * @return 如果成功返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public boolean start();
	
	/**
	 * 重启服务
	 * @author ZZ
	 * @return 如果成功返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public boolean restart();
	
	/**
	 * 停止并销毁服务
	 * @author ZZ
	 * @return 如果成功返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public boolean stop();
}
