package org.oszz.ox.core.heartbeat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.oszz.ox.core.service.IService;

/**
 * 心跳服务
 * @author ZZ
 */
public class HeartbeatService implements IService {
	
	
	/**
	 * 心跳线程大小，默认是单线程的
	 */
	private static final int CORE_POOL_SIZE = 1;
	
	/**
	 * 心跳服务的心跳周期(单位:毫秒)<br>
	 * 默认是200毫秒
	 */
	private int period = 200;
	
	/**
	 * 心跳线程
	 */
	private ScheduledExecutorService scheduledExecutorService;
	

	@Override
	public boolean init() {
		scheduledExecutorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
		return true;
	}


	@Override
	public boolean start() {
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				
			}
		}, 0, period, TimeUnit.MILLISECONDS);
		
		return true;
	}



	@Override
	public boolean stop() {
		scheduledExecutorService.shutdown();
		return true;
	}


	@Override
	public boolean restart() {
		init();
		start();
		return false;
	}


	@Override
	public boolean create() {
		return false;
	}
	
	

}
