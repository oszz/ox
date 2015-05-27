package org.oszz.ox.core.filter;

import org.oszz.ox.core.filter.codec.ProtocolCodecFactory;

public class ProtocolCodecFilter implements IFilter {
	
	private ProtocolCodecFactory protocolCodecFactory;
	
	public ProtocolCodecFilter(ProtocolCodecFactory protocolCodecFactory){
		this.protocolCodecFactory = protocolCodecFactory;
	}

	@Override
	public void doFilter() {
		// TODO Auto-generated method stub

	}

}
