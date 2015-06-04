package org.oszz.ox.core.filter.codec.request.doGet;

import org.oszz.ox.core.filter.codec.ProtocolCodecFactory;
import org.oszz.ox.core.filter.codec.ProtocolDecoder;
import org.oszz.ox.core.filter.codec.ProtocolEncoder;

/**
 * 行读编解码工厂类
 * @author ZZ
 *
 */
public class DoGetCodecFactory implements ProtocolCodecFactory {
	
	private String charsetName;
	
	private ProtocolDecoder decoder;
	
	private ProtocolEncoder encoder;
	
	public DoGetCodecFactory(String charsetName){
		this.charsetName = charsetName;
		decoder = new DoGetDecoder(this.charsetName);
		encoder = new DoGetEncoder(this.charsetName);
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
