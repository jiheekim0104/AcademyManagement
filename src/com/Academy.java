package com;

import java.util.Calendar; // 날짜
import java.util.Scanner;

import com.service.*;

public class Academy {
	public static final int COMMAND_STUDENT = 1;
	public static final int COMMAND_REPORT_CARD = 2;
	public static final int COMMAND_ACADEMY_MANAGE = 3;
	public static final int COMMAND_BREAK = 0;
	public static final String ACADEMY_PRESIDENT_ID = "고재훈";
	public static final String ACADEMY_PRESIDENT_PASSWORD = "0000";

	public Scanner scanner;
	public Calendar cal; // 날짜
	public String name; // 학원이름
	public String tel; // 학원 전화번호

	public ClassRoomService classRoomService;
	public ReceiptService receiptService;
	public ReportCardService reportCardService;
	public StudentService studentService;
	public TeacherService teacherService;

	public Academy(String name, String tel) {
		this.scanner = new Scanner(System.in);
		this.name = name;
		this.tel = tel;

		classRoomService = new ClassRoomService(this);
		receiptService = new ReceiptService(this);
		reportCardService = new ReportCardService(this);
		studentService = new StudentService(this);
		teacherService = new TeacherService(this);
	}

	public int login() { // 로그인
		int i = 0;
		System.out.print("원장 ID: ");
		if (!getString().equals(ACADEMY_PRESIDENT_ID))
			return -1;

		System.out.print("PASSWORD: ");
		if (!getString().equals(ACADEMY_PRESIDENT_PASSWORD))
			return -2;

		return 1;
	}

	public void init() { // 초기에 있어야하는 내용들을 추가하는 메소드
		classRoomService.init();
		studentService.init();
		teacherService.init();
	}

	public void run() {
		cal = Calendar.getInstance();
		int command;
		System.out.print("오늘은 "+(cal.get(Calendar.MONTH)+1)+"월 "+cal.get(Calendar.DATE)+"일,");
		System.out.println(ACADEMY_PRESIDENT_ID + "원장님 환영합니다.");
		System.out.println("-----------------------------");
		System.out.println("  " + name + "  " + tel); // 학원 이름과 전화번호 변경 가능 
		System.out.println("-----------<모의고사>-----------");
		
		cal.set(2023, 3, 23); // 날짜 설정하여 디데이 계산
		long dDay = cal.getTimeInMillis(); // 1000분의 1초로 계산
		long now = System.currentTimeMillis();
		long result = dDay - now;
		System.out.printf("%-15s", "3월 D-day " + result / 1000 / 60 / 60 / 24 + "일");

		cal.set(2023, 6, 1);
		dDay = cal.getTimeInMillis();
		now = System.currentTimeMillis();
		result = dDay - now;
		System.out.println("6월 D-day " + result / 1000 / 60 / 60 / 24 + "일");

		cal.set(2023, 9, 6);
		dDay = cal.getTimeInMillis();
		now = System.currentTimeMillis();
		result = dDay - now;
		System.out.printf("%-15s", "9월 D-day " + result / 1000 / 60 / 60 / 24 + "일");

		cal.set(2023, 11, 21);
		dDay = cal.getTimeInMillis();
		now = System.currentTimeMillis();
		result = dDay - now;
		System.out.println("수능 D-day " + result / 1000 / 60 / 60 / 24 + "일");

		boolean isClosed = false;
		while (!isClosed) {

			System.out.println("---------<관리탭 선택>---------");
			System.out.println("1.학생 2.성적 3.학원경영 0.종료");
			System.out.println("-----------------------------");
			System.out.print("선택>");
			command = getInteger();
			switch (command) {
			case COMMAND_STUDENT: {
				studentService.service();
			}
				break;
			case COMMAND_REPORT_CARD: {
				reportCardService.service();
				break;
			}
			case COMMAND_ACADEMY_MANAGE: {
				receiptService.service();
				break;
			}
			case COMMAND_BREAK: {
				System.out.println("시스템을 종료합니다.");
				isClosed = true;
				break;
			}
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	public int getInteger() { // 숫자를 입력받는 메소드, 메뉴 선택시 주로 사용
		while (true) {
			try {
				return Integer.parseInt(getString());
			} catch (Exception e) {
				System.out.println("입력오류: 다시입력해주세요.");
			}
		}
	}

	public int getScoreInteger() { // 수능 성적 입력받는 메소드
		int score = 0;
		boolean isAble = true;
		while (true) {
			try {
				do {
					score = Integer.parseInt(getString());
					if (score >= 1 && score <= 9) {
						isAble = false;
						return score;
					} else {
						System.out.println("1~9 사이의 숫자만 입력가능합니다.");
					}
				} while (isAble);
			} catch (Exception e) {
				System.out.println("입력오류: 다시입력해주세요.");
			}
		}
	}

	public int getMonthInteger() { // 월 입력받는 메소드
		int month = 0;
		boolean isAble = true;
		while (true) {
			try {
				do {
					month = Integer.parseInt(getString());
					if (month >= 1 && month <= 12) {
						isAble = false;
						return month;
					} else {
						System.out.println("1~12 사이의 숫자만 입력가능합니다.");
					}
				} while (isAble);
			} catch (Exception e) {
				System.out.println("입력오류: 다시입력해주세요.");
			}
		}
	}

	public String getString() { // 문자 입력받는 메소드, 빈칸을 공백으로 처리함
		String input;
		while ((input = scanner.nextLine().trim()).equals(""))
			System.out.println("입력오류: 빈칸입니다. 다시 입력해주세요.");
		return input;
	}

	public String getAnswer() { // y 또는 n 대답을 입력받는 메소드
		String answer;
		boolean isAble = false;
		while (!isAble) {
			answer = scanner.nextLine();
			if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
				return answer;
			} else {
				System.out.println("잘못된입력입니다. Y / N 중에 선택해주십시오.");
			}
		}
		return null;
	}

	public void close() {
		this.scanner.close();
	}
}
