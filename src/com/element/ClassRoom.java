package com.element;

import java.util.LinkedList;
import java.util.List;

public class ClassRoom {
	public List<Student> students;

	public int maxNumber; // �� �ִ� ����
	public String className;
	
	public ClassRoom(String className, int maxNumber) { // ���̸��� �ִ� �л��� ��������
		this.className = className;
		this.maxNumber = maxNumber;
		this.students = new LinkedList<>(); // �ش� �ݾȿ� �л���� �̿��ϱ� ����
	}

	
	

	
}
