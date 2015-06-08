package org.oszz.ox.common.log;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;

public class Log {
	/**
     * logger的持有者
     */
    private final static ConcurrentMap<String, Logger> loggersHolder = new ConcurrentHashMap<>();
    
    /**
     * 是否已经初始化
     */
    private static boolean isInitialized = false;
    
    private static Class<?> logClass = null;

	static{
//		org.slf4j.Logger
		try {
			logClass = Class.forName(LogConstants.SLF4J_LOGGER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回Loggers
	 * @author ZZ
	 * @return 返回Loggers
	 */
	protected static ConcurrentMap<String, Logger> getLoggers() {
		return loggersHolder;
	}
	
	public static void init(){
		synchronized(Log.class){
			if(isInitialized){
				return ;
			}else{
				isInitialized = true;
			}
			
		}
	}
	
	
}
