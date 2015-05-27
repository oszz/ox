package org.oszz.ox.core.filter.codec.line;

import org.oszz.ox.core.filter.codec.ProtocolEncoder;
import org.oszz.ox.core.filter.codec.ProtocolEncoderOutput;

/**
 * 字符串行的解码器
 * @author ZZ
 *
 */
public class TextLineEncoder implements ProtocolEncoder {
	
	private String charsetName;
	
	public TextLineEncoder(String charsetName){
		this.charsetName = charsetName;
	}

	@Override
	public void doEncoder(ProtocolEncoderOutput out, Object obj) {
		// TODO Auto-generated method stub

	}

}
