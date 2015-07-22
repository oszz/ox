package org.oszz.ox.common.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP请求工具类
 * @author ZZ
 *
 */
public class HttpUtils {
	
	private static final int BUFF_SIZE = 512;
	
	/**
	 * GET请求的方法名
	 */
	public static final String GET_METHOD_NAME = "GET";
	/**
	 * POST请求的方法名
	 */
	public static final String POST_METHOD_NAME = "POST";
	
	public static byte[] sendGetRequest(String urlStr, Map<String, String> paraMaps){
		HttpURLConnection httpURLConnection = null;
		try {
			String urlPara = getUrlParamsByMap(paraMaps);
			urlStr += "?" + urlPara;
			URL url = new URL(urlStr);
			httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection = setProperty(httpURLConnection, GET_METHOD_NAME);
			httpURLConnection.connect(); 
			return read(httpURLConnection);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
//			if(httpURLConnection != null){
//				httpURLConnection.disconnect();
//			}
		}
		return null;
	}
	
	public static byte[] sendPostRequest(String urlStr, byte[] paraBytes){
		HttpURLConnection httpURLConnection = null;
		OutputStream out = null;
		
		try {
			URL url = new URL(urlStr);
			httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection = setProperty(httpURLConnection, POST_METHOD_NAME);
			httpURLConnection.connect();
			out = httpURLConnection.getOutputStream();                          
            out.write(paraBytes);  
            out.flush();  
            
            return read(httpURLConnection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null){try {out.close();} catch (IOException e) {}}
		}
		return null;
	}
	
	/** 
     * 将url参数转换成map 
     * @param param aa=11&bb=22&cc=33 
     * @return 
     */  
    public static Map<String, Object> getUrlParams(String param) {  
        Map<String, Object> map = new HashMap<String, Object>(0);  
        if (StringUtils.isBlank(param)) {  
            return map;  
        }  
        String[] params = param.split("&");  
        for (int i = 0; i < params.length; i++) {  
            String[] p = params[i].split("=");  
            if (p.length == 2) {  
                map.put(p[0], p[1]);  
            }  
        }  
        return map;  
    }  
  
    /** 
     * 将map转换成url 
     * @param map 
     * @return 
     */  
    public static String getUrlParamsByMap(Map<String, String> map) {  
        if (map == null) {  
            return "";  
        }  
        StringBuffer sb = new StringBuffer();  
        for (Map.Entry<String, String> entry : map.entrySet()) {  
            sb.append(entry.getKey() + "=" + entry.getValue());  
            sb.append("&");  
        }  
        String s = sb.toString();  
        if (s.endsWith("&")) {  
            s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");  
        }  
        return s;  
    }  
    
    private static HttpURLConnection setProperty(HttpURLConnection httpURLConnection, String reqMethod) throws IOException{
    	 //设置http连接属性  
		httpURLConnection.setDoOutput(true);  
		httpURLConnection.setDoInput(true);  
//        connection.setRequestMethod("POST"); // 可以根据需要 提交 GET、POST、DELETE、INPUT等http提供的功能
		httpURLConnection.setRequestMethod(reqMethod); 
		httpURLConnection.setUseCaches(false);  
		httpURLConnection.setInstanceFollowRedirects(true); 
		httpURLConnection.setRequestProperty("connection", "Keep-Alive");
        //设置http头 消息  application/octet-stream
		httpURLConnection.setRequestProperty("Content-Type","application/octet-stream");
		return httpURLConnection;
    }
    
    
    private static byte[] read(HttpURLConnection httpURLConnection){
    	BufferedInputStream bis = null;
    	try {
			bis = new BufferedInputStream(httpURLConnection.getInputStream());
			int readIndex = 0;
			byte[] totalBytes = new byte[0];//所有的bytes
			byte[] buff = new byte[BUFF_SIZE];
			while ((readIndex = bis.read(buff)) != -1) {  
			 	if(readIndex == BUFF_SIZE){
			 		totalBytes = byteMerger(totalBytes, buff);
			 	}else{//说明到结尾了
			 		byte[] tempBytes = new byte[readIndex];
			 		for(int i=0 ; i<readIndex ; i++){
			 			tempBytes[i] = buff[i];
			 		}
			 		totalBytes = byteMerger(totalBytes, tempBytes);
			 	}
			}
			return totalBytes;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(bis != null){try {bis.close();} catch (IOException e) {}}
		}
    	return null;
    }
  //java 合并两个byte数组  
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){  
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];  
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);  
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);  
        return byte_3;  
    } 
}
