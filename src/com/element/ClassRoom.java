package com.element;

import java.util.LinkedList;
import java.util.List;

public class ClassRoom {
	public List<Student> students;

	public int maxNumber; // 반 최대 정원
	public String className;
	
	public ClassRoom(String className, int maxNumber) { // 반이름과 최대 학생수 수정가능
		this.className = className;
		this.maxNumber = maxNumber;
		this.students = new LinkedList<>(); // 해당 반안에 학생들로 이용하기 위함
	}

	
	

	
}
