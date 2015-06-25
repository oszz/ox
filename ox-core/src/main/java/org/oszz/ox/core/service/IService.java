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
	 * 当所有服务都已经初始化完成后，调用该方法<br>
	 * 如果服务直接相互依赖的话，确保依赖的服务可用
	 * @author ZZ
	 */
	public void onInitialized();
}
