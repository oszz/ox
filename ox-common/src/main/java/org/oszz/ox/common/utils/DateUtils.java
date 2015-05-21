package org.oszz.ox.common.utils;

import java.util.Calendar;

/**
 * 日期工具类
 * @author ZZ
 *
 */
public class DateUtils {
	
	
	/**
	 * 获得某年某月某天某时某分某秒的时间秒数
	 * @author ZZ
	 * @param year 某年
	 * @param month 某月
	 * @param day 某天
	 * @param hour 某时
	 * @param minute 某分
	 * @param second 某秒
	 * @return 获得某年某月某时某分某秒的时间秒数
	 */
	public static int getTimes(int year, int month, int day, int hour, int minute, int second){ 
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR, year); 
		cal.set(Calendar.MONTH, month); 
		cal.set(Calendar.DAY_OF_MONTH, day); 
		cal.set(Calendar.HOUR_OF_DAY, hour); 
		cal.set(Calendar.SECOND, minute); 
		cal.set(Calendar.MINUTE, second); 
		cal.set(Calendar.MILLISECOND, 0); 
		return (int)(cal.getTimeInMillis()/1000); 
	}

	/**
	 * 获得当天0点时间 (秒)
	 * @author ZZ
	 * @return 获得当天0点时间 (秒)
	 */
	public static int getTodayTimes0(){ 
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		return (int) (cal.getTimeInMillis()/1000); 
	} 
	/**
	 * 获得当天24点时间 (秒)
	 * @author ZZ
	 * @return 获得当天24点时间 (秒)
	 */
	public static int getTodayTimes24(){ 
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.HOUR_OF_DAY, 24); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		return (int) (cal.getTimeInMillis()/1000); 
	} 
}
