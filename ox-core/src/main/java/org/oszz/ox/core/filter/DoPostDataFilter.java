package org.oszz.ox.core.filter;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.core.message.MessageCodeMapping;
import org.oszz.ox.core.message.MessageProcesserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 过滤Post请求的数据
 * @author ZZ
 *
 */
public class DoPostDataFilter implements IFilter {
	protected static final Logger log = LoggerFactory.getLogger("DoPostDataFilter");
	
	@Override
	public IMessage doInputFilter(HttpServletRequest request) {
		IMessage message = null;
		InputStream is = null;
		DataInputStream dis = null;
		try{
			is = request.getInputStream();
			dis = new DataInputStream(is);
			short code = dis.readShort();//消息编码
			int length = dis.readInt();//消息长度
			byte[] bytes = new byte[length];//存放消息的内容
			dis.read(bytes);
			
			Class<? extends IMessage> msgClass = MessageCodeMapping.getInstance().getMessageClass(code);
			IMessageHandler msgHandler = MessageCodeMapping.getInstance().getMessageHandler(code);
			MessageProcesserType messageProcesserType = MessageCodeMapping.getInstance().getMessageProcesserType(code);
			
			message = ClassUtils.newInstance(msgClass);
			message.toProtobufMessage(bytes, message.getProtobufMessageClass());
			message.setMsgHandler(msgHandler);
			message.setMessageProcesserType(messageProcesserType);
		}catch (Exception e){
			log.error("从request中读取信息时出错.错误信息：{}", e);
		}finally{
			if(is != null){try {is.close();} catch (IOException e) {}}
			if(dis != null){try {dis.close();} catch (IOException e) {}}
		}
		return message;
	}

}
