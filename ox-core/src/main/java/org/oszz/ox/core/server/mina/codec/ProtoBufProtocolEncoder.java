package org.oszz.ox.core.server.mina.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.oszz.ox.core.message.IMessage;

public class ProtoBufProtocolEncoder extends ProtocolEncoderAdapter {

	private Charset charset;
	
	public ProtoBufProtocolEncoder(Charset charset){
		this.charset = charset;
	}
	
	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		IMessage msg = (IMessage)message;
		byte[] bytes = msg.toBytes();
		IoBuffer buf = IoBuffer.wrap(bytes);
		buf.put(bytes);
		buf.flip(); 
	    out.write(buf);
	}
}
