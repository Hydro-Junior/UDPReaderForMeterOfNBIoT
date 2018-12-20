package com.xjy.domain;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gson.Gson;

public class ResultOfMsg {
	String address;//表地址
	Double readValue;//读数
	PeriodType reportPeriod;//上报周期
	Timestamp reportTime;//上报时间
	Timestamp meterClock;//表时钟
	Boolean sumCheckResult;//和校验结果
	
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

