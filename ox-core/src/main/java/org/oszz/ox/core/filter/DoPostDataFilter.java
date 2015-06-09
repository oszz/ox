package org.oszz.ox.core.filter;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oszz.ox.core.message.MessageCodeMapping;
import org.oszz.ox.core.session.GSSession;

import com.google.protobuf.MessageLite;

public class DoPostDataFilter implements IFilter {

	@Override
	public void doInputFilter(GSSession gsSession, HttpServletRequest request,
			HttpServletResponse response, IFilterChain filterChain) {
		InputStream is = null;
		DataInputStream dis = null;
		try{
			is = request.getInputStream();
			dis = new DataInputStream(is);
			short code = dis.readShort();
			int length = dis.readInt();
			byte[] bytes = new byte[length];
			dis.read(bytes);
			
			Class<MessageLite> msgLiteClass = MessageCodeMapping.getInstance().getMessageLite(code);
			if(msgLiteClass != null){
				MessageLite msgLite = msgLiteClass.newInstance();
			}
			
			filterChain.doInputFilter(gsSession, request, response);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(is != null){try {is.close();} catch (IOException e) {}}
			if(dis != null){try {dis.close();} catch (IOException e) {}}
		}
		
	}

}
