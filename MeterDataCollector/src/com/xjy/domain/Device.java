package com.xjy.domain;

import java.sql.Timestamp;

public class Device {
	private int id;
	private String name;
	private String iAddr;// ���
	private String deviceNo;

	// �豸�Ƿ�ʹ��״̬ 0����ʹ�ã�1�ѷ���
	private int status;
	private int runStatue;// ����״̬,0�����ߣ�2����
	private int BigDeviceFlag;//Ĭ����0��ʾС��2��ʾ���

	
	private int strobeStatue;// 0�޷�,1����2��
	private Timestamp StrobeDate;// ����ִ��ʱ��
	private int strobeExecute;// ������Ҫ״̬
	private String userAddr;// �û���ַ
	private String enprNo;// ˮ˾����
	private Timestamp readTime;//����ȡʱ��
	
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
	// ��ǰ״̬,0������1����С�ڵ��ϴζ�����2λ��ʾ�����澯��3λ��ʾ ͨ���쳣��4��ʾ��ǰֵ�澯
	private int nowStatue;
	// ͨ��״̬,0������1����ʧ�ܣ�2�ڵ�ʧ��,3�ֳ���
	private int commStatue;
	// �豸״̬,0����������1��Դ�澯��2ǿ�Ÿ澯��3, ��Դǿ�Ÿ澯4�źŹ��ϣ�5��Դ�źŹ��ϣ�6ǿ���źŹ��ϣ�7ȫ�澯
	private int deviceStatue;
	

}
