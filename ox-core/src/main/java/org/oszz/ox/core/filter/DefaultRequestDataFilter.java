package org.oszz.ox.core.filter;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.continuation.Continuation;

public class DefaultRequestDataFilter implements IFilter {
	
	private Continuation continuation;
	private HttpServletRequest request;
	
	public DefaultRequestDataFilter(Continuation continuation, HttpServletRequest request){
		this.continuation = continuation;
		this.request = request;
	}

	@Override
	public void doFilter(){
		InputStream in = null;
		DataInputStream dis = null;
		try{
			in = request.getInputStream();
			dis = new DataInputStream(in);
			short code = dis.readShort();
			int length = dis.readInt();
			byte[] buf = new byte[length];
			dis.read(buf, 0, length);
		}catch(Exception e){
			
		}finally{
			if(in != null){try {in.close();} catch (IOException e) {}}
			if(dis != null){try {dis.close();} catch (IOException e) {}}
		}
		

	}

}
