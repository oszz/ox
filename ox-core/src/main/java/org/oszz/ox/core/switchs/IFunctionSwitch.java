package org.oszz.ox.core.switchs;


/**
 * 功能开关
 * @author ZZ
 *
 */
public interface IFunctionSwitch extends ISwitch {

	/**
	 * 返回该功能所属的父模块
	 * @author ZZ
	 * @return 返回该功能所属的父模块
	 */
	public IModuleSwitch getParentModuleSwitch();
}
