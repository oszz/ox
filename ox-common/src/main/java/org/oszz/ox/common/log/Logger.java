package org.oszz.ox.common.log;


/**
 * 一个简单的日志门面,目的是简单地捕捉日志所使用OX-GameServer的风格
 * @author ZZ
 *
 */
public interface Logger {

	/**
	 * 返回日志的名称
	 * @author ZZ
	 * @return 返回日志的名称
	 */
	public String getName();
	
	/**
	 * 是否开启了debug
	 * @author ZZ
	 * @return 如果是返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public boolean isDebugEnabled();
	
	/**
	 * 在debug级别输出日志
	 * @author ZZ
	 * @param content 格式化的日志内容
	 * @param args 格式化日志的参数
	 */
	public void debug(String content, Object... args);
	
	/**
	 * 在debug级别输出异常
	 * @author ZZ
	 * @param thrown 抛出的异常信息
	 */
	public void debug(Throwable thrown);
	
	/**
	 * 在debug级别输出日志和异常信息
	 * @author ZZ
	 * @param content 日志内容
	 * @param thrown 抛出的异常信息
	 */
	public void debug(String content, Throwable thrown);
	
	
	/**
	 * 在info级别输出日志
	 * @author ZZ
	 * @param content 格式化的日志内容
	 * @param args 格式化日志的参数
	 */
	public void info(String content, Object... args);
	
	/**
	 * 在info级别输出异常
	 * @author ZZ
	 * @param thrown 抛出的异常信息
	 */
	public void info(Throwable thrown);
	
	/**
	 * 在info级别输出日志和异常信息
	 * @author ZZ
	 * @param content 日志内容
	 * @param thrown 抛出的异常信息
	 */
	public void info(String content, Throwable thrown);
	
	/**
	 * 在warn级别输出日志
	 * @author ZZ
	 * @param content 格式化的日志内容
	 * @param args 格式化日志的参数
	 */
	public void warn(String content, Object... args);
	
	/**
	 * 在warn级别输出异常
	 * @author ZZ
	 * @param thrown 抛出的异常信息
	 */
	public void warn(Throwable thrown);
	
	/**
	 * 在warn级别输出日志和异常信息
	 * @author ZZ
	 * @param content 日志内容
	 * @param thrown 抛出的异常信息
	 */
	public void warn(String content, Throwable thrown);
	
	/**
	 * 
	 * @author ZZ
	 * @param name
	 * @return
	 */
	public Logger getLogger(String name);
	
}
