package org.oszz.ox.common.log;

import org.slf4j.Logger;

/**
 * 日志书写者
 * @author ZZ
 *
 */
public class LoggerWritor {
	
	private static final String LOG_START_STR = "OX-GameServer ";

	public static void info(Logger log, String content){
		if(log.isInfoEnabled()){
			log.info(LOG_START_STR + content);
		}
	}
	
	public static void debug(Logger log, String content){
		if(log.isDebugEnabled()){
			log.debug(LOG_START_STR +content);
		}
	}
	
	public static void warn(Logger log, String content){
		if(log.isWarnEnabled()){
			log.warn(LOG_START_STR +content);
		}
	}
	
	public static void error(Logger log, String content){
		if(log.isErrorEnabled()){
			log.error(LOG_START_STR +content);
		}
	}
}
