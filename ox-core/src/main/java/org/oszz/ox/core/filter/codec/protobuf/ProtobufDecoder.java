package org.oszz.ox.core.filter.codec.protobuf;

import org.oszz.ox.core.filter.codec.ProtocolDecoder;
import org.oszz.ox.core.filter.codec.ProtocolDecoderOutput;

public class ProtobufDecoder implements ProtocolDecoder {
	private String charsetName;
	
	public ProtobufDecoder(String charsetName){
		this.charsetName = charsetName;
	}

	@Override
	public void doDecoder(ProtocolDecoderOutput out, Object obj) {
		// TODO Auto-generated method stub
	}

}
