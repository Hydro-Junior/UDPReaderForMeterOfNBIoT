package com.xjy.messageHandler;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.xjy.domain.PeriodType;
import com.xjy.domain.ResultOfMsg;
import com.xjy.util.MsgHandleUtil;

public class MessageHandler implements Runnable {
	String msg = null;

	public MessageHandler(String str) {
		this.msg = str;
	}

	@Override
	// ��theMessage�е���Ϣ����ΪJson��ʽ��������Ҫ���ִ������ݿ�
	public void run() {
		// ��ӡ�յ���ԭ����
		Logger logger = Logger.getLogger(MessageHandler.class);
		logger.info("�����߳�" + Thread.currentThread().getName() + "�յ�ԭ����\n" + msg);
		//System.out.println("�����߳�" + Thread.currentThread().getName() + "�յ�ԭ����\n" + msg);
		if (!((msg.endsWith("16") && msg.startsWith("68"))) || // �����ϱ�׼����ͷβ
				msg.length() < 20) {// ���Ȳ����ϱ�׼
			//System.out.println("��������Ѷ�����");
			logger.error("��������Ѷ�����\n");
			return;
		}
		// ������У��
		int effectivelen;
		if (!((effectivelen = MsgHandleUtil.effectiveLength(msg)) > 0 && (msg.length() / 2) - 8 == effectivelen)) {// �����������Ч���ȴ����㣬����Ч���ȵ����ܳ���8�ֽ�
			/*System.out.println("���Ƚ�����������");
			System.out.println("ԭ���ĳ��ȣ�     " + msg.length());
			System.out.println("����������Ч���ȣ�    " + effectivelen);*/
			logger.error("���Ƚ�����������\n"+"ԭ���ĳ��ȣ�     " + msg.length()+"\n����������Ч���ȣ�    " + effectivelen);
			
			return;
		}
		// ����У��
		if (!MsgHandleUtil.checkSum(msg)) {
			//System.out.println("��У������");
			logger.error("��У������\n");
			return;
		}
		ResultOfMsg meterResult = new ResultOfMsg();
		meterResult.setSumCheckResult(true);
		
		// �������ַ
		String meterAddr = MsgHandleUtil.reverseMsg(msg.substring(14, 24));
		meterResult.setAddress(meterAddr);

		// �ϱ�����
		String period = msg.substring(42, 44);
		if (period.equals(PeriodType.ONCE_A_MONTH.getTypecode()))
			meterResult.setReportPeriod(PeriodType.ONCE_A_MONTH);
		else if (period.equals(PeriodType.ONCE_A_DAY.getTypecode()))
			meterResult.setReportPeriod(PeriodType.ONCE_A_DAY);
		else if (period.equals(PeriodType.ONCE_AN_HOUR.getTypecode()))
			meterResult.setReportPeriod(PeriodType.ONCE_AN_HOUR);
		else {
			//System.out.println("���������δ֪���ͣ�");
			logger.error("���������δ֪���ͣ�\n");}
		// System.out.println("�������ͣ� " + meterResult.getReportPeriod());

		// �ϱ�ʱ��
		StringBuilder timeString = new StringBuilder(MsgHandleUtil.reverseMsg(msg.substring(44, 44 + 12)));
		String reptimeStr = timeString.replace(2, 4, monthStr(msg.substring(52, 54))).toString();
		meterResult.setReportTime(strToTime(reptimeStr));

		// ��ʱ��
		StringBuilder clockString = new StringBuilder(MsgHandleUtil.reverseMsg(msg.substring(56, 56 + 12)));
		String clocktimeStr = clockString.replace(2, 4, monthStr(msg.substring(64, 66))).toString();
		meterResult.setMeterClock(strToTime(clocktimeStr));

		// �����
		String valueStr = MsgHandleUtil.reverseMsg(msg.substring(68, 68 + 8));
		double res = Double.parseDouble(valueStr) / 100;
		meterResult.setReadValue(res);

		//System.out.println(meterResult);
		logger.info(meterResult);
	}

	// һ�������������·ݽ�����ʱ����,ȡ��5λ��Ч��Ϣ
	public static String monthStr(String str) {
		if (str.length() != 2)
			return null;// ֻ�����ַ�������Ϊ2�����
		int tmp = Integer.parseInt(str, 16);
		int unit = tmp & 0x0f;// �õ���λ
		String res = String.valueOf(unit);
		if ((tmp & 0x10) > 0) {
			res = "1" + res;
		} else {
			res = "0" + res;
		}
		return res;
	}

	// ��12λ�ַ���תΪʱ��
	public static Timestamp strToTime(String str) {
		Timestamp res = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		try {
			res = new Timestamp(sdf.parse(str).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}

}