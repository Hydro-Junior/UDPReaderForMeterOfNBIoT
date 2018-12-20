package com.xjy.util;

public class MsgHandleUtil {
	//把字节数组转成标准16进制表示的字符串
	public static String bytesToHexString(byte[] bs) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < bs.length;i++) {
			int tmp = (int)bs[i]; 
			//java的byte类型超过127会被置为负数，这里做一下恢复
			if(tmp < 0) {
				tmp += 256;
			}
			//将int类型转为十六进制的字符串表示，便于统一处理，并且值为一位的话要补零，这块也许可优化
			String putStr = Integer.toHexString(tmp).toUpperCase();
			if(putStr.length()==1) {
				putStr = "0" + putStr;
			}
			
			sb.append(putStr);
		}
		return sb.toString();
	}
	//做校验和
	public static boolean checkSum(String info) {
		//得到原校验和
		String supposedSum = info.substring(info.length()-4,info.length()-2);
		//除去最后4位不做和校验
		String str = info.substring(0, info.length()-4);
		//两两取出，转为int型做校验和
		int sum = 0;
		for(int i = 0 ; i <= str.length()-2 ; i += 2) {
			String tmp = str.substring(i, i+2);
			sum += Integer.parseInt(tmp,16);
		}
		//超过范围的取补码,视应用场景而定，这里取低8位即可
		/*if(sum > 0xff) {
			sum= ~sum;
			sum += 1;
		}*/
		sum = sum & 0xff;
		String realSum = Integer.toHexString(sum).toUpperCase();
		if(realSum.length()==1) {realSum = "0"+realSum;}
		if(realSum.equals(supposedSum)) {return true;}//校验成功
		
		System.out.println("校验和错误！");
		System.out.println("实际校验和："+realSum+"  "+"期待校验和："+supposedSum);
		return false;
	}
	//把十六进制字符串高低位反转（以字节为最小粒度）
	public static String reverseMsg(String str) {
		if(str.length()%2 != 0) {
			System.out.println("字符串长度应为偶数！");
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=str.length()-2;i >= 0;i -= 2) {
			String tmp = str.substring(i, i+2);
			sb.append(tmp);
		}
		
		return sb.toString();
	}
	//解析出报文反映的长度
	public static int effectiveLength(String str) {
		int parsedLength = 0;
		String sub1 = reverseMsg(str.substring(2, 6));
		String sub2 = reverseMsg(str.substring(6, 10));
		if(sub1.equals(sub2)) {
			parsedLength = Integer.parseInt(sub1,16);
		}
		parsedLength = (parsedLength - 1)/4;
		return parsedLength;
	}
}
