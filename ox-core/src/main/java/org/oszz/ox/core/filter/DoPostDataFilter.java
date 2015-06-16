package org.oszz.ox.core.filter;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.core.message.MessageCodeMapping;
import org.oszz.ox.core.message.OXMessage;
import org.oszz.ox.core.session.GSSession;

import com.google.protobuf.GeneratedMessage;

public class DoPostDataFilter implements IFilter {
	
	@Override
	public void doInputFilter(GSSession gsSession, HttpServletRequest request,
			HttpServletResponse response) {

		InputStream is = null;
		DataInputStream dis = null;
		try{
			is = request.getInputStream();
			dis = new DataInputStream(is);
			short code = dis.readShort();
			int length = dis.readInt();
			byte[] bytes = new byte[length];
			dis.read(bytes);
			
			Class<? extends GeneratedMessage> msgClass = MessageCodeMapping.getInstance().getMessageClass(code);
			IMessageHandler msgHandler = MessageCodeMapping.getInstance().getMessageHandler(code);
			
			IPlayer player = gsSession.getPlayer();
			OXMessage oxMessage = new OXMessage(code);
			oxMessage.toProtobufMessage(bytes, msgClass);
			
			msgHandler.handle(player, oxMessage);
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(is != null){try {is.close();} catch (IOException e) {}}
			if(dis != null){try {dis.close();} catch (IOException e) {}}
		}
		
	}

}
