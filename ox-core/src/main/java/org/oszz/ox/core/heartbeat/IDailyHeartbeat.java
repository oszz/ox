package org.oszz.ox.core.heartbeat;

/**
 * 每日整点心跳的接口
 * @author ZZ
 *
 */
public interface IDailyHeartbeat extends IHeartbeat{

	/**
	 * 设置心跳的整点小时
	 * @author ZZ
	 * @param hour 整点小时（0-24）
	 */
	public void setHour(int hour);
	
	/**
	 * 每日整点的刷新动作<br>
	 * 可用于刷新数据
	 * @author ZZ
	 */
	public void refresh();
}
