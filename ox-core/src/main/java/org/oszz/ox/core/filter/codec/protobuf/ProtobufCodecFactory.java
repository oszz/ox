package org.oszz.ox.core.filter.codec.protobuf;

import org.oszz.ox.core.filter.codec.ProtocolCodecFactory;
import org.oszz.ox.core.filter.codec.ProtocolDecoder;
import org.oszz.ox.core.filter.codec.ProtocolEncoder;

public class ProtobufCodecFactory implements ProtocolCodecFactory {

	private ProtocolDecoder decoder;
	
	private ProtocolEncoder encoder;
	
	private String charsetName;
	
	public ProtobufCodecFactory(String charsetName){
		this.charsetName = charsetName;
		decoder = new ProtobufDecoder(this.charsetName);
		encoder = new ProtobufEncoder(this.charsetName);
	}

	@Override
	public ProtocolDecoder getDecoder() throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder() throws Exception {
		return encoder;
	}
	
	
}
