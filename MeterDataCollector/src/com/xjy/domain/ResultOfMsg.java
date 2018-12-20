package com.xjy.domain;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gson.Gson;

public class ResultOfMsg {
	String address;//���ַ
	Double readValue;//����
	PeriodType reportPeriod;//�ϱ�����
	Timestamp reportTime;//�ϱ�ʱ��
	Timestamp meterClock;//��ʱ��
	Boolean sumCheckResult;//��У����
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getReadValue() {
		return readValue;
	}
	public void setReadValue(Double readValue) {
		this.readValue = readValue;
	}
	public PeriodType getReportPeriod() {
		return reportPeriod;
	}
	public void setReportPeriod(PeriodType reportPeriod) {
		this.reportPeriod = reportPeriod;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Timestamp reportTime) {
		this.reportTime =  reportTime;
	}
	public Date getMeterClock() {
		return meterClock;
	}
	public void setMeterClock(Timestamp meterClock) {
		this.meterClock =  meterClock;
	}
	public Boolean getSumCheckResult() {
		return sumCheckResult;
	}
	public void setSumCheckResult(Boolean sumCheckResult) {
		this.sumCheckResult = sumCheckResult;
	}
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this).toString();
	}
	
	
}

