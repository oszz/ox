package org.oszz.ox.common.utils.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * HTTP的客户端
 * @author ZZ
 *
 */
public class HttpClient {

	// 取得cookie，相当于记录了身份，供下次访问时使用   
	//String cookieVal = connection.getHeaderField("Set-Cookie");
	private String cookieVal = null;
	private String url = null;
	private byte[] responseResult;//响应的结果
	
	public static HttpClient openGet(String urlStr, Map<String, String> paraMaps){
		HttpClient httpClient = new HttpClient();
		byte[] respResult = send(httpClient, urlStr, HttpUtils.GET_METHOD_NAME, paraMaps);
		return httpClient;
	}
	
	public static HttpClient openPost(String urlStr, byte[] paraBytes){
		HttpClient httpClient = new HttpClient();
		byte[] respResult = send(httpClient,urlStr, HttpUtils.POST_METHOD_NAME, paraBytes);
		return httpClient;
	}
	@SuppressWarnings("unchecked")
	private static byte[] send(HttpClient httpClient, String urlStr, String methodName, Object para){
		httpClient.setUrl(urlStr);
		HttpURLConnection httpURLConnection = null;
		OutputStream out = null;
		try {
			if(methodName.equals(HttpUtils.GET_METHOD_NAME)){
				Map<String, String> paraMaps = (Map<String, String>)para;
				String urlPara = HttpUtils.getUrlParamsByMap(paraMaps);
				urlStr += "?" + urlPara;
			}
//			else if(methodName.equals(HttpUtils.POST_METHOD_NAME)){
//				
//			}
			URL url = new URL(urlStr);
			httpURLConnection = (HttpURLConnection)url.openConnection();
			String cookieVal = httpClient.getCookieVal();
			if (cookieVal != null) {  
                //发送cookie信息上去，以表明自己的身份，否则会被认为没有权限   
				httpURLConnection.setRequestProperty("Cookie", cookieVal);  
			}  
			httpURLConnection = HttpUtils.setProperty(httpURLConnection, methodName);
			httpURLConnection.connect(); 
			if(methodName.equals(HttpUtils.POST_METHOD_NAME)){
				byte[] paraBytes = (byte[])para;
				out = httpURLConnection.getOutputStream();                          
	            out.write(paraBytes);  
	            out.flush();  
			}
			if(cookieVal == null){
				// 取得cookie，相当于记录了身份，供下次访问时使用   
				cookieVal = httpURLConnection.getHeaderField("Set-Cookie");
				httpClient.setCookieVal(cookieVal);
			}
			byte[] respResult = HttpUtils.read(httpURLConnection);
			httpClient.setResponseResult(respResult);
			return respResult;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(out != null){try {out.close();} catch (IOException e) {}}
		}
	}

	public String getCookieVal() {
		return cookieVal;
	}

	public void setCookieVal(String cookieVal) {
		this.cookieVal = cookieVal;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte[] getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(byte[] responseResult) {
		this.responseResult = responseResult;
	}
	
	public byte[] sendGetRequest(Map<String, String> paraMaps){
		byte[] respResult = send(this, this.getUrl(), HttpUtils.GET_METHOD_NAME, paraMaps);
		return respResult;
	}
	
	public byte[] sendPostRequest(byte[] paraBytes){
		byte[] respResult = send(this, this.getUrl(), HttpUtils.POST_METHOD_NAME, paraBytes);
		return respResult;
	}
	
}
