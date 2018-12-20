package com.xjy.main;

import com.xjy.communicate.UdpServer;
import com.xjy.messageHandler.MessageCarrier;

public class Main {
	public static void main(String[] args) {
		UdpServer server = new UdpServer();
		Thread receiver = new Thread(server);
		receiver.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(new MessageCarrier(server.messageQueue)).start();
	}
}
