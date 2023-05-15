package com.element;



public class Teacher {
	public ClassRoom classRoom;
	public Receipt receipt;
	
	
	public String name;
	public int basicPay; // 기본급여
	public int additionalPay; // 성과금
	
	public Teacher(String name,int basicPay) {
		this.name = name;
		this.basicPay = basicPay;
	}
	
}
