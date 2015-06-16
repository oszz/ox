package org.oszz.ox.core.processer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
							pm.getMsgHandler().handle(pm.getPalyer(), pm.getMessage());
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.error("处理消息出错：玩家id-{},消息编码-{},错误信息-{}.", 
								pm.getPalyer(), pm.getMessage().getCode(), e);
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
