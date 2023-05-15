package com.service;

import java.util.LinkedList;
import java.util.List;
import com.Academy;
import com.element.Teacher;

public class TeacherService {
	private Academy academy;
	List<Teacher> teachers;

	public TeacherService(Academy academy) {
		this.academy = academy;
		this.teachers = new LinkedList<Teacher>();
	}
	
	public void init() {
		add(new Teacher("������T",400));
		add(new Teacher("������T",380));
		add(new Teacher("ǥ����T",370));
		
		

	}

	private void add(Teacher teacher) {
		teachers.add(teacher);
		
	}


}
