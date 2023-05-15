package com.element;

public class Student {
	
	public ClassRoom classRoom;

	public String name;
	public String phone; // 학생 전화번호
	public String parentName; // 학부모 이름 
	public String parentPhone; // 학부모 전화번호
	public String school; // 출신 학교
	public Boolean payment; // 월 회비 납부 확인, true: 납부완료 & false: 미납
	public int membershipFee; // 수강료
	public int absence; // 결석일수
	public boolean isDeleted; // true: 퇴원

	public int bestkorean=Integer.MAX_VALUE; //과목별 최고성적을 담기위한 변수
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
