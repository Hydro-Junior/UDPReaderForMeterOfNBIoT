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
		DatagramChannel dc = DatagramChannel.open();//��ͨ��
		dc.configureBlocking(false);//���÷�����
		
		ByteBuffer buf = ByteBuffer.allocate(1024);//���仺����
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			String str = sc.next();
			buf.put((new Date().toString()+":\n"+str).getBytes());//д�뻺����
			//��-->ȡ flip
			buf.flip();
			dc.send(buf,new InetSocketAddress("127.0.0.1", 9898));//�ӻ�������ȡ��������
			buf.clear();
		}
		dc.close();
	}
	@Test
	public void receive() throws IOException {
		DatagramChannel dc = DatagramChannel.open();//��ͨ��
		dc.configureBlocking(false);//������
		dc.bind(new InetSocketAddress(9898));
		Selector selector = Selector.open();//���ն���Ҫ��ѡ����
		dc.register(selector, SelectionKey.OP_READ);//ע��ѡ������ָ����������
		
		while(selector.select() > 0) {
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while(it.hasNext()) {
				SelectionKey sk = it.next();
				
				if(sk.isReadable()) {
					ByteBuffer buf = ByteBuffer.allocate(1024);//���仺����
					dc.receive(buf);
					//��-->ȡ flip
					buf.flip();
					System.out.println(new String(buf.array(),0,buf.limit()));
					buf.clear();
				}
			}
		}
	}
	
}
