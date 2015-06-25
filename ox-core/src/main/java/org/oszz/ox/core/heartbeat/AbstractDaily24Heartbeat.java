package org.oszz.ox.core.heartbeat;

import org.oszz.ox.common.utils.DateUtils;


/**
 * 抽象的每日24点的心跳
 * @author ZZ
 */
public abstract class AbstractDaily24Heartbeat implements IDailyHeartbeat {
	
	/**
	 * 下一次心跳的时间点（秒）
	 */
	private int nextBeatTime = 0;//秒数
	
	@Override
	public void beat() {
		if(nextBeatTime == 0){
			nextBeatTime = DateUtils.getTodayTimes24();
		}
		int currentTime = (int)(System.currentTimeMillis()/1000L); 
		if(currentTime > nextBeatTime){
			refresh();
			nextBeatTime = DateUtils.getTodayTimes24();
//			nextBeatTime = currentTime + 1;
		}
	}

}
