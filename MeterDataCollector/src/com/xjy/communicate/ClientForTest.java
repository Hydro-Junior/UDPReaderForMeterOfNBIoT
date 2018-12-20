package com.xjy.communicate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

import org.junit.Test;

public class ClientForTest {
	public static final String meterData= "68910091006888120000000000008C60070100004D4100151801831823151409A31870062600000000008316";
	public static final byte[] meterByteData = new byte[]{0x68,(byte) 0x91,0x00,(byte) 0x91,0x00,0x68,
														(byte) 0x88,0x12,0x00,0x00,0x00,0x00,
														0x00,0x00,(byte) 0x8C,0x60,0x07,0x01,
														0x00,0x00,0x4D,0x41,0x00,0x15,
														0x18,0x01,(byte) 0x83,0x18,0x23,0x15,
														0x14,0x09,(byte) 0xA3,0x18,0x70,0x06,
														0x26,0x00,0x00,0x00,0x00,0x00,
														(byte) 0x83,0x16};
	public static final byte[] meterByteData2 = new byte[] {0x68,(byte) 0x91 ,0x00 ,(byte) 0x91 ,0x00, 0x68, (byte) 0x88, 0x12, 0x00 ,
			0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,(byte) 0x8C ,0x60 ,0x07 ,0x01 ,0x00 ,0x00 ,0x3C ,(byte) 0xC1 ,0x00 ,0x30 ,0x16 ,0x31,
			(byte) 0xC3 ,0x18 ,0x55, 0x09 ,0x17, 0x31, (byte) 0xC3 ,0x18 ,(byte) 0x80, 0x59 ,0x44 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x6D ,
			0x16 
	};
	public static final byte[] meterByteData3 = new byte[] {0x68,(byte) 0x91 ,0x00 ,(byte) 0x91 ,0x00 ,0x68 ,(byte) 0x88 ,0x12 
			,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,(byte) 0x8C ,0x60 ,0x07 ,0x01 ,0x00 ,0x00 ,0x3C ,(byte) 0xC1 ,0x00 ,0x30
			,0x16 ,0x31 ,(byte) 0xC3 ,0x18 ,0x19 ,0x10 ,0x17 ,0x31 ,(byte) 0xC3 ,0x18 ,(byte) 0x80 ,0x59 ,0x41 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00
			,0x35 ,0x16 

	};
	public static final byte[] meterByteData4 = new byte[] {0x68,(byte) 0x91 ,0x00 ,(byte) 0x91 ,0x00 ,0x68 ,(byte) 0x88 ,0x12 
			,0x00 ,0x00 ,0x00,0x00 ,0x00 ,0x00 ,(byte) 0x8C ,0x60 ,0x07 ,0x01 ,0x00 ,0x00 ,0x3D ,(byte) 0xC1 ,0x00 ,0x14 
			,0x17 ,0x31 ,(byte) 0xC3 ,0x18 ,0x23 ,0x14 ,0x17 ,0x31 ,(byte) 0xC3 ,0x18 ,(byte) 0x80 ,0x59 ,0x44 ,0x00 ,0x00 ,0x00 ,0x00 
			,0x00 ,0x2C ,0x16


	};
	@Test
	public void send() throws IOException {
		DatagramChannel dc = DatagramChannel.open();//开通道
		dc.configureBlocking(false);//设置非阻塞
		
		ByteBuffer buf = ByteBuffer.allocate(1024);//分配缓冲区
		//Scanner sc = new Scanner(System.in);
		
		while(true) {
			String str = meterData;
			//buf.put((str).getBytes());//写入缓冲区
			buf.put(meterByteData4);
			//存-->取 flip
			buf.flip();
			dc.send(buf,new InetSocketAddress("127.0.0.1", 9898));//从缓冲区中取出并发送
			buf.clear();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//dc.close();
	}
}
