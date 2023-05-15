package com.element;

public class ReportCard {


	public Student student;
	
	public int month;
	public int korean;
	public int math;
	public int english;
	public int add1;
	public int add2;
	//public boolean isDeleted;

	public ReportCard(Student student) {
		this.student = student;
	}
	
	public ReportCard(Student student, int month, int korean, int math, int english, int add1, int add2) {
		this.student = student;
		this.month = month;
		this.korean = korean;
		this.math = math;
		this.english = english;
		this.add1 = add1;
		this.add2 = add2;
		//this.isDeleted = isDeleted;
			
	}
	
}
