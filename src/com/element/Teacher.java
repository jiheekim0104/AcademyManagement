package com.element;



public class Teacher {
	public ClassRoom classRoom;
	public Receipt receipt;
	
	
	public String name;
	public int basicPay; // �⺻�޿�
	public int additionalPay; // ������
	
	public Teacher(String name,int basicPay) {
		this.name = name;
		this.basicPay = basicPay;
	}
	
}
