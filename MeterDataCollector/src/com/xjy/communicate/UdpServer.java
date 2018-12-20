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
			//���һϵ��ͨ������
			dc = DatagramChannel.open();//��ͨ��
			dc.configureBlocking(false);//������
			dc.bind(new InetSocketAddress(9898));//�󶨶˿�
			selector = Selector.open();//��ѡ����
			dc.register(selector, SelectionKey.OP_READ);//ע��ѡ������ָ����������
			
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
						ByteBuffer buf = ByteBuffer.allocate(1024);//���仺����
						dc.receive(buf);
						//��-->ȡ flip
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
