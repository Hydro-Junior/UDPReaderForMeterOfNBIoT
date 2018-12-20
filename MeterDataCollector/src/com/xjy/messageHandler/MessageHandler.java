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
	// 将theMessage中的信息解析为Json格式，并将必要部分存入数据库
	public void run() {
		// 打印收到的原报文
		Logger logger = Logger.getLogger(MessageHandler.class);
		logger.info("处理线程" + Thread.currentThread().getName() + "收到原报文\n" + msg);
		//System.out.println("处理线程" + Thread.currentThread().getName() + "收到原报文\n" + msg);
		if (!((msg.endsWith("16") && msg.startsWith("68"))) || // 不符合标准报文头尾
				msg.length() < 20) {// 长度不符合标准
			//System.out.println("错误包，已丢弃！");
			logger.error("错误包，已丢弃！\n");
			return;
		}
		// 做长度校验
		int effectivelen;
		if (!((effectivelen = MsgHandleUtil.effectiveLength(msg)) > 0 && (msg.length() / 2) - 8 == effectivelen)) {// 如果不满足有效长度大于零，且有效长度等于总长减8字节
			/*System.out.println("长度解析出现问题");
			System.out.println("原报文长度：     " + msg.length());
			System.out.println("解析出的有效长度：    " + effectivelen);*/
			logger.error("长度解析出现问题\n"+"原报文长度：     " + msg.length()+"\n解析出的有效长度：    " + effectivelen);
			
			return;
		}
		// 做和校验
		if (!MsgHandleUtil.checkSum(msg)) {
			//System.out.println("和校验有误！");
			logger.error("和校验有误！\n");
			return;
		}
		ResultOfMsg meterResult = new ResultOfMsg();
		meterResult.setSumCheckResult(true);
		
		// 解析表地址
		String meterAddr = MsgHandleUtil.reverseMsg(msg.substring(14, 24));
		meterResult.setAddress(meterAddr);

		// 上报周期
		String period = msg.substring(42, 44);
		if (period.equals(PeriodType.ONCE_A_MONTH.getTypecode()))
			meterResult.setReportPeriod(PeriodType.ONCE_A_MONTH);
		else if (period.equals(PeriodType.ONCE_A_DAY.getTypecode()))
			meterResult.setReportPeriod(PeriodType.ONCE_A_DAY);
		else if (period.equals(PeriodType.ONCE_AN_HOUR.getTypecode()))
			meterResult.setReportPeriod(PeriodType.ONCE_AN_HOUR);
		else {
			//System.out.println("周期域出现未知类型！");
			logger.error("周期域出现未知类型！\n");}
		// System.out.println("周期类型： " + meterResult.getReportPeriod());

		// 上报时间
		StringBuilder timeString = new StringBuilder(MsgHandleUtil.reverseMsg(msg.substring(44, 44 + 12)));
		String reptimeStr = timeString.replace(2, 4, monthStr(msg.substring(52, 54))).toString();
		meterResult.setReportTime(strToTime(reptimeStr));

		// 表时钟
		StringBuilder clockString = new StringBuilder(MsgHandleUtil.reverseMsg(msg.substring(56, 56 + 12)));
		String clocktimeStr = clockString.replace(2, 4, monthStr(msg.substring(64, 66))).toString();
		meterResult.setMeterClock(strToTime(clocktimeStr));

		// 表读数
		String valueStr = MsgHandleUtil.reverseMsg(msg.substring(68, 68 + 8));
		double res = Double.parseDouble(valueStr) / 100;
		meterResult.setReadValue(res);

		//System.out.println(meterResult);
		logger.info(meterResult);
	}

	// 一个辅助方法：月份解析的时候用,取低5位有效信息
	public static String monthStr(String str) {
		if (str.length() != 2)
			return null;// 只处理字符串长度为2的情况
		int tmp = Integer.parseInt(str, 16);
		int unit = tmp & 0x0f;// 得到个位
		String res = String.valueOf(unit);
		if ((tmp & 0x10) > 0) {
			res = "1" + res;
		} else {
			res = "0" + res;
		}
		return res;
	}

	// 把12位字符串转为时间
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