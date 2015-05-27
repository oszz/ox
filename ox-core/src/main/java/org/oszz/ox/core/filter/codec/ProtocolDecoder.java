package org.oszz.ox.core.filter.codec;

/**
 * 解码器接口
 * @author ZZ
 *
 */
public interface ProtocolDecoder {

	public void doDecoder(ProtocolDecoderOutput out, Object obj);
}
