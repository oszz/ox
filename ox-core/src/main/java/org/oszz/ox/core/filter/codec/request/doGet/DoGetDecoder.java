package org.oszz.ox.core.filter.codec.request.doGet;

import org.oszz.ox.core.filter.codec.ProtocolDecoder;
import org.oszz.ox.core.filter.codec.ProtocolDecoderOutput;

/**
 * 字符串行的解码器
 * @author ZZ
 *
 */
public class DoGetDecoder implements ProtocolDecoder{

	private String charsetName;
	
	public DoGetDecoder(String charsetName){
		this.charsetName = charsetName;
	}
	
	@Override
	public void doDecoder(ProtocolDecoderOutput out, Object obj) {
		// TODO Auto-generated method stub
		
	}

}
