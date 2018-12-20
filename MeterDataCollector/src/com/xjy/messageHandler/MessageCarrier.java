package com.xjy.messageHandler;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageCarrier implements Runnable{
	private Vector<String> messageQueue = null;
	private ExecutorService handlerPool = null;
	public MessageCarrier(Vector<String> messages) {
		this.messageQueue = messages;//指向传入的消息队列
		handlerPool = Executors.newCachedThreadPool();
	}
	@Override
	public void run() {
		while(true) {
			if(this.messageQueue.isEmpty()) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				String msToHandle = messageQueue.remove(0);
				handlerPool.execute(new MessageHandler(msToHandle));
			}
		}
		
	}
	
}
