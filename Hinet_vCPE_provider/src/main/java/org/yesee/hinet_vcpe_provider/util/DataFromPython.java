package org.yesee.hinet_vcpe_provider.util;

public class DataFromPython {
	
	private int value1;
	private String value2;
	private int value3;
	
	DataFromPython() {

	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public int getValue3() {
		return value3;
	}

	public void setValue3(int value3) {
		this.value3 = value3;
	}

	@Override
	public String toString() {
		return "DataFromPython [value1=" + value1 + ", value2=" + value2 + ", value3=" + value3 + "]";
	}
	
	

}
