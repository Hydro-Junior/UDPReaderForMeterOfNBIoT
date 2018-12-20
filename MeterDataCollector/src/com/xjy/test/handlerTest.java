package com.xjy.test;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import com.xjy.util.MsgHandleUtil;

public class handlerTest {
	private static String info ="68910091006888120000000000008C60070100004D4100151801831823151409A31870062600000000008316"; 
	
	public static void checkSum(String s) throws UnsupportedEncodingException {
		System.out.println(s.substring(s.length()-4, s.length()-2));
	}
	@Test
	public void testCheckSum() throws UnsupportedEncodingException {
		Assert.assertTrue(MsgHandleUtil.checkSum(info));
	}
	@Test
	public void testReverse() {
		String ss = new String("70062600");
		String res = MsgHandleUtil.reverseMsg(ss);
		System.out.println(res);
	}
}
