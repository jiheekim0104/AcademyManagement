package com.service;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.element.ClassRoom;
import com.element.ReportCard;
import com.element.Student;
import com.service.PrintHelper.GetAvg;
import com.Academy;

public class ReportCardService {

	private static final int COMMAND_REPORT_CARD_WRITE = 1;
	private static final int COMMAND_REPORT_CARD_READ_ONE_BY_STUDENT_AND_MONTH = 2;
	private static final int COMMAND_REPORT_CARD_READ_BY_STUDENT = 3;
	private static final int COMMAND_REPORT_CARD_READ_BY_MONTH = 4;
	private static final int COMMAND_REPORT_CARD_READ_BY_CLASSNAME = 5;
	private static final int COMMAND_REPORT_CARD_EDIT = 6;
	private static final int COMMAND_BREAK = 0;

	Calendar now = Calendar.getInstance();
	public Academy academy;
	List<ReportCard> reportCards;

	public ReportCardService(Academy academy) {
		this.academy = academy;
		this.reportCards = new LinkedList<>();
	}

	public void service() {
		int command;
		Student student;
		int month;
		ReportCard reportCard = null;
		List<ReportCard> reportCards = null;
		boolean isClosed = false;
		while (!isClosed) {
			System.out.println("메인>>성적----------------------");
			System.out.println("1.성적입력 2.학생명+월 3.학생명 4.월별");
			System.out.println("5.반별 6.성적수정 0.이전메뉴로");
			System.out.println("-----------------------------");
			System.out.print("선택>");
			command = academy.getInteger();
			switch (command) {
			case COMMAND_REPORT_CARD_WRITE:
				System.out.print("학생명: ");
				student = academy.studentService.getStudent(academy.getString());
				if (student == null)
					System.out.println("검색하신 이름은 없는 이름입니다.");
				else {
					reportCard = new ReportCard(student);
					System.out.print("월: ");
					reportCard.month = academy.getMonthInteger();
					System.out.print("국어: ");
					reportCard.korean = academy.getScoreInteger();
					System.out.print("수학: ");
					reportCard.math = academy.getScoreInteger();
					System.out.print("영어: ");
					reportCard.english = academy.getScoreInteger();
					System.out.print("탐구1: ");
					reportCard.add1 = academy.getScoreInteger();
					System.out.print("탐구2: ");
					reportCard.add2 = academy.getScoreInteger();
					this.reportCards.add(reportCard);
					System.out.println("'"+student.name+"'학생의 "+reportCard.month+"월 성적이 추가되었습니다.");
				}

				break;

			case COMMAND_REPORT_CARD_READ_ONE_BY_STUDENT_AND_MONTH:
				System.out.print("학생명: ");
				student = academy.studentService.getStudent(academy.getString());
				if (student == null)
					System.out.println("검색하신 이름은 없는 이름입니다.");
				else {
					System.out.print("월: ");
					month = academy.getMonthInteger();
					reportCard = getReportCard(student, month);
					if (reportCard == null) {
						System.out.println("입력하신 학생의 해당 월 성적은 입력되지않았습니다.");
					} else {
						PrintHelper.print(reportCard);
					}
				}

				break;

			case COMMAND_REPORT_CARD_READ_BY_STUDENT:
				System.out.print("학생명: ");
				student = academy.studentService.getStudent(academy.getString());
				if (student == null)
					System.out.println("검색하신 이름은 없는 이름입니다.");
				else {
					reportCards = getReportCards(student, null);
					PrintHelper.print(reportCards);
				}

				break;
			case COMMAND_REPORT_CARD_READ_BY_MONTH:
				System.out.print("월: ");
				month = academy.getMonthInteger();
				reportCards = getReportCards(null, month);
				if (reportCards.size() == 0) {
					System.out.println("입력하신 해당 월 성적은 입력되지않았습니다.");
				} else {
					PrintHelper.print(reportCards);
				}
				break;

			case COMMAND_REPORT_CARD_READ_BY_CLASSNAME:
				serviceReportCard();
				break;

			case COMMAND_REPORT_CARD_EDIT:
				System.out.print("학생명: ");
				student = academy.studentService.getStudent(academy.getString());
				if (student == null)
					System.out.println("검색하신 이름은 없는 이름입니다.");
				else {
					System.out.print("월: ");
					month = academy.getMonthInteger();
					reportCard = getReportCard(student, month);
					if (reportCard == null)
						System.out.println("입력하신 학생의 해당 월 성적은 입력되지않았습니다.");
					else {
						System.out.print("국어: ");
						reportCard.korean = academy.getScoreInteger();
						System.out.print("수학: ");
						reportCard.math = academy.getScoreInteger();
						System.out.print("영어: ");
						reportCard.english = academy.getScoreInteger();
						System.out.print("탐구1: ");
						reportCard.add1 = academy.getScoreInteger();
						System.out.print("탐구2: ");
						reportCard.add2 = academy.getScoreInteger();
						System.out.println("정상적으로 수정되었습니다. ");
					}
				}
				break;
			case COMMAND_BREAK:
				isClosed = true;
				break;

			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	private void serviceReportCard() {
		boolean isClosed = false;
		while (!isClosed) {
			System.out.println("*****************************");
			int index =1;
			List<ClassRoom> classRooms = academy.classRoomService.classRooms;
			for(ClassRoom classRoom : classRooms) {
				System.out.print((index++)+"."+classRoom.className+" ");
			}
			System.out.println(" 0.이전메뉴로");
			System.out.println("*****************************");
			System.out.print("선택>");
			index = academy.getInteger();
			if(index==0) {
				isClosed = true;
				break;
			} else if(0<index&&index<=classRooms.size()) {
				printReporcardByClass(academy.classRoomService.classRooms.get(index-1));
				
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
		}
	}

	public ReportCard getReportCard(Student student, Integer month) {
		return getReportCard(student, month, false);
	}

	public ReportCard getReportCard(Student student, Integer month, boolean includeDeleted) {
		ReportCard result = null;
		for (ReportCard reportCard : reportCards) {
//			if (includeDeleted && reportCard.isDeleted)
//				continue;
			if (student != null && reportCard.student != student) // 입력한 학생이 있고, 비교하려는 성적표의 학생과 입력한 학생이 다르다면 넘겨라!
				continue;
			if (month != null && reportCard.month != month) // 입력한 달이 있고, 비교하려는 성적표의 달과 입력한 달이 다르다면 넘겨라!
				continue;
			result = reportCard;
		}
		return result;
	}

	public List<ReportCard> getReportCards(Student student, Integer month) {
		return getReportCards(student, month, false);
	}

	public List<ReportCard> getReportCards(Student student, Integer month, boolean includeDeleted) {
		LinkedList<ReportCard> reportCards = new LinkedList<>(); // 찾아낼 성적표들을 담을 새로운 컬렉션
		for (ReportCard reportCard : this.reportCards) {
//			if (!includeDeleted && reportCard.isDeleted)
//				continue;
			if (student != null && reportCard.student != student)
				continue;
			if (month != null && reportCard.month != month)
				continue;
			reportCards.add(reportCard); // 조건에 맞는 성적표를 새로만든 컬렉션에 담아라.
		}
		return reportCards;
	}

	public ReportCard addFilter(Student student) { // 신규생 성적입력시 사용하는 메소드
		ReportCard reportCard = new ReportCard(student);
		System.out.print("국어: ");
		reportCard.korean = academy.getScoreInteger();
		System.out.print("수학: ");
		reportCard.math = academy.getScoreInteger();
		System.out.print("영어: ");
		reportCard.english = academy.getScoreInteger();
		System.out.print("탐구1: ");
		reportCard.add1 = academy.getScoreInteger();
		System.out.print("탐구2: ");
		reportCard.add2 = academy.getScoreInteger();
		return reportCard;
	}

	public void add(Student student, ReportCard reportCard) { // 수능 이후의 성적을 담을때 사용하는 메소드
		// 성적 갱신
		List<ReportCard> olds = getReportCards(student, null);
		if (!olds.isEmpty()) {
			ReportCard old = olds.get(olds.size() - 1);
			student.subjectBestReportCard(old);
		}
		// 성적 입력
		reportCards.add(reportCard);
		reportCard.student = student;

	}

	public void printReporcardByClass(ClassRoom classRoom) {// 반별 성적 출력 메소드
		
		int thisMonth = (now.get(Calendar.MONTH)) + 1;
		System.out.println("'" + classRoom.className +"'학생들의 "+ (thisMonth-1) +"월 -> "+thisMonth+"월 성적변화입니다.");
		List<ReportCard> preReportCards = new LinkedList<>();
		List<ReportCard> newReportCards = new LinkedList<>();
		
		for (int i = 0; i < classRoom.students.size(); i++) {
			Student student = classRoom.students.get(i);
			ReportCard reportCard = academy.reportCardService.getReportCard(student, thisMonth - 1);
			preReportCards.add(reportCard);
			reportCard = academy.reportCardService.getReportCard(student, thisMonth);
			newReportCards.add(reportCard);
		}
		
		PrintHelper.printClass("국어", preReportCards, newReportCards, new GetAvg() {
			@Override
			public int getAvg(ReportCard reportCard) {
				return reportCard.korean;
			}
		});
		
		PrintHelper.printClass("수학", preReportCards, newReportCards, (reportCard)->reportCard.math); // 위에(253-257)랑 같은표현입니다!
		PrintHelper.printClass("영어", preReportCards, newReportCards, (reportCard)->reportCard.english);
		PrintHelper.printClass("탐구1", preReportCards, newReportCards, (reportCard)->reportCard.add1);
		PrintHelper.printClass("탐구2", preReportCards, newReportCards, (reportCard)->reportCard.add2);

		double bestReportCard = Double.MAX_VALUE;
		double worstReportCard = Double.MIN_VALUE;
		String bestStudentName = null;
		String worstStudentName = null;
		for (int i = 0; i < classRoom.students.size(); i++) {
			Student student = classRoom.students.get(i);
			ReportCard reportCard = getReportCard(student, thisMonth);
			double studnetAvg = (double)(reportCard.korean + reportCard.math + reportCard.english +reportCard.add1 + reportCard.add2)/5;
			if(bestReportCard>studnetAvg) {
				bestReportCard = studnetAvg;
				bestStudentName = student.name;
			}
			if(worstReportCard<studnetAvg) {
				worstReportCard = studnetAvg;
				worstStudentName = student.name;
			}
		}
		System.out.println("최상위 성적의 평균: "+bestReportCard+"("+bestStudentName+")");
		System.out.println("최하위 성적의 평균: "+worstReportCard+"("+worstStudentName+")");
	}
	

		
	

}
