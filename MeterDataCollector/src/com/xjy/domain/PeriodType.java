package com.xjy.domain;

public enum PeriodType{
	ONCE_A_MONTH("C1"),ONCE_A_DAY("81"),ONCE_AN_HOUR("41");
	private String typecode;

	private PeriodType(String typecode) {
		this.setTypecode(typecode);
	}

	public String getTypecode() {
		return typecode;
	}

	private void setTypecode(String typecode) {
		this.typecode = typecode;
	}
}