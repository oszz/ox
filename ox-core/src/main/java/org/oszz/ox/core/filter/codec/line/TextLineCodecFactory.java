package org.oszz.ox.core.filter.codec.line;

import org.oszz.ox.core.filter.codec.ProtocolCodecFactory;
import org.oszz.ox.core.filter.codec.ProtocolDecoder;
import org.oszz.ox.core.filter.codec.ProtocolEncoder;

/**
 * 行读编解码工厂类
 * @author ZZ
 *
 */
public class TextLineCodecFactory implements ProtocolCodecFactory {
	
	private String charsetName;
	
	private ProtocolDecoder decoder;
	
	private ProtocolEncoder encoder;
	
	public TextLineCodecFactory(String charsetName){
		this.charsetName = charsetName;
		decoder = new TextLineDecoder(this.charsetName);
		encoder = new TextLineEncoder(this.charsetName);
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
