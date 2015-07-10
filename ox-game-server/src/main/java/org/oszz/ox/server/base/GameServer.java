package org.oszz.ox.server.base;

import java.util.ArrayList;
import java.util.List;

import org.oszz.ox.core.Globals;
import org.oszz.ox.core.conf.BaseConfig;
import org.oszz.ox.core.heartbeat.HeartbeatService;
import org.oszz.ox.core.processer.IProcesserService;
import org.oszz.ox.core.processer.ProcesserService;
import org.oszz.ox.core.server.IServer;
import org.oszz.ox.core.server.jetty.IJettySessionFactory;
import org.oszz.ox.core.server.jetty.JettyHashSessionFactory;
import org.oszz.ox.core.server.jetty.JettyServer;
import org.oszz.ox.core.service.IService;
import org.oszz.ox.core.service.ISystemService;
import org.oszz.ox.core.template.ITemplateService;
import org.oszz.ox.core.template.TemplateService;
import org.oszz.ox.server.base.conf.DBConfig;
import org.oszz.ox.server.base.conf.JettyServerConfig;
import org.oszz.ox.server.base.conf.RedisConfig;
import org.oszz.ox.server.base.conf.ServerConfig;
import org.oszz.ox.server.base.conf.TemplateConfig;
import org.oszz.ox.server.base.handler.OXServerHandler;
import org.oszz.ox.server.base.mapping.ModuleServiceRegister;

/**
 * GameServer 启动程序
 * @author ZZ
 *
 */
public class GameServer {
	
	public GameServer(){
		Globals.create();
		Globals.addAllConfigClasses(getConfigClasses());
		Globals.addAllService(getSystemService());
		Globals.setModuleServiceRegister(new ModuleServiceRegister());
	}

	public void init(String configFilePath){
		Globals.initConfig(configFilePath);
		//----先初始化配置，再初始化服务----
		Globals.initService();
	}
	private List<Class<? extends BaseConfig>> getConfigClasses(){
		List<Class<? extends BaseConfig>> configClasses = new ArrayList<Class<? extends BaseConfig>>();
		configClasses.add(ServerConfig.class);
		configClasses.add(JettyServerConfig.class);
		configClasses.add(DBConfig.class);
		configClasses.add(RedisConfig.class);
		configClasses.add(TemplateConfig.class);
		return configClasses;
	}
	private List<IService> getSystemService(){
		List<IService> systemServices = new ArrayList<IService>();
		
		ISystemService heartbeatService = new HeartbeatService();//心跳服务
		
		IProcesserService processerService = new ProcesserService();//线程服务
		ServerConfig serverConfig = Globals.getCofing(ServerConfig.class);
		processerService.setAsynThreadSize(serverConfig.getAsynThreadSize());
		processerService.setSceneNum(serverConfig.getSceneNum());
		
		ITemplateService templateService = new TemplateService();//模板服务
		TemplateConfig templateConfig = Globals.getCofing(TemplateConfig.class);
		templateService.setTemplateConfig(templateConfig);
		
		systemServices.add(heartbeatService);
		systemServices.add(processerService);
		systemServices.add(templateService);
		
		return systemServices;
	}
	
	
	public void start() throws Exception {
		startJettyServer();
	}
	
	private static void startJettyServer() throws Exception {
		JettyServerConfig jsConfig = Globals.getCofing(JettyServerConfig.class);
		ServerConfig serverCongfig = Globals.getCofing(ServerConfig.class);
		
		int port = jsConfig.getPort();
		IServer jsServer = new JettyServer(serverCongfig.isDebug(), serverCongfig.getCharset());
		IJettySessionFactory jettySessionFactory = new JettyHashSessionFactory();
		jsServer.setSessionFactory(jettySessionFactory);//一定要先setSessionFactory
		
		jsServer.addContext(port, new String[]{"/"});
		
		OXServerHandler oxsHandler = new OXServerHandler(jsConfig.getResponseTimeout());
		jsServer.setHandler(oxsHandler);
		jsServer.start();
	}
}
