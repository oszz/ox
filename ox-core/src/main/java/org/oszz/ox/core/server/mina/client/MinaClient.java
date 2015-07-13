package org.oszz.ox.core.server.mina.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.server.mina.MinaIoHandler;
import org.oszz.ox.core.server.mina.codec.ProtoBufCodecFactory;

public class MinaClient {

	private SocketConnector socketConnector;  
  
    private String host;  
  
    private int port; 
    private ConnectFuture connectFuture;
    private IoSession session;
    
    public MinaClient(String serverIP, int port, String charsetName){
    	this.host = serverIP;
    	this.port = port;
    	socketConnector = new NioSocketConnector();  
        socketConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ProtoBufCodecFactory(Charset.forName(charsetName))));  
        socketConnector.setHandler(new MinaIoHandler()); 
    }
    
    public void connect(){
    	InetSocketAddress addr = new InetSocketAddress(host, port);  
        connectFuture = socketConnector.connect(addr);  
       
    }
    
    public void send(IMessage message){
    	session = connectFuture.getSession();
    	session.write(message);
    }
    
}
