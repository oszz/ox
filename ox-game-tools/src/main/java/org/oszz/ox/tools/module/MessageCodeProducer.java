package org.oszz.ox.tools.module;

import org.oszz.ox.common.utils.StringUtils;

/**
 * message code的生成者
 * @author ZZ
 *
 */
public class MessageCodeProducer {
	private static int CODE_START = 0x0001;
	
	public static String next(){
		return StringUtils.toHex(CODE_START++);
	}
}
