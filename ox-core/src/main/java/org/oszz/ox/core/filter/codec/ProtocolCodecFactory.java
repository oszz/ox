package org.oszz.ox.core.filter.codec;

/**
 * 协议编解码工厂接口
 * @author ZZ
 *
 */
public interface ProtocolCodecFactory {

    public ProtocolDecoder getDecoder() throws Exception;
 
    public ProtocolEncoder getEncoder() throws Exception;
}
