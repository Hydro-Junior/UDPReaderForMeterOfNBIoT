package com.xjy.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DeviceTmp {
	private String addr; // 水表地址
	private int readDate; // 读取日子
	private int centerID; // 集中器ID
	private BigDecimal showValue; // 表读数
	private BigDecimal fshowValue; // 峰值读数
	private int meterState; // 设备状态
	private int commState; // 通讯状态
	private int isUse; // 是否使用
	private Timestamp readTime; // 读取时间
	private String enprNo; // 水司编码

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
