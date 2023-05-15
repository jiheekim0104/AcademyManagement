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
	
	public void init() { // 반이름과 학생수 수정가능
		add(new ClassRoom("의치한&SKY반", 5));
		add(new ClassRoom("서성한&중경외시반", 5));
		add(new ClassRoom("IN서울반", 5));
		add(new ClassRoom("국립대반", 5));
	}
	
	private void add(ClassRoom classRoom) {
		classRooms.add(classRoom);
	}

	public ClassRoom classify(ReportCard reportCard) { // 입력받은 성적을가지고 반을 분류하는 메소드
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
		if(index<0||index>=classRooms.size()) // 인덱스가 -1이거나 해당교실의 크기보다 크다면 
			return null; // 해당하는 교실이 없다고 리턴
		return classRooms.get(index); // 해당되는 교실 리턴함

	
	}

	public boolean isFull(ClassRoom classRoom) { // 해당반의 인원이 최대정원인지 확인하는 메소드
		return classRoom.maxNumber <= classRoom.students.size(); // 해당반의 최대인원(5)보다 소속된 학생의 수가 같거나 많다면 TRUE 리턴
	}

	public void enter(Student student, ClassRoom classRoom) { // 조건에 맞는 학생을 반에 넣는 메소드
		classRoom.students.add(student);
	}

	public void remove(Student student, ClassRoom classRoom) { // 해당학생을 반에서 삭제하는 메소드
		classRoom.students.remove(student);
		
	}
	


	
	
	
}
