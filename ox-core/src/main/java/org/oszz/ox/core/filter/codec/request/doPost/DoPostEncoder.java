package org.oszz.ox.core.filter.codec.request.doPost;

import org.oszz.ox.core.filter.codec.ProtocolEncoder;
import org.oszz.ox.core.filter.codec.ProtocolEncoderOutput;

/**
 * 字符串行的解码器
 * @author ZZ
 *
 */
public class DoPostEncoder implements ProtocolEncoder {
	
	private String charsetName;
	
	public DoPostEncoder(String charsetName){
		this.charsetName = charsetName;
	}

	@Override
	public void doEncoder(ProtocolEncoderOutput out, Object obj) {
		// TODO Auto-generated method stub

	}

}
