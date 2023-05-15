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
			System.out.println("����>>����----------------------");
			System.out.println("1.�����Է� 2.�л���+�� 3.�л��� 4.����");
			System.out.println("5.�ݺ� 6.�������� 0.�����޴���");
			System.out.println("-----------------------------");
			System.out.print("����>");
			command = academy.getInteger();
			switch (command) {
			case COMMAND_REPORT_CARD_WRITE:
				System.out.print("�л���: ");
				student = academy.studentService.getStudent(academy.getString());
				if (student == null)
					System.out.println("�˻��Ͻ� �̸��� ���� �̸��Դϴ�.");
				else {
					reportCard = new ReportCard(student);
					System.out.print("��: ");
					reportCard.month = academy.getMonthInteger();
					System.out.print("����: ");
					reportCard.korean = academy.getScoreInteger();
					System.out.print("����: ");
					reportCard.math = academy.getScoreInteger();
					System.out.print("����: ");
					reportCard.english = academy.getScoreInteger();
					System.out.print("Ž��1: ");
					reportCard.add1 = academy.getScoreInteger();
					System.out.print("Ž��2: ");
					reportCard.add2 = academy.getScoreInteger();
					this.reportCards.add(reportCard);
					System.out.println("'"+student.name+"'�л��� "+reportCard.month+"�� ������ �߰��Ǿ����ϴ�.");
				}

				break;

			case COMMAND_REPORT_CARD_READ_ONE_BY_STUDENT_AND_MONTH:
				System.out.print("�л���: ");
				student = academy.studentService.getStudent(academy.getString());
				if (student == null)
					System.out.println("�˻��Ͻ� �̸��� ���� �̸��Դϴ�.");
				else {
					System.out.print("��: ");
					month = academy.getMonthInteger();
					reportCard = getReportCard(student, month);
					if (reportCard == null) {
						System.out.println("�Է��Ͻ� �л��� �ش� �� ������ �Էµ����ʾҽ��ϴ�.");
					} else {
						PrintHelper.print(reportCard);
					}
				}

				break;

			case COMMAND_REPORT_CARD_READ_BY_STUDENT:
				System.out.print("�л���: ");
				student = academy.studentService.getStudent(academy.getString());
				if (student == null)
					System.out.println("�˻��Ͻ� �̸��� ���� �̸��Դϴ�.");
				else {
					reportCards = getReportCards(student, null);
					PrintHelper.print(reportCards);
				}

				break;
			case COMMAND_REPORT_CARD_READ_BY_MONTH:
				System.out.print("��: ");
				month = academy.getMonthInteger();
				reportCards = getReportCards(null, month);
				if (reportCards.size() == 0) {
					System.out.println("�Է��Ͻ� �ش� �� ������ �Էµ����ʾҽ��ϴ�.");
				} else {
					PrintHelper.print(reportCards);
				}
				break;

			case COMMAND_REPORT_CARD_READ_BY_CLASSNAME:
				serviceReportCard();
				break;

			case COMMAND_REPORT_CARD_EDIT:
				System.out.print("�л���: ");
				student = academy.studentService.getStudent(academy.getString());
				if (student == null)
					System.out.println("�˻��Ͻ� �̸��� ���� �̸��Դϴ�.");
				else {
					System.out.print("��: ");
					month = academy.getMonthInteger();
					reportCard = getReportCard(student, month);
					if (reportCard == null)
						System.out.println("�Է��Ͻ� �л��� �ش� �� ������ �Էµ����ʾҽ��ϴ�.");
					else {
						System.out.print("����: ");
						reportCard.korean = academy.getScoreInteger();
						System.out.print("����: ");
						reportCard.math = academy.getScoreInteger();
						System.out.print("����: ");
						reportCard.english = academy.getScoreInteger();
						System.out.print("Ž��1: ");
						reportCard.add1 = academy.getScoreInteger();
						System.out.print("Ž��2: ");
						reportCard.add2 = academy.getScoreInteger();
						System.out.println("���������� �����Ǿ����ϴ�. ");
					}
				}
				break;
			case COMMAND_BREAK:
				isClosed = true;
				break;

			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
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
			System.out.println(" 0.�����޴���");
			System.out.println("*****************************");
			System.out.print("����>");
			index = academy.getInteger();
			if(index==0) {
				isClosed = true;
				break;
			} else if(0<index&&index<=classRooms.size()) {
				printReporcardByClass(academy.classRoomService.classRooms.get(index-1));
				
			} else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
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
			if (student != null && reportCard.student != student) // �Է��� �л��� �ְ�, ���Ϸ��� ����ǥ�� �л��� �Է��� �л��� �ٸ��ٸ� �Ѱܶ�!
				continue;
			if (month != null && reportCard.month != month) // �Է��� ���� �ְ�, ���Ϸ��� ����ǥ�� �ް� �Է��� ���� �ٸ��ٸ� �Ѱܶ�!
				continue;
			result = reportCard;
		}
		return result;
	}

	public List<ReportCard> getReportCards(Student student, Integer month) {
		return getReportCards(student, month, false);
	}

	public List<ReportCard> getReportCards(Student student, Integer month, boolean includeDeleted) {
		LinkedList<ReportCard> reportCards = new LinkedList<>(); // ã�Ƴ� ����ǥ���� ���� ���ο� �÷���
		for (ReportCard reportCard : this.reportCards) {
//			if (!includeDeleted && reportCard.isDeleted)
//				continue;
			if (student != null && reportCard.student != student)
				continue;
			if (month != null && reportCard.month != month)
				continue;
			reportCards.add(reportCard); // ���ǿ� �´� ����ǥ�� ���θ��� �÷��ǿ� ��ƶ�.
		}
		return reportCards;
	}

	public ReportCard addFilter(Student student) { // �űԻ� �����Է½� ����ϴ� �޼ҵ�
		ReportCard reportCard = new ReportCard(student);
		System.out.print("����: ");
		reportCard.korean = academy.getScoreInteger();
		System.out.print("����: ");
		reportCard.math = academy.getScoreInteger();
		System.out.print("����: ");
		reportCard.english = academy.getScoreInteger();
		System.out.print("Ž��1: ");
		reportCard.add1 = academy.getScoreInteger();
		System.out.print("Ž��2: ");
		reportCard.add2 = academy.getScoreInteger();
		return reportCard;
	}

	public void add(Student student, ReportCard reportCard) { // ���� ������ ������ ������ ����ϴ� �޼ҵ�
		// ���� ����
		List<ReportCard> olds = getReportCards(student, null);
		if (!olds.isEmpty()) {
			ReportCard old = olds.get(olds.size() - 1);
			student.subjectBestReportCard(old);
		}
		// ���� �Է�
		reportCards.add(reportCard);
		reportCard.student = student;

	}

	public void printReporcardByClass(ClassRoom classRoom) {// �ݺ� ���� ��� �޼ҵ�
		
		int thisMonth = (now.get(Calendar.MONTH)) + 1;
		System.out.println("'" + classRoom.className +"'�л����� "+ (thisMonth-1) +"�� -> "+thisMonth+"�� ������ȭ�Դϴ�.");
		List<ReportCard> preReportCards = new LinkedList<>();
		List<ReportCard> newReportCards = new LinkedList<>();
		
		for (int i = 0; i < classRoom.students.size(); i++) {
			Student student = classRoom.students.get(i);
			ReportCard reportCard = academy.reportCardService.getReportCard(student, thisMonth - 1);
			preReportCards.add(reportCard);
			reportCard = academy.reportCardService.getReportCard(student, thisMonth);
			newReportCards.add(reportCard);
		}
		
		PrintHelper.printClass("����", preReportCards, newReportCards, new GetAvg() {
			@Override
			public int getAvg(ReportCard reportCard) {
				return reportCard.korean;
			}
		});
		
		PrintHelper.printClass("����", preReportCards, newReportCards, (reportCard)->reportCard.math); // ����(253-257)�� ����ǥ���Դϴ�!
		PrintHelper.printClass("����", preReportCards, newReportCards, (reportCard)->reportCard.english);
		PrintHelper.printClass("Ž��1", preReportCards, newReportCards, (reportCard)->reportCard.add1);
		PrintHelper.printClass("Ž��2", preReportCards, newReportCards, (reportCard)->reportCard.add2);

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
		System.out.println("�ֻ��� ������ ���: "+bestReportCard+"("+bestStudentName+")");
		System.out.println("������ ������ ���: "+worstReportCard+"("+worstStudentName+")");
	}
	

		
	

}
