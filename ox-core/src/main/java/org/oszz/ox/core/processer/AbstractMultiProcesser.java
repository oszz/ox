package org.oszz.ox.core.processer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.message.IMessage;

public abstract class AbstractMultiProcesser extends AbstractProcesser {

	private ExecutorService executorService;

	public AbstractMultiProcesser(int size){
		super();
		executorService = Executors.newFixedThreadPool(size);
	}
	
	@Override
	public void start(){
//		while(true) {
//			ProcesserMessage pm = null;
//			try {
//				pm = msgQueue.poll();
//				if(pm != null){
//					MsgRunnable msgRun = new MsgRunnable(pm);
//					executorService.execute(msgRun);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				log.error("处理消息出错：玩家id-{},消息编码-{},错误信息-{}.", 
//						pm.getPalyer(), pm.getMessage().getCode(), e);
//			}
//		}
		
	}
	@Override
	public void putMessage(IPlayer palyer,IMessage message) {
		super.putMessage(palyer, message);
		try {
			ProcesserMessage pm = msgQueue.take();
			executorService.submit(new MsgRunnable(pm));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void stop() {
		executorService.shutdown();
	}
	
	class MsgRunnable implements Runnable {

		private ProcesserMessage pm = null;
		
		public MsgRunnable(ProcesserMessage pm){
			this.pm = pm;
		}
		
		@Override
		public void run() {
			IMessage message = pm.getMessage();
			message.execute(pm.getPlayer());
			message.getAsynResponseProcesser().complete();
		}
		
	}
	
}
