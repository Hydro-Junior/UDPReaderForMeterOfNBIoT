package com.xjy.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DeviceTmp {
	private String addr; // ˮ���ַ
	private int readDate; // ��ȡ����
	private int centerID; // ������ID
	private BigDecimal showValue; // �����
	private BigDecimal fshowValue; // ��ֵ����
	private int meterState; // �豸״̬
	private int commState; // ͨѶ״̬
	private int isUse; // �Ƿ�ʹ��
	private Timestamp readTime; // ��ȡʱ��
	private String enprNo; // ˮ˾����

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getReadDate() {
		return readDate;
	}

	public void setReadDate(int readDate) {
		this.readDate = readDate;
	}

	public int getCenterID() {
		return centerID;
	}

	public void setCenterID(int centerID) {
		this.centerID = centerID;
	}



	public BigDecimal getShowValue() {
		return showValue;
	}

	public void setShowValue(BigDecimal showValue) {
		this.showValue = showValue;
	}

	public BigDecimal getFshowValue() {
		return fshowValue;
	}

	public void setFshowValue(BigDecimal fshowValue) {
		this.fshowValue = fshowValue;
	}

	public int getMeterState() {
		return meterState;
	}

	public void setMeterState(int meterState) {
		this.meterState = meterState;
	}

	public int getCommState() {
		return commState;
	}

	public void setCommState(int commState) {
		this.commState = commState;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public Timestamp getReadTime() {
		return readTime;
	}

	public void setReadTime(Timestamp readTime) {
		this.readTime = readTime;
	}

	public String getEnprNo() {
		return enprNo;
	}

	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}
}
