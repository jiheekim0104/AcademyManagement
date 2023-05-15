package com;

import java.util.Calendar; // ��¥
import java.util.Scanner;

import com.service.*;

public class Academy {
	public static final int COMMAND_STUDENT = 1;
	public static final int COMMAND_REPORT_CARD = 2;
	public static final int COMMAND_ACADEMY_MANAGE = 3;
	public static final int COMMAND_BREAK = 0;
	public static final String ACADEMY_PRESIDENT_ID = "������";
	public static final String ACADEMY_PRESIDENT_PASSWORD = "0000";

	public Scanner scanner;
	public Calendar cal; // ��¥
	public String name; // �п��̸�
	public String tel; // �п� ��ȭ��ȣ

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

	public int login() { // �α���
		int i = 0;
		System.out.print("���� ID: ");
		if (!getString().equals(ACADEMY_PRESIDENT_ID))
			return -1;

		System.out.print("PASSWORD: ");
		if (!getString().equals(ACADEMY_PRESIDENT_PASSWORD))
			return -2;

		return 1;
	}

	public void init() { // �ʱ⿡ �־���ϴ� ������� �߰��ϴ� �޼ҵ�
		classRoomService.init();
		studentService.init();
		teacherService.init();
	}

	public void run() {
		cal = Calendar.getInstance();
		int command;
		System.out.print("������ "+(cal.get(Calendar.MONTH)+1)+"�� "+cal.get(Calendar.DATE)+"��,");
		System.out.println(ACADEMY_PRESIDENT_ID + "����� ȯ���մϴ�.");
		System.out.println("-----------------------------");
		System.out.println("  " + name + "  " + tel); // �п� �̸��� ��ȭ��ȣ ���� ���� 
		System.out.println("-----------<���ǰ���>-----------");
		
		cal.set(2023, 3, 23); // ��¥ �����Ͽ� ���� ���
		long dDay = cal.getTimeInMillis(); // 1000���� 1�ʷ� ���
		long now = System.currentTimeMillis();
		long result = dDay - now;
		System.out.printf("%-15s", "3�� D-day " + result / 1000 / 60 / 60 / 24 + "��");

		cal.set(2023, 6, 1);
		dDay = cal.getTimeInMillis();
		now = System.currentTimeMillis();
		result = dDay - now;
		System.out.println("6�� D-day " + result / 1000 / 60 / 60 / 24 + "��");

		cal.set(2023, 9, 6);
		dDay = cal.getTimeInMillis();
		now = System.currentTimeMillis();
		result = dDay - now;
		System.out.printf("%-15s", "9�� D-day " + result / 1000 / 60 / 60 / 24 + "��");

		cal.set(2023, 11, 21);
		dDay = cal.getTimeInMillis();
		now = System.currentTimeMillis();
		result = dDay - now;
		System.out.println("���� D-day " + result / 1000 / 60 / 60 / 24 + "��");

		boolean isClosed = false;
		while (!isClosed) {

			System.out.println("---------<������ ����>---------");
			System.out.println("1.�л� 2.���� 3.�п��濵 0.����");
			System.out.println("-----------------------------");
			System.out.print("����>");
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
				System.out.println("�ý����� �����մϴ�.");
				isClosed = true;
				break;
			}
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
		}
	}

	public int getInteger() { // ���ڸ� �Է¹޴� �޼ҵ�, �޴� ���ý� �ַ� ���
		while (true) {
			try {
				return Integer.parseInt(getString());
			} catch (Exception e) {
				System.out.println("�Է¿���: �ٽ��Է����ּ���.");
			}
		}
	}

	public int getScoreInteger() { // ���� ���� �Է¹޴� �޼ҵ�
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
						System.out.println("1~9 ������ ���ڸ� �Է°����մϴ�.");
					}
				} while (isAble);
			} catch (Exception e) {
				System.out.println("�Է¿���: �ٽ��Է����ּ���.");
			}
		}
	}

	public int getMonthInteger() { // �� �Է¹޴� �޼ҵ�
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
						System.out.println("1~12 ������ ���ڸ� �Է°����մϴ�.");
					}
				} while (isAble);
			} catch (Exception e) {
				System.out.println("�Է¿���: �ٽ��Է����ּ���.");
			}
		}
	}

	public String getString() { // ���� �Է¹޴� �޼ҵ�, ��ĭ�� �������� ó����
		String input;
		while ((input = scanner.nextLine().trim()).equals(""))
			System.out.println("�Է¿���: ��ĭ�Դϴ�. �ٽ� �Է����ּ���.");
		return input;
	}

	public String getAnswer() { // y �Ǵ� n ����� �Է¹޴� �޼ҵ�
		String answer;
		boolean isAble = false;
		while (!isAble) {
			answer = scanner.nextLine();
			if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
				return answer;
			} else {
				System.out.println("�߸����Է��Դϴ�. Y / N �߿� �������ֽʽÿ�.");
			}
		}
		return null;
	}

	public void close() {
		this.scanner.close();
	}
}