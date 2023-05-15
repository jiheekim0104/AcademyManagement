package com.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.Academy;
import com.element.ClassRoom;
import com.element.ReportCard;
import com.element.Student;
import com.service.PrintHelper.GetScore;

public class StudentService {

	private static final int COMMAND_STUDENT_SEARCH = 1;
	private static final int COMMAND_ADD_STUDENT = 2;
	private static final int COMMAND_PRINT_ALL = 3;
	private static final int COMMAND_BACK = 0;

	private static final int COMMAND_ABSENCE_CHECK = 1;
	private static final int COMMAND_OUT_STUDENT = 2;
	private static final int COMMAND_STUDENT_GRAPH = 3;
	private static final int COMMAND_PAYMENT_CHECK = 4;
	
	
	LocalDate now = LocalDate.now();

	public Academy academy;
	List<Student> students;

	public StudentService(Academy academy) {
		this.academy = academy;
		this.students = new LinkedList<>();
	}

	public ReportCard reportCard;
	public ReportCardService reportCardService;
	public ReceiptService receiptService;
	
	public void init() {
		Student student;

		student = new Student("안유진", "010-0000-0001", "안가을", "010-0000-0011", "리즈고", false, 200, 1, false);
		reportCard = new ReportCard(student, 0, 1, 1, 1, 1, 1);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 1, 2, 2, 1, 1);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 1, 2, 1, 1);
		academy.reportCardService.add(student, reportCard);

		student = new Student("장원영", "010-0000-0002", "장이서", "010-0000-0012", "레이고", false, 200, 0, false);
		reportCard = new ReportCard(student, 0, 2, 1, 2, 1, 1);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 1, 1, 2, 1, 1);
		academy.reportCardService.add(student, reportCard);
//		reportCard = new ReportCard(student, 2, 1, 1, 1, 1, 1);
//		academy.reportCardService.add(student, reportCard);

		student = new Student("강해린", "010-0000-0003", "강고양", "010-0000-0131", "디토고", false, 200, 1, false);
		reportCard = new ReportCard(student, 0, 1, 1, 1, 1, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 1, 2, 2, 1, 1);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 2, 1, 1, 1);
		academy.reportCardService.add(student, reportCard);

		student = new Student("김민지", "010-4000-0001", "김천리", "010-4000-0011", "한림예고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 1, 1, 1, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 1, 1, 1, 1, 1);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 1, 1, 1, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("정슬기", "010-9874-3481", "정약용", "010-9874-8134", "해운대고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 1, 2, 1, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 2, 2, 1, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 2, 1, 2, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("이혜인", "010-4100-0101", "이문학", "010-4000-0011", "인천고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 1, 3, 1, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 1, 2, 1, 3);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 2, 1, 3, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("조이안", "010-7880-1251", "박아름", "010-7880-1211", "귀엽고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 1, 2, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 2, 1, 3, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 2, 2, 2, 2, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("서민주", "010-2178-1414", "서민영", "010-2178-4141", "성일여고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 2, 2, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 2, 2, 2, 1);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 2, 1, 2, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("최서준", "010-7213-5484", "서민영", "010-7213-5554", "미사고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 5, 2, 1, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 5, 1, 2, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 2, 3, 1, 5, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("문성원", "010-2008-1014", "문혜인", "010-2008-1004", "신장고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 2, 2, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 2, 2, 3, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 3, 1, 1, 1);
		academy.reportCardService.add(student, reportCard);

		student = new Student("김지수", "010-3358-9541", "김기영", "010-3358-9541", "부산여고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 2, 2, 3, 3);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 3, 3, 2, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 3, 2, 2, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("박아름", "010-4652-3144", "박우영", "010-4652-3322", "울산여고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 4, 2, 2, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 4, 3, 3, 1, 1);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 1, 4, 2, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("김강민", "010-1181-8818", "김태우", "010-1181-8828", "하남고", true, 200, 2, false);
		reportCard = new ReportCard(student, 0, 3, 3, 3, 3, 3);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 3, 3, 3, 3, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 2, 3, 3, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("문보민", "010-9512-9555", "문경화", "010-9512-9411", "미강고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 5, 2, 3, 3);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 1, 3, 3, 4);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 2, 3, 3, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("권보영", "010-2228-1111", "권지영", "010-2228-2222", "상산고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 3, 5, 2, 3, 3);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 3, 4, 3, 4, 4);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 2, 5, 4, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("김동완", "010-5578-1554", "김민교", "010-5578-1664", "미강고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 4, 4, 4, 4, 4);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 4, 3, 3, 5, 4);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 2, 5, 5, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("김재현", "010-2222-1111", "김재영", "010-3333-1111", "하남고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 4, 4, 3, 4, 4);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 4, 3, 3, 5, 4);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 5, 2, 4, 4, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("박성재", "010-1156-7845", "박영우", "010-7845-1156", "미사고", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 5, 3, 1, 5, 5);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 4, 5, 3, 5, 4);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 5, 1, 4, 5, 2);
		academy.reportCardService.add(student, reportCard);
	}

	private void initStudent(ReportCard reportCard, Student student) { // 초기에 학생들 추가하는 메소드
		ClassRoom classRoom = academy.classRoomService.classify(reportCard);
		if (classRoom != null && !academy.classRoomService.isFull(classRoom)) {
			student.classRoom = classRoom;
			add(student); // 학생을 추가함
			academy.classRoomService.enter(student, classRoom); // 해당학생을 해당반에 추가함
			academy.reportCardService.add(student, reportCard); // 해당학생의 성적표를 추가함
		}
	}

	private void add(Student student) {
		students.add(student);

	}

	public void service() {
		int command;
		ClassRoom classRoom;
		Student student;
		boolean isOutterClosed = false;
		while (!isOutterClosed) {
			System.out.println("메인>>학생---------------------");
			System.out.println("1.학생검색 2.신규생등록 3.전체보기 0.이전메뉴로");
			System.out.println("-----------------------------");
			System.out.print("선택>");
			command = academy.getInteger();
			switch (command) {
			case COMMAND_STUDENT_SEARCH:
				System.out.print("이름:");
				String name = academy.getString();
				student = getStudent(name);
				if (getStudent(name) == (null)) {
					System.out.println("검색하신 학생은 없는 이름입니다.");
					break;
				}
				PrintHelper.searchPrint(student);
				serviceStudent(student); // 선택된학생으로 추가 실행
				break;

			case COMMAND_ADD_STUDENT:
				System.out.println("신규생의 수능 성적을 입력하세요.");
				reportCard = academy.reportCardService.addFilter(null); // 성적을 입력받음
				classRoom = academy.classRoomService.classify(reportCard); // 입력받은 성적에 맞추어 반을 나눔
				if (classRoom == null) {
					System.out.println("해당학생은 성적 미달로 입학할수 없습니다.");
				} else if (academy.classRoomService.isFull(classRoom)) {
					System.out.println(classRoom.className + "은 인원초과로 등록할수없습니다.");
				} else {
					System.out.println(classRoom.className + "에 해당학생 등록을 시작합니다.");
					student = new Student();
					System.out.print("이름:");
					student.name = academy.getString();
					System.out.print("학생전화: ");
					student.phone = academy.getString();
					System.out.print("졸업학교: ");
					student.school = academy.getString();
					System.out.print("학부모명: ");
					student.parentName = academy.getString();
					System.out.print("학부모전화: ");
					student.parentPhone = academy.getString();
					student.payment = true;
					student.membershipFee = 200;
					student.absence = 0;
					student.classRoom = classRoom;
					add(student); // 학생을 추가함
					academy.classRoomService.enter(student, classRoom); // 해당학생을 해당반에 추가함
					academy.reportCardService.add(student, reportCard); // 해당학생의 성적표를 추가함
					System.out.println("'" + student.name + "'학생이 '" + classRoom.className + "'에 정상적으로 추가되었습니다.");
				}

				break;

			case COMMAND_PRINT_ALL: 
				System.out.printf("%-22s %-6s %-6s %-6s %-6s %-5s \n", "반이름", "학생1", "학생2", "학생3", "학생4", "학생5");
				for (int i = 0; i < academy.classRoomService.classRooms.size(); i++) {
					System.out.printf("%-15s", academy.classRoomService.classRooms.get(i).className);
					for (Student classStudentMember : students) {
						if (classStudentMember.classRoom.className
								.equals(academy.classRoomService.classRooms.get(i).className))
							System.out.print("\t" + classStudentMember.name);
					}
					System.out.println();
				}

				break;
			case COMMAND_BACK:
				isOutterClosed = true;
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	private void serviceStudent(Student student) {
		int absenceDay = -1;
		int paymentMonth = -1;
		int command;
		boolean isInnerClosed = false;
		inner: while (!isInnerClosed) {
			System.out.println("*****************************");
			System.out.println("1.결석처리 2.퇴원처리 3.성적확인 4.회비납부 0.이전메뉴로");
			System.out.println("*****************************");
			System.out.print("선택>");
			command = academy.getInteger();
			switch (command) {
			case COMMAND_ABSENCE_CHECK: 
				if (absenceDay < now.getDayOfMonth()) {
					System.out.println(now.getMonthValue() + "월 " + now.getDayOfMonth() + "일까지 '" + student.name
							+ "'학생의 결석일수는 " + student.absence + "일 입니다.");
					System.out.println("오늘 결석처리 하시겠습니까?");
					if (academy.getAnswer().equalsIgnoreCase("y")) {
						absenceDay = now.getDayOfMonth();
						student.absence++;
						System.out.println(now.getMonthValue() + "월 " + now.getDayOfMonth() + "일 정상 결석처리 되었습니다.");
					}
				} else {
					System.out.println("이미 오늘(" + now.getMonthValue() + "월 " + now.getDayOfMonth() + "일)결석처리 되었습니다.");

				}

				if (student.absence == 3) {
					System.out.println(student.name + "학생 결석일수 3일이 되어 자동 퇴원처리됩니다.");
					removeStudent(student);
					break inner;
				}

				break;

			case COMMAND_OUT_STUDENT:
				removeStudent(student);
				break inner;

			case COMMAND_STUDENT_GRAPH:
				PrintHelper.printGraph("국어", academy.reportCardService.getReportCards(student, null), new GetScore() {
					@Override
					public int getScore(ReportCard reportCard) {
						return reportCard.korean;
					}
				});
				PrintHelper.printGraph("수학", academy.reportCardService.getReportCards(student, null), (reportCard) -> reportCard.math);
				PrintHelper.printGraph("영어", academy.reportCardService.getReportCards(student, null), (reportCard) -> reportCard.english);
				PrintHelper.printGraph("탐구1", academy.reportCardService.getReportCards(student, null), (reportCard) -> reportCard.add1);
				PrintHelper.printGraph("탐구2", academy.reportCardService.getReportCards(student, null), (reportCard) -> reportCard.add2);

				break;

			case COMMAND_PAYMENT_CHECK:
				if (student.payment) {
					System.out.println("이미 이번달(" + now.getMonthValue() + "월)납부처리 되었습니다.");
				} else if (paymentMonth < now.getMonthValue()) {
					System.out.println("'" + student.name + "'학생" + now.getMonthValue() + "월 회비는 다음과 같습니다.");
					reportCard = academy.reportCardService.getReportCard(student, null);
					System.out.print("장학금은 ");
					academy.receiptService.scholarshipCalculationPrint(student, reportCard);
					int scholarship = (academy.receiptService.scholarshipCalculation(student, reportCard)) * 10;
					System.out.println("으로 " + scholarship + "만원");

					System.out.print("보충 수강료는 ");
					academy.receiptService.additionalClassCalculationPrint(student, reportCard);
					int additionalClass = (academy.receiptService.additionalClassCalculation(student, reportCard)) * 10;
					System.out.println("으로 " + additionalClass + "만원");
					System.out.println("총 " + (200 - scholarship + additionalClass) + "만원 입니다.");
					System.out.println(now.getMonthValue() + "월 " + now.getDayOfMonth() + "일 납부 확인 처리하시겠습니까?");
					if (academy.getAnswer().equalsIgnoreCase("y")) {
						paymentMonth = now.getMonthValue();
						System.out.println(now.getMonthValue() + "월 " + now.getDayOfMonth() + "일 정상 납부처리 되었습니다.");
						student.payment = true;
					}
				}

				break;
			case COMMAND_BACK:
				isInnerClosed = true;
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");

			}
		}
	}

	private void removeStudent(Student student) {
		System.out.println(student.name + "학생 퇴원처리 하시겠습니까?");
		if (academy.getAnswer().equalsIgnoreCase("y")) {
			students.remove(student);
			academy.classRoomService.remove(student, student.classRoom); // 해당학생을 해당반에서 삭제
			student.isDeleted = true;
			System.out.println("정상 퇴원처리 되었습니다.");

		}
	}

	public Student getStudent(String name) { // 이름으로 해당하는 학생을 찾는 메소드
		for (Student student : students) {
			if (student.isDeleted) // 지워진 학생이라면
				continue; // 무시하고 다음 학생으로 넘어감
			if (student.name.equals(name)) // 이름이 같다면
				return student; // 해당학생을 리턴함
		}
		return null;
	}
	
	
}
