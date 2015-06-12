package org.oszz.ox.core.switchs;

import java.util.List;

/**
 * 模块开关接口
 * @author ZZ
 *
 */
public interface IModuleSwitch extends ISwitch {

	/**
	 * 返回该模块的所有功能
	 * @author ZZ
	 * @return 返回该模块的所有功能
	 */
	public List<IFunctionSwitch> getAllFunctionSwitch();
}
