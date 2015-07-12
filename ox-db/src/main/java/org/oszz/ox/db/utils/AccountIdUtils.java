package org.oszz.ox.db.utils;


/**
 * 账号id工具类
 * @author ZZ
 *
 */
public class AccountIdUtils {

	/**
	 * 返回一个唯一的账号id
	 * @author ZZ
	 * @return
	 */
	public static String getId(){
		String id = System.currentTimeMillis() + "";//先这样计算着
		return id;
	}
}
