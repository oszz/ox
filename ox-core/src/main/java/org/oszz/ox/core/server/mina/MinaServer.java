package org.oszz.ox.core.server.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.oszz.ox.core.server.IRequestHandler;
import org.oszz.ox.core.server.IServer;
import org.oszz.ox.core.server.ISessionFactory;
import org.oszz.ox.core.server.mina.codec.ProtoBufCodecFactory;

public class MinaServer implements IServer {
	
	private final int port; 
	private IoAcceptor acceptor;
	
	public MinaServer(int port, String charsetName) {
		this.port = port;  
        // 定义一个接收客户端请求的Acceptor  
        acceptor = new NioSocketAcceptor();  
          
        // 1. 定义Logging的IoFilters  
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());  
        // 2. 定义解析器的IoFilters，将客户端传入的流或协议数据按照定义的解析器进行解析  
//        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"))));  
        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new ProtoBufCodecFactory(Charset.forName(charsetName))));  
        
        // 3. 设置业务处理机制，定义接收连接后的信息接收，信息回复的处理方法  
//        acceptor.setHandler(new DemoTimeServerHandler());  
        //NioSocketAcceptor配置       acceptor.getSessionConfig().setReadBufferSize(2048);  
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);  
        acceptor.setHandler(new MinaIoHandler());
       
	}

	@Override
	public void setHandler(IRequestHandler requestHandler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() throws Exception {
		 //定义完成，绑定端口  
        acceptor.bind(new InetSocketAddress(this.port));  

	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void addContext(int port, String... contextPaths) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSessionFactory(ISessionFactory sessionFactory) {
		// TODO Auto-generated method stub

	}

}
