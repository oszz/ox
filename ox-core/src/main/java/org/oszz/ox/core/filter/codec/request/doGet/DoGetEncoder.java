package org.oszz.ox.core.filter.codec.request.doGet;

import org.oszz.ox.core.filter.codec.ProtocolEncoder;
import org.oszz.ox.core.filter.codec.ProtocolEncoderOutput;

/**
 * 字符串行的解码器
 * @author ZZ
 *
 */
public class DoGetEncoder implements ProtocolEncoder {
	
	private String charsetName;
	
	public DoGetEncoder(String charsetName){
		this.charsetName = charsetName;
	}

	@Override
	public void doEncoder(ProtocolEncoderOutput out, Object obj) {
		// TODO Auto-generated method stub

	}

}
