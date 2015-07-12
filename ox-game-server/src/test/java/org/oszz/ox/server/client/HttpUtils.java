package org.oszz.ox.server.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.oszz.ox.core.message.IMessage;

public class HttpUtils {
	
	public static final String GET_REQ = "GET";
	public static final String POST_REQ = "POST";
	
	
	public static void readPost(String urlStr, String reqMethod, IMessage msg) throws IOException { 
		URL url = new URL(urlStr);
		read(url, reqMethod, msg, null);
	}
	
	public static void readGet(String urlStr, String reqMethod, Map<String, String> getMaps) throws IOException { 
		URL url = new URL(urlStr);
		read(url, reqMethod, null, getMaps);
	}

	public static void read(URL url, String reqMethod, IMessage msg, Map<String, String> getMaps) throws IOException { 
		HttpURLConnection connection=null;  
        try {  
        	
        	if(reqMethod == GET_REQ){
        		String urlStr = url.toString();
        		urlStr += "?";
        		for(String key : getMaps.keySet()){
        			String value = getMaps.get(key);
        			urlStr += key + "=" + value + "&";
        		}
        		urlStr = urlStr.substring(0, urlStr.length() - 1);
        		url = new URL(urlStr);
            }
             //创建连接  
             connection = (HttpURLConnection) url.openConnection();  
               
  
             //设置http连接属性  
             connection.setDoOutput(true);  
             connection.setDoInput(true);  
//             connection.setRequestMethod("POST"); // 可以根据需要 提交 GET、POST、DELETE、INPUT等http提供的功能
             connection.setRequestMethod(reqMethod); 
             connection.setUseCaches(false);  
             connection.setInstanceFollowRedirects(true); 
             connection.setRequestProperty("connection", "Keep-Alive");
               
             //设置http头 消息  application/octet-stream
             connection.setRequestProperty("Content-Type","application/octet-stream");
//             connection.setRequestProperty("Content-Type","application/json");  //设定 请求格式 json，也可以设定xml格式的  
             //connection.setRequestProperty("Content-Type", "text/xml");   //设定 请求格式 xml，  
//             connection.setRequestProperty("Accept","application/json");//设定响应的信息的格式为 json，也可以设定xml格式的  
//	             connection.setRequestProperty("X-Auth-Token","xx");  //特定http服务器需要的信息，根据服务器所需要求添加  
             connection.connect();  
   
            if(reqMethod == POST_REQ){
            	OutputStream out = connection.getOutputStream();                          
                out.write(msg.toBytes());  
                out.flush();  
                out.close();
            }
              
   
            //	            读取响应  
             BufferedReader reader = new BufferedReader(new InputStreamReader(  
                     connection.getInputStream()));  
             String lines;  
             StringBuffer sb = new StringBuffer("");  
             while ((lines = reader.readLine()) != null) {  
                 lines = new String(lines.getBytes());  
                 sb.append(lines);  
             }  
             System.out.println("来自服务端的消息：" + sb.toString());  
             reader.close();  
////	              断开连接  
             connection.disconnect();  
         } catch (MalformedURLException e) {  
             e.printStackTrace();  
         } catch (UnsupportedEncodingException e) {  
             e.printStackTrace();  
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
	}
}
