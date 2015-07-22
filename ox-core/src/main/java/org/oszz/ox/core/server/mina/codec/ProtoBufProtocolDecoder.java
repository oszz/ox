package org.oszz.ox.core.server.mina.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.core.Globals;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.message.IMessageHandler;
import org.oszz.ox.core.message.MessageCodeMapping;
import org.oszz.ox.core.message.MessageProcesserType;

public class ProtoBufProtocolDecoder extends ProtocolDecoderAdapter{

	/*private Charset charset;
	
	public ProtoBufProtocolDecoder(Charset charset){
		this.charset = charset;
	}*/
	
	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		short code = in.getShort();
		int length = in.getInt();
		byte[] bytes = new byte[length];
		in.get(bytes);
		MessageCodeMapping msgCodeMapping = Globals.getMessageCodeMapping(code);
		Class<? extends IMessage> msgClass = msgCodeMapping.getMessageClass();
		IMessageHandler msgHandler = msgCodeMapping.getMsgHandler();
		MessageProcesserType messageProcesserType = msgCodeMapping.getMessageProcesserType();
		
		IMessage message = ClassUtils.newInstance(msgClass);
		message.toProtobufMessage(bytes, message.getProtobufMessageClass());
		message.setMsgHandler(msgHandler);
		message.setMessageProcesserType(messageProcesserType);
		
		out.write(message);
	}
}
