package com.xjy.util;

public class MsgHandleUtil {
	//���ֽ�����ת�ɱ�׼16���Ʊ�ʾ���ַ���
	public static String bytesToHexString(byte[] bs) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < bs.length;i++) {
			int tmp = (int)bs[i]; 
			//java��byte���ͳ���127�ᱻ��Ϊ������������һ�»ָ�
			if(tmp < 0) {
				tmp += 256;
			}
			//��int����תΪʮ�����Ƶ��ַ�����ʾ������ͳһ��������ֵΪһλ�Ļ�Ҫ���㣬���Ҳ����Ż�
			String putStr = Integer.toHexString(tmp).toUpperCase();
			if(putStr.length()==1) {
				putStr = "0" + putStr;
			}
			
			sb.append(putStr);
		}
		return sb.toString();
	}
	//��У���
	public static boolean checkSum(String info) {
		//�õ�ԭУ���
		String supposedSum = info.substring(info.length()-4,info.length()-2);
		//��ȥ���4λ������У��
		String str = info.substring(0, info.length()-4);
		//����ȡ����תΪint����У���
		int sum = 0;
		for(int i = 0 ; i <= str.length()-2 ; i += 2) {
			String tmp = str.substring(i, i+2);
			sum += Integer.parseInt(tmp,16);
		}
		//������Χ��ȡ����,��Ӧ�ó�������������ȡ��8λ����
		/*if(sum > 0xff) {
			sum= ~sum;
			sum += 1;
		}*/
		sum = sum & 0xff;
		String realSum = Integer.toHexString(sum).toUpperCase();
		if(realSum.length()==1) {realSum = "0"+realSum;}
		if(realSum.equals(supposedSum)) {return true;}//У��ɹ�
		
		System.out.println("У��ʹ���");
		System.out.println("ʵ��У��ͣ�"+realSum+"  "+"�ڴ�У��ͣ�"+supposedSum);
		return false;
	}
	//��ʮ�������ַ����ߵ�λ��ת�����ֽ�Ϊ��С���ȣ�
	public static String reverseMsg(String str) {
		if(str.length()%2 != 0) {
			System.out.println("�ַ�������ӦΪż����");
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=str.length()-2;i >= 0;i -= 2) {
			String tmp = str.substring(i, i+2);
			sb.append(tmp);
		}
		
		return sb.toString();
	}
	//���������ķ�ӳ�ĳ���
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
