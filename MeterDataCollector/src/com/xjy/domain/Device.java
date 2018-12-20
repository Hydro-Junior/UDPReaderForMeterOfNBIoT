package com.xjy.domain;

import java.sql.Timestamp;

public class Device {
	private int id;
	private String name;
	private String iAddr;// 表号
	private String deviceNo;

	// 设备是否使用状态 0正常使用，1已废弃
	private int status;
	private int runStatue;// 运行状态,0不在线，2在线
	private int BigDeviceFlag;//默认是0表示小表，2表示大表

	
	private int strobeStatue;// 0无阀,1开，2关
	private Timestamp StrobeDate;// 阀门执行时间
	private int strobeExecute;// 阀门需要状态
	private String userAddr;// 用户地址
	private String enprNo;// 水司编码
	private Timestamp readTime;//最后读取时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getiAddr() {
		return iAddr;
	}
	public void setiAddr(String iAddr) {
		this.iAddr = iAddr;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRunStatue() {
		return runStatue;
	}
	public void setRunStatue(int runStatue) {
		this.runStatue = runStatue;
	}
	public int getStrobeStatue() {
		return strobeStatue;
	}
	public void setStrobeStatue(int strobeStatue) {
		this.strobeStatue = strobeStatue;
	}
	public Timestamp getStrobeDate() {
		return StrobeDate;
	}
	public void setStrobeDate(Timestamp strobeDate) {
		StrobeDate = strobeDate;
	}
	public int getStrobeExecute() {
		return strobeExecute;
	}
	public void setStrobeExecute(int strobeExecute) {
		this.strobeExecute = strobeExecute;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getEnprNo() {
		return enprNo;
	}
	public void setEnprNo(String enprNo) {
		this.enprNo = enprNo;
	}
	public Timestamp getReadTime() {
		return readTime;
	}
	public void setReadTime(Timestamp readTime) {
		this.readTime = readTime;
	}
	public int getNowStatue() {
		return nowStatue;
	}
	public void setNowStatue(int nowStatue) {
		this.nowStatue = nowStatue;
	}
	public int getCommStatue() {
		return commStatue;
	}
	public void setCommStatue(int commStatue) {
		this.commStatue = commStatue;
	}
	public int getDeviceStatue() {
		return deviceStatue;
	}
	public void setDeviceStatue(int deviceStatue) {
		this.deviceStatue = deviceStatue;
	}
	public int getBigDeviceFlag() {
		return BigDeviceFlag;
	}
	public void setBigDeviceFlag(int bigDeviceFlag) {
		BigDeviceFlag = bigDeviceFlag;
	}
	// 当前状态,0正常，1读数小于当上次读数，2位表示用量告警，3位表示 通信异常，4表示当前值告警
	private int nowStatue;
	// 通读状态,0正常，1读表失败，2节点失败,3手抄表
	private int commStatue;
	// 设备状态,0所有正常，1电源告警，2强磁告警，3, 电源强磁告警4信号故障，5电源信号故障，6强磁信号故障，7全告警
	private int deviceStatue;
	

}
