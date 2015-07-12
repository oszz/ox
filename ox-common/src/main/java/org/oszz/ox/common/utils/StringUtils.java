package org.oszz.ox.common.utils;

/**
 * 字符串工具类
 * @author ZZ
 *
 */
public class StringUtils {

	/**
	 * 判断一个字符串是否为空白
	 * @author ZZ
	 * @param str 字符串内容
	 * @return 如果是空白字符串则返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public static boolean isBlank(String str){
		if (str == null)  {
            return true;
        }
        int size = str.length();
        char c;
        for (int i = 0; i < size; i++){
            c = str.charAt(i);
            if (!Character.isWhitespace(c)){
                return false;
            }
        }
        return true;
    }
	
	/**
	 * 将十进制转成十六进制
	 * @author ZZ
	 * @param code 十进制数
	 * @return 返回十进制数的十六进制字符串形式（如：0x0001）
	 */
	public static String toHex(int code){
		return String.format("0x%04X", code);	
	}
	
	/**
	 * 将二进制转换成16进制 
	 * @param bytes 二进制的byte数组
	 * @return 
	 */  
	public static String parseByte2HexStr(byte[] bytes) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < bytes.length; i++) {  
            String hex = Integer.toHexString(bytes[i] & 0xFF);  
            if (hex.length() == 1) {  
                    hex = '0' + hex;  
            }  
            sb.append(hex.toUpperCase());  
        }  
        return sb.toString();  
	}  
	
	/**  
	 * 将16进制转换成二进制的byte数组
	 * @param hexString 16进制字符串
	 * @return byte[] 二进制的byte数组
	 */  
	public static byte[] parseHexStr2Bytes(String hexString) {   
	    if (hexString == null || hexString.equals("")) {   
	        return null;   
	    }   
	    hexString = hexString.toUpperCase();   
	    int length = hexString.length() / 2;   
	    char[] hexChars = hexString.toCharArray();   
	    byte[] d = new byte[length];   
	    for (int i = 0; i < length; i++) {   
	        int pos = i * 2;   
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
	    }   
	    return d;   
	}  
	/**  
	 * Convert char to byte  
	 * @param c char  
	 * @return byte  
	 */  
	 private static byte charToByte(char c) {   
	    return (byte) "0123456789ABCDEF".indexOf(c);   
	}  
}
