package org.oszz.ox.core.processer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.oszz.ox.core.message.IMessage;

/**
 * 单线程处理器
 * @author ZZ
 *
 */
public abstract class AbstractSingleProcesser extends AbstractProcesser{
	private ExecutorService executorService;
	
	public AbstractSingleProcesser(){
		super();
		executorService = Executors.newSingleThreadExecutor();
	}
	
	@Override
	public void start(){
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				while(true) {
					ProcesserMessage pm = null;
					try {
						pm = msgQueue.take();
						if(pm != null){
							IMessage message = pm.getMessage();
							message.execute(pm.getPlayer());
							pm.getAsynReq().callback();
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.error("处理消息出错：玩家id-{},消息编码-{},错误信息-{}.", 
								pm.getPlayer(), pm.getMessage().getCode(), e);
					}
				}
			}
		});
	}
	
	@Override
	public void stop() {
		executorService.shutdown();
	}
}
