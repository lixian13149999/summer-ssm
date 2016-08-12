package com.bcdbook.summer.demo.enumtest;

public enum Gender {
	MAN(1),WOMEN(0);
	private final Integer value;
	Gender(Integer value){
		this.value = value;
	}
	public Integer getValue(){
		return value;
	}
}
