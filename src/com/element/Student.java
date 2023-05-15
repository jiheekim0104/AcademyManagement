package com.element;

public class Student {
	
	public ClassRoom classRoom;

	public String name;
	public String phone; // �л� ��ȭ��ȣ
	public String parentName; // �кθ� �̸� 
	public String parentPhone; // �кθ� ��ȭ��ȣ
	public String school; // ��� �б�
	public Boolean payment; // �� ȸ�� ���� Ȯ��, true: ���οϷ� & false: �̳�
	public int membershipFee; // ������
	public int absence; // �Ἦ�ϼ�
	public boolean isDeleted; // true: ���

	public int bestkorean=Integer.MAX_VALUE; //���� �ְ����� ������� ����
	public int bestmath=Integer.MAX_VALUE;
	public int bestenglish=Integer.MAX_VALUE;
	public int bestadd1=Integer.MAX_VALUE;
	public int bestadd2=Integer.MAX_VALUE;
	
	public Student (String name,String phone,String parentName,String parentPhone,String school,Boolean payment,int membershipFee,int absence,Boolean isDeleted) {
		this.name = name;
		this.phone = phone;
		this.parentName = parentName;
		this.parentPhone = parentPhone;
		this.school = school;
		this.payment = payment;
		this.membershipFee = membershipFee;
		this.absence = absence;
		this.isDeleted = isDeleted;
	}


	public Student() {
		
	}
	
	public void subjectBestReportCard(ReportCard reportCard) {
		if(bestkorean>reportCard.korean) {
			bestkorean = reportCard.korean;
		}
		if(bestmath>reportCard.math) {
			bestmath = reportCard.math;
		}
		if(bestenglish>reportCard.english) {
			bestenglish = reportCard.english;
		}
		if(bestadd1>reportCard.add1) {
			bestadd1 = reportCard.add1;
		}
		if(bestadd2>reportCard.add2) {
			bestadd2 = reportCard.add2;
		}
		
	}
}
