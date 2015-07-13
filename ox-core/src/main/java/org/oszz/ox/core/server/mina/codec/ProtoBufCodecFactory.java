package org.oszz.ox.core.server.mina.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ProtoBufCodecFactory implements ProtocolCodecFactory {
	
	private ProtocolEncoder encoder;//编码
	private ProtocolDecoder decoder;//解码
	
	
	public ProtoBufCodecFactory(Charset charset){
		encoder = new ProtoBufProtocolEncoder(charset);
		decoder = new ProtoBufProtocolDecoder(charset);
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
