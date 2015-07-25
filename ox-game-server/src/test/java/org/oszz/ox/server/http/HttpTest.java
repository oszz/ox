package org.oszz.ox.server.http;

import java.util.HashMap;
import java.util.Map;

import org.oszz.ox.common.utils.http.HttpClient;

public class HttpTest {

	private static final String URL = "http://127.0.0.1:9973"; 
	
	public static void main(String[] args) throws Exception{
		Map<String, String> paraMaps = new HashMap<String, String>();
		paraMaps.put("code", "1");
		paraMaps.put("openId", "openIdTest");
		
		HttpClient httpClient = HttpClient.openGet(URL, paraMaps);
		
		byte[] reaultBytes = httpClient.getResponseResult();
		System.out.println(new String(reaultBytes));
		
		
//		for(int i=0 ; i<5 ; i++){
//			Thread.sleep(1*1000L);
//			System.out.println("---------------------");
//			byte[] reault = httpClient.sendGetRequest(paraMaps);
//			System.out.println(new String(reault));
//		}
		Thread.sleep(1*1000L);
		System.out.println("---------------------");
		Map<String, String> paraMaps2 = new HashMap<String, String>();
		paraMaps2.put("code", "3");
		paraMaps2.put("openId", "openIdTest");
		byte[] reault = httpClient.sendGetRequest(paraMaps2);
		System.out.println(new String(reault));
	}

}
