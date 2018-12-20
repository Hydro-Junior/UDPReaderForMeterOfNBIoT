package com.xjy.communicate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Vector;

import com.xjy.util.MsgHandleUtil;

public class UdpServer implements Runnable{
	private static final int TIMEOUT = 6000;
	public Vector<String> messageQueue = null; 
	@Override
	public void run() {
		messageQueue = new Vector<String>();
		DatagramChannel dc;
		Selector selector;
		try {
			//完成一系列通道操作
			dc = DatagramChannel.open();//打开通道
			dc.configureBlocking(false);//非阻塞
			dc.bind(new InetSocketAddress(9898));//绑定端口
			selector = Selector.open();//打开选择器
			dc.register(selector, SelectionKey.OP_READ);//注册选择器，指定操作类型
			
			while(true) {
				if(selector.select(TIMEOUT) == 0) {
					System.out.println("...");
					continue;
				}
				
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while(it.hasNext()) {
					SelectionKey sk = it.next();
					if(sk.isReadable()) {
						//handleRead(sk);
						ByteBuffer buf = ByteBuffer.allocate(1024);//分配缓冲区
						dc.receive(buf);
						//存-->取 flip
						buf.flip();
						byte[] receivedBytes= new byte[buf.limit()];
						buf.get(receivedBytes);
						String tmp = MsgHandleUtil.bytesToHexString(receivedBytes);
						messageQueue.add(tmp);
						buf.clear();
						it.remove();
					}
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
