package org.oszz.ox.server.client;


public class Client {

	public static void main(String[] args) throws Exception {
		HttpRequest httpReq = new HttpRequest();
		
//		httpReq.auth("openIdTest");
		httpReq.authGet("openIdTest");
		
		for(int i=0;i<10;i++){
			httpReq.authGet("openIdTest" + i);
		}
	}
}
