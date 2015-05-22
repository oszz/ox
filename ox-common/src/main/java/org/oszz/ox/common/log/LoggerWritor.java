package org.oszz.ox.common.log;

import org.slf4j.Logger;

/**
 * 日志书写者
 * @author ZZ
 *
 */
public class LoggerWritor {

	public static void info(Logger log, String content){
		if(log.isInfoEnabled()){
			log.info(content);
		}
	}
	
	public static void debug(Logger log, String content){
		if(log.isDebugEnabled()){
			log.debug(content);
		}
	}
	
	public static void warn(Logger log, String content){
		if(log.isWarnEnabled()){
			log.warn(content);
		}
	}
	
	public static void error(Logger log, String content){
		if(log.isErrorEnabled()){
			log.error(content);
		}
	}
}
