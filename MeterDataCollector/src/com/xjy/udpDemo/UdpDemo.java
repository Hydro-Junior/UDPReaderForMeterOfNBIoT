package com.xjy.udpDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

public class UdpDemo {
	@Test
	public void send() throws IOException {
		DatagramChannel dc = DatagramChannel.open();//开通道
		dc.configureBlocking(false);//设置非阻塞
		
		ByteBuffer buf = ByteBuffer.allocate(1024);//分配缓冲区
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			String str = sc.next();
			buf.put((new Date().toString()+":\n"+str).getBytes());//写入缓冲区
			//存-->取 flip
			buf.flip();
			dc.send(buf,new InetSocketAddress("127.0.0.1", 9898));//从缓冲区中取出并发送
			buf.clear();
		}
		dc.close();
	}
	@Test
	public void receive() throws IOException {
		DatagramChannel dc = DatagramChannel.open();//开通道
		dc.configureBlocking(false);//非阻塞
		dc.bind(new InetSocketAddress(9898));
		Selector selector = Selector.open();//接收端需要打开选择器
		dc.register(selector, SelectionKey.OP_READ);//注册选择器，指定操作类型
		
		while(selector.select() > 0) {
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while(it.hasNext()) {
				SelectionKey sk = it.next();
				
				if(sk.isReadable()) {
					ByteBuffer buf = ByteBuffer.allocate(1024);//分配缓冲区
					dc.receive(buf);
					//存-->取 flip
					buf.flip();
					System.out.println(new String(buf.array(),0,buf.limit()));
					buf.clear();
				}
			}
		}
	}
	
}
