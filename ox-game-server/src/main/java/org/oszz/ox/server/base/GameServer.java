package org.oszz.ox.server.base;

import java.util.ArrayList;
import java.util.List;

import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.core.Globals;
import org.oszz.ox.core.conf.BaseConfig;
import org.oszz.ox.core.heartbeat.HeartbeatService;
import org.oszz.ox.core.processer.IProcesserService;
import org.oszz.ox.core.processer.ProcesserService;
import org.oszz.ox.core.server.IServer;
import org.oszz.ox.core.server.jetty.IJettySessionFactory;
import org.oszz.ox.core.server.jetty.JettyHashSessionFactory;
import org.oszz.ox.core.server.jetty.JettyServer;
import org.oszz.ox.core.server.mina.MinaServer;
import org.oszz.ox.core.service.IService;
import org.oszz.ox.core.service.ISystemService;
import org.oszz.ox.core.template.ITemplateService;
import org.oszz.ox.core.template.TemplateService;
import org.oszz.ox.server.base.conf.DBConfig;
import org.oszz.ox.server.base.conf.JettyServerConfig;
import org.oszz.ox.server.base.conf.RedisConfig;
import org.oszz.ox.server.base.conf.ServerConfig;
import org.oszz.ox.server.base.conf.TemplateConfig;
import org.oszz.ox.server.base.dom.HumanDataManagerRegister;
import org.oszz.ox.server.base.dom.ModuleServiceRegister;
import org.oszz.ox.server.base.dom.TemplateDataRegister;
import org.oszz.ox.server.base.handler.OXServerHandler;

/**
 * GameServer 
 * @author ZZ
 *
 */
public class GameServer {
	
	public GameServer(String configFilePath){
		Globals.create();
		
		Globals.addAllConfigClasses(getConfigClasses());
		Globals.initConfig(configFilePath);
		
		Globals.addAllService(getSystemService());
		
		Globals.setModuleServiceRegister(new ModuleServiceRegister());
//		Globals.setMessageCodeRegister(new MessageCodeRegister());
		Globals.setTemplateDataRegister(new TemplateDataRegister());
		Globals.setHumanDataManagerRegister(new HumanDataManagerRegister());
		
		//----先初始化配置，再初始化其他----
		Globals.init();
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
		ServerConfig serverConfig = Globals.getCofing(ServerConfig.class);
		TemplateConfig templateConfig = Globals.getCofing(TemplateConfig.class);
		
		List<IService> systemServices = new ArrayList<IService>();//系统服务列表
		
		ISystemService heartbeatService = new HeartbeatService();//心跳服务
		
		IProcesserService processerService = new ProcesserService();//线程服务
		
		processerService.setAsynThreadSize(serverConfig.getAsynThreadSize());
		processerService.setSceneNum(serverConfig.getSceneNum());
		
		ITemplateService templateService = new TemplateService();//模板服务
		String language = serverConfig.getLanguage();
		String dirPath = templateConfig.getDirPath();
		dirPath = dirPath + SystemProperty.FILE_SEPARATOR.getValue() + 
				language + SystemProperty.FILE_SEPARATOR.getValue(); 
		templateConfig.setDirPath(dirPath);
		templateService.setTemplateConfig(templateConfig);
		
		systemServices.add(heartbeatService);
		systemServices.add(processerService);
		systemServices.add(templateService);
		
		return systemServices;
	}
	
	
	public void start() throws Exception {
		startJettyServer();
//		startMinaServer();
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
	
	private static void startMinaServer() throws Exception {
		ServerConfig serverCongfig = Globals.getCofing(ServerConfig.class);
		
		int port = 1111;
		IServer server = new MinaServer(port, serverCongfig.getCharset());
		server.start();
	}
}
