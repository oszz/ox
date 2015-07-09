package org.oszz.ox.common;

import org.oszz.ox.common.utils.crypto.AESUtils;


/**
 * Unit test for simple App.
 */
public class AppTest {
	
	public static void main(String[] args) {
		String content = "127.0.0.1:8080";
		String password = "123pwd";
		String hexStr = AESUtils.encrypt2HexStr(content, password);
		System.out.println(hexStr);
		
		String decContent = AESUtils.decrypt(hexStr, password);
		System.out.println(decContent);
	}
}
