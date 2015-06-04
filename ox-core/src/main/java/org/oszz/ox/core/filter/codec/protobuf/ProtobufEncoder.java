package org.oszz.ox.core.filter.codec.protobuf;

import org.oszz.ox.core.filter.codec.ProtocolEncoder;
import org.oszz.ox.core.filter.codec.ProtocolEncoderOutput;

public class ProtobufEncoder implements ProtocolEncoder {
	private String charsetName;

	public ProtobufEncoder(String charsetName){
		this.charsetName = charsetName;
	}
	
	@Override
	public void doEncoder(ProtocolEncoderOutput out, Object obj) {
		// TODO Auto-generated method stub

	}

}
