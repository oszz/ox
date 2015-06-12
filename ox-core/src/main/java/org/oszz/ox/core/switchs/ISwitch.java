package org.oszz.ox.core.switchs;

/**
 * 开关接口<br>
 * 模块>功能(模块包含功能)<br>
 * 如果一个模块关闭，则该模块包含的所有功能即全部不能使用<br>
 * 如果一个功能关闭，则该功能所属模块的其他功能不收影响
 * @author ZZ
 *
 */
public interface ISwitch {

	/**
	 * 模块或功能是否打开
	 * @author ZZ
	 * @return 模块或功能如果打开，则返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public boolean isOpen();
	
	/**
	 * 返回模块或功能的编码
	 * @author ZZ
	 * @return 返回模块或功能的编码
	 */
	public int getCode();
	
	/**
	 * 如果开关关闭，则返回该提示信息
	 * @author ZZ
	 * @return 返回开关关闭的提示信息
	 */
	public String returnIfClosed();
}
