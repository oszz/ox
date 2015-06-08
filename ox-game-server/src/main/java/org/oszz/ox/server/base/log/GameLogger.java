package org.oszz.ox.server.base.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 日志服务
 * @author ZZ
 *
 */
public class GameLogger {

	private static final String PREFIX = "OX-GameServer";
	
	/**
	 * 系统日志
	 */
	public static final Logger SYSTEM = LoggerFactory.getLogger(PREFIX + " System:");

}
