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

		student = new Student("������", "010-0000-0001", "�Ȱ���", "010-0000-0011", "�����", false, 200, 1, false);
		reportCard = new ReportCard(student, 0, 1, 1, 1, 1, 1);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 1, 2, 2, 1, 1);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 1, 2, 1, 1);
		academy.reportCardService.add(student, reportCard);

		student = new Student("�����", "010-0000-0002", "���̼�", "010-0000-0012", "���̰�", false, 200, 0, false);
		reportCard = new ReportCard(student, 0, 2, 1, 2, 1, 1);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 1, 1, 2, 1, 1);
		academy.reportCardService.add(student, reportCard);
//		reportCard = new ReportCard(student, 2, 1, 1, 1, 1, 1);
//		academy.reportCardService.add(student, reportCard);

		student = new Student("���ظ�", "010-0000-0003", "�����", "010-0000-0131", "�����", false, 200, 1, false);
		reportCard = new ReportCard(student, 0, 1, 1, 1, 1, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 1, 2, 2, 1, 1);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 2, 1, 1, 1);
		academy.reportCardService.add(student, reportCard);

		student = new Student("�����", "010-4000-0001", "��õ��", "010-4000-0011", "�Ѹ�����", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 1, 1, 1, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 1, 1, 1, 1, 1);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 1, 1, 1, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("������", "010-9874-3481", "�����", "010-9874-8134", "�ؿ���", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 1, 2, 1, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 2, 2, 1, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 2, 1, 2, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("������", "010-4100-0101", "�̹���", "010-4000-0011", "��õ��", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 1, 3, 1, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 1, 2, 1, 3);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 2, 1, 3, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("���̾�", "010-7880-1251", "�ھƸ�", "010-7880-1211", "�Ϳ���", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 1, 2, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 2, 1, 3, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 2, 2, 2, 2, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("������", "010-2178-1414", "���ο�", "010-2178-4141", "���Ͽ���", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 2, 2, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 2, 2, 2, 1);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 1, 2, 1, 2, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("�ּ���", "010-7213-5484", "���ο�", "010-7213-5554", "�̻��", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 5, 2, 1, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 5, 1, 2, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 2, 3, 1, 5, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("������", "010-2008-1014", "������", "010-2008-1004", "�����", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 2, 2, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 2, 2, 3, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 3, 1, 1, 1);
		academy.reportCardService.add(student, reportCard);

		student = new Student("������", "010-3358-9541", "��⿵", "010-3358-9541", "�λ꿩��", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 2, 2, 3, 3);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 3, 3, 2, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 3, 2, 2, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("�ھƸ�", "010-4652-3144", "�ڿ쿵", "010-4652-3322", "��꿩��", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 4, 2, 2, 2, 2);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 4, 3, 3, 1, 1);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 1, 4, 2, 2);
		academy.reportCardService.add(student, reportCard);

		student = new Student("�谭��", "010-1181-8818", "���¿�", "010-1181-8828", "�ϳ���", true, 200, 2, false);
		reportCard = new ReportCard(student, 0, 3, 3, 3, 3, 3);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 3, 3, 3, 3, 2);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 2, 3, 3, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("������", "010-9512-9555", "����ȭ", "010-9512-9411", "�̰���", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 2, 5, 2, 3, 3);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 2, 1, 3, 3, 4);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 2, 3, 3, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("�Ǻ���", "010-2228-1111", "������", "010-2228-2222", "����", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 3, 5, 2, 3, 3);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 3, 4, 3, 4, 4);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 2, 5, 4, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("�赿��", "010-5578-1554", "��α�", "010-5578-1664", "�̰���", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 4, 4, 4, 4, 4);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 4, 3, 3, 5, 4);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 3, 2, 5, 5, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("������", "010-2222-1111", "���翵", "010-3333-1111", "�ϳ���", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 4, 4, 3, 4, 4);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 4, 3, 3, 5, 4);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 5, 2, 4, 4, 3);
		academy.reportCardService.add(student, reportCard);

		student = new Student("�ڼ���", "010-1156-7845", "�ڿ���", "010-7845-1156", "�̻��", true, 200, 1, false);
		reportCard = new ReportCard(student, 0, 5, 3, 1, 5, 5);
		initStudent(reportCard, student);
		reportCard = new ReportCard(student, 1, 4, 5, 3, 5, 4);
		academy.reportCardService.add(student, reportCard);
		reportCard = new ReportCard(student, 2, 5, 1, 4, 5, 2);
		academy.reportCardService.add(student, reportCard);
	}

	private void initStudent(ReportCard reportCard, Student student) { // �ʱ⿡ �л��� �߰��ϴ� �޼ҵ�
		ClassRoom classRoom = academy.classRoomService.classify(reportCard);
		if (classRoom != null && !academy.classRoomService.isFull(classRoom)) {
			student.classRoom = classRoom;
			add(student); // �л��� �߰���
			academy.classRoomService.enter(student, classRoom); // �ش��л��� �ش�ݿ� �߰���
			academy.reportCardService.add(student, reportCard); // �ش��л��� ����ǥ�� �߰���
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
			System.out.println("����>>�л�---------------------");
			System.out.println("1.�л��˻� 2.�űԻ���� 3.��ü���� 0.�����޴���");
			System.out.println("-----------------------------");
			System.out.print("����>");
			command = academy.getInteger();
			switch (command) {
			case COMMAND_STUDENT_SEARCH:
				System.out.print("�̸�:");
				String name = academy.getString();
				student = getStudent(name);
				if (getStudent(name) == (null)) {
					System.out.println("�˻��Ͻ� �л��� ���� �̸��Դϴ�.");
					break;
				}
				PrintHelper.searchPrint(student);
				serviceStudent(student); // ���õ��л����� �߰� ����
				break;

			case COMMAND_ADD_STUDENT:
				System.out.println("�űԻ��� ���� ������ �Է��ϼ���.");
				reportCard = academy.reportCardService.addFilter(null); // ������ �Է¹���
				classRoom = academy.classRoomService.classify(reportCard); // �Է¹��� ������ ���߾� ���� ����
				if (classRoom == null) {
					System.out.println("�ش��л��� ���� �̴޷� �����Ҽ� �����ϴ�.");
				} else if (academy.classRoomService.isFull(classRoom)) {
					System.out.println(classRoom.className + "�� �ο��ʰ��� ����Ҽ������ϴ�.");
				} else {
					System.out.println(classRoom.className + "�� �ش��л� ����� �����մϴ�.");
					student = new Student();
					System.out.print("�̸�:");
					student.name = academy.getString();
					System.out.print("�л���ȭ: ");
					student.phone = academy.getString();
					System.out.print("�����б�: ");
					student.school = academy.getString();
					System.out.print("�кθ��: ");
					student.parentName = academy.getString();
					System.out.print("�кθ���ȭ: ");
					student.parentPhone = academy.getString();
					student.payment = true;
					student.membershipFee = 200;
					student.absence = 0;
					student.classRoom = classRoom;
					add(student); // �л��� �߰���
					academy.classRoomService.enter(student, classRoom); // �ش��л��� �ش�ݿ� �߰���
					academy.reportCardService.add(student, reportCard); // �ش��л��� ����ǥ�� �߰���
					System.out.println("'" + student.name + "'�л��� '" + classRoom.className + "'�� ���������� �߰��Ǿ����ϴ�.");
				}

				break;

			case COMMAND_PRINT_ALL: 
				System.out.printf("%-22s %-6s %-6s %-6s %-6s %-5s \n", "���̸�", "�л�1", "�л�2", "�л�3", "�л�4", "�л�5");
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
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
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
			System.out.println("1.�Ἦó�� 2.���ó�� 3.����Ȯ�� 4.ȸ�񳳺� 0.�����޴���");
			System.out.println("*****************************");
			System.out.print("����>");
			command = academy.getInteger();
			switch (command) {
			case COMMAND_ABSENCE_CHECK: 
				if (absenceDay < now.getDayOfMonth()) {
					System.out.println(now.getMonthValue() + "�� " + now.getDayOfMonth() + "�ϱ��� '" + student.name
							+ "'�л��� �Ἦ�ϼ��� " + student.absence + "�� �Դϴ�.");
					System.out.println("���� �Ἦó�� �Ͻðڽ��ϱ�?");
					if (academy.getAnswer().equalsIgnoreCase("y")) {
						absenceDay = now.getDayOfMonth();
						student.absence++;
						System.out.println(now.getMonthValue() + "�� " + now.getDayOfMonth() + "�� ���� �Ἦó�� �Ǿ����ϴ�.");
					}
				} else {
					System.out.println("�̹� ����(" + now.getMonthValue() + "�� " + now.getDayOfMonth() + "��)�Ἦó�� �Ǿ����ϴ�.");

				}

				if (student.absence == 3) {
					System.out.println(student.name + "�л� �Ἦ�ϼ� 3���� �Ǿ� �ڵ� ���ó���˴ϴ�.");
					removeStudent(student);
					break inner;
				}

				break;

			case COMMAND_OUT_STUDENT:
				removeStudent(student);
				break inner;

			case COMMAND_STUDENT_GRAPH:
				PrintHelper.printGraph("����", academy.reportCardService.getReportCards(student, null), new GetScore() {
					@Override
					public int getScore(ReportCard reportCard) {
						return reportCard.korean;
					}
				});
				PrintHelper.printGraph("����", academy.reportCardService.getReportCards(student, null), (reportCard) -> reportCard.math);
				PrintHelper.printGraph("����", academy.reportCardService.getReportCards(student, null), (reportCard) -> reportCard.english);
				PrintHelper.printGraph("Ž��1", academy.reportCardService.getReportCards(student, null), (reportCard) -> reportCard.add1);
				PrintHelper.printGraph("Ž��2", academy.reportCardService.getReportCards(student, null), (reportCard) -> reportCard.add2);

				break;

			case COMMAND_PAYMENT_CHECK:
				if (student.payment) {
					System.out.println("�̹� �̹���(" + now.getMonthValue() + "��)����ó�� �Ǿ����ϴ�.");
				} else if (paymentMonth < now.getMonthValue()) {
					System.out.println("'" + student.name + "'�л�" + now.getMonthValue() + "�� ȸ��� ������ �����ϴ�.");
					reportCard = academy.reportCardService.getReportCard(student, null);
					System.out.print("���б��� ");
					academy.receiptService.scholarshipCalculationPrint(student, reportCard);
					int scholarship = (academy.receiptService.scholarshipCalculation(student, reportCard)) * 10;
					System.out.println("���� " + scholarship + "����");

					System.out.print("���� ������� ");
					academy.receiptService.additionalClassCalculationPrint(student, reportCard);
					int additionalClass = (academy.receiptService.additionalClassCalculation(student, reportCard)) * 10;
					System.out.println("���� " + additionalClass + "����");
					System.out.println("�� " + (200 - scholarship + additionalClass) + "���� �Դϴ�.");
					System.out.println(now.getMonthValue() + "�� " + now.getDayOfMonth() + "�� ���� Ȯ�� ó���Ͻðڽ��ϱ�?");
					if (academy.getAnswer().equalsIgnoreCase("y")) {
						paymentMonth = now.getMonthValue();
						System.out.println(now.getMonthValue() + "�� " + now.getDayOfMonth() + "�� ���� ����ó�� �Ǿ����ϴ�.");
						student.payment = true;
					}
				}

				break;
			case COMMAND_BACK:
				isInnerClosed = true;
				break;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");

			}
		}
	}

	private void removeStudent(Student student) {
		System.out.println(student.name + "�л� ���ó�� �Ͻðڽ��ϱ�?");
		if (academy.getAnswer().equalsIgnoreCase("y")) {
			students.remove(student);
			academy.classRoomService.remove(student, student.classRoom); // �ش��л��� �ش�ݿ��� ����
			student.isDeleted = true;
			System.out.println("���� ���ó�� �Ǿ����ϴ�.");

		}
	}

	public Student getStudent(String name) { // �̸����� �ش��ϴ� �л��� ã�� �޼ҵ�
		for (Student student : students) {
			if (student.isDeleted) // ������ �л��̶��
				continue; // �����ϰ� ���� �л����� �Ѿ
			if (student.name.equals(name)) // �̸��� ���ٸ�
				return student; // �ش��л��� ������
		}
		return null;
	}
	
	
}
