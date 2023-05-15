package com.service;

import java.util.LinkedList;
import java.util.List;

import com.Academy;
import com.element.ClassRoom;
import com.element.ReportCard;
import com.element.Student;

public class ClassRoomService {
	
	public Academy academy;
	List<ClassRoom> classRooms;
	public ClassRoomService(Academy academy) {
		this.academy=academy;
		this.classRooms = new LinkedList<>();
	}
	
	public void init() { // ���̸��� �л��� ��������
		add(new ClassRoom("��ġ��&SKY��", 5));
		add(new ClassRoom("������&�߰�ܽù�", 5));
		add(new ClassRoom("IN�����", 5));
		add(new ClassRoom("�������", 5));
	}
	
	private void add(ClassRoom classRoom) {
		classRooms.add(classRoom);
	}

	public ClassRoom classify(ReportCard reportCard) { // �Է¹��� ������������ ���� �з��ϴ� �޼ҵ�
		int score = reportCard.korean+reportCard.math+reportCard.english+reportCard.add1+reportCard.add2;
		int index = -1;
		if(score<=8)
			index=0;
		else if(score<=11)
			index=1;
		else if(score<=15)
			index=2;
		else if(score<=20)
			index=3;
		if(index<0||index>=classRooms.size()) // �ε����� -1�̰ų� �ش米���� ũ�⺸�� ũ�ٸ� 
			return null; // �ش��ϴ� ������ ���ٰ� ����
		return classRooms.get(index); // �ش�Ǵ� ���� ������

	
	}

	public boolean isFull(ClassRoom classRoom) { // �ش���� �ο��� �ִ��������� Ȯ���ϴ� �޼ҵ�
		return classRoom.maxNumber <= classRoom.students.size(); // �ش���� �ִ��ο�(5)���� �Ҽӵ� �л��� ���� ���ų� ���ٸ� TRUE ����
	}

	public void enter(Student student, ClassRoom classRoom) { // ���ǿ� �´� �л��� �ݿ� �ִ� �޼ҵ�
		classRoom.students.add(student);
	}

	public void remove(Student student, ClassRoom classRoom) { // �ش��л��� �ݿ��� �����ϴ� �޼ҵ�
		classRoom.students.remove(student);
		
	}
	


	
	
	
}
