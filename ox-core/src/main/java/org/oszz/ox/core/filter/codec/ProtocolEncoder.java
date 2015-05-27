package org.oszz.ox.core.filter.codec;

/**
 * 编码器接口
 * @author ZZ
 *
 */
public interface ProtocolEncoder {

	public void doEncoder(ProtocolEncoderOutput out, Object obj);
}
