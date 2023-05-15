package com.service;

import java.util.Calendar;
//import java.time.LocalDate;
import com.Academy;
//import com.element.Receipt;
import com.element.ReportCard;
import com.element.Student;

public class ReceiptService {

	private static final int COMMAND_SCHOLARSHIP = 1;
	private static final int COMMAND_ADDITIONAL_CLASS_FEE = 2;
	private static final int COMMAND_TEACHER_PAY_MANAGE = 3;
	private static final int COMMAND_MONTH_INCOME = 4;
	private static final int COMMAND_NON_PAIEMENT = 5;
	private static final int COMMAND_BREAK = 0;

	Calendar now = Calendar.getInstance();
	public Academy academy;

	public ReceiptService(Academy academy) {
		this.academy = academy;
	}

	public void service() {
		int command;
		boolean isClosed = false;
		while (!isClosed) {
			System.out.println("����>>�п����-------------------");
			System.out.println("1.���б� 2.��������� 3.����޿����� 4.������ 5.�̳�ȸ�� 0.�����޴���");
			System.out.println("-----------------------------");
			System.out.print("����>");
			command = academy.getInteger();
			switch (command) {
			case COMMAND_SCHOLARSHIP:
				System.out.println((now.get(Calendar.MONTH) + 1) + "�� ���л� ��� �Դϴ�.");
				int index = 1;
				int sum = 0;
				
				System.out.format("%20s\t%14s\t %s\t %10s\n", "�� �̸�","| �л���","| ���б� |","���޻���");
				System.out.println("---------------------------------------------------------------------------------------------------");
				for (Student student : academy.studentService.students) {
					ReportCard reportCard = academy.reportCardService.getReportCard(student, null); // �ֽż����� �޾ƿ�
					if (scholarshipCalculation(student, reportCard) > 0) { // ���б��� �������ִ��� ī����
						System.out.format("%-5s %-17s\t %s \t %s\t", "(" + (index++) + ")","|"+student.classRoom.className,
								"|"+student.name, "|"+scholarshipCalculation(student, reportCard) * 10 + "����  |");
				
						scholarshipCalculationPrint(student, reportCard); // ���б� ������ �����
						System.out.println();
						sum += scholarshipCalculation(student, reportCard) * 10;
					}
				}
				System.out.println("�� " + sum + "���� �Դϴ�.");

				break;

			case COMMAND_ADDITIONAL_CLASS_FEE:
				System.out.println((now.get(Calendar.MONTH) + 1) + "�� ��������� ��� �Դϴ�.");
				index = 1;
				sum = 0;
				System.out.format("%20s\t%14s\t %s\t %10s\n", "�� �̸�","| �л���","|���������|","��������");
				System.out.println("---------------------------------------------------------------------------------------------------");
				
				for (Student student : academy.studentService.students) {
					ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
					if (additionalClassCalculation(student, reportCard) > 0) {
						System.out.printf("%-5s %-17s\t %s \t %s\t", "(" + (index++) + ")", "|"+student.classRoom.className,
								"|"+student.name, "|"+additionalClassCalculation(student, reportCard) * 10 + "����   |");
						additionalClassCalculationPrint(student, reportCard);
						System.out.println();
						sum += additionalClassCalculation(student, reportCard) * 10;
					}
				}
				System.out.println("�� " + sum + "���� �Դϴ�.");
				break;

			case COMMAND_TEACHER_PAY_MANAGE:
				System.out.println((now.get(Calendar.MONTH) + 1) + "�� ����޿� �Դϴ�.");
				System.out.printf("%10s \t %-5s %-5s \t %-5s \n", "�� �̸�","| ����","| ������","| �̴ޱ޿�");
				System.out.println("--------------------------------------------");
				
				for (int i = 1; i < academy.classRoomService.classRooms.size(); i++) {
					sum = 0;
					System.out.printf("%-10s \t %-5s", academy.classRoomService.classRooms.get(i).className,
							"|"+academy.teacherService.teachers.get(i - 1).name);

					for (Student student : academy.classRoomService.classRooms.get(i).students) { // �ݿ� �ִ� �л����� �ֱټ���ǥ����
						ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
						if (scholarshipCalculation(student, reportCard) > 0) { // ���б� �������ִ��� ī����
							sum += scholarshipCalculation(student, reportCard) * 3;
						}
					}
					System.out.printf(" %-5s \t %-5s \n", "|"+sum+"����",
							"|"+(academy.teacherService.teachers.get(i - 1).basicPay + sum + "����")); // ������ 0�� �ε������� �����̶� -1

				}
				break;

			case COMMAND_MONTH_INCOME:
				int monthIncome = 0;
				for (Student student : academy.studentService.students) {
					if (student.payment) { // payment�� true�� �л��̶��
						ReportCard reportCard = academy.reportCardService.getReportCard(student, null); // �ֱټ���ǥ�� ������
						int scholarship = (academy.receiptService.scholarshipCalculation(student, reportCard)) * 10; // ���бݰ�
						int additionalClass = (academy.receiptService.additionalClassCalculation(student, reportCard))
								* 10; // ��������� �����
						monthIncome += 200 - scholarship + additionalClass;
					}
				}
				System.out.println((now.get(Calendar.MONTH) + 1) + "�� �� ���ε� ������� " + monthIncome + "���� �Դϴ�.");

				int monthscholarship = 0;
				for (Student student : academy.studentService.students) {
					ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
					if (scholarshipCalculation(student, reportCard) > 0) {
						monthscholarship += scholarshipCalculation(student, reportCard) * 10;
					}
				}
				System.out.println("���б��� " + monthscholarship + "���� �Դϴ�.");

				int monthadditionalClass = 0;
				for (Student student : academy.studentService.students) {
					ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
					if (additionalClassCalculation(student, reportCard) > 0) {
						monthadditionalClass += additionalClassCalculation(student, reportCard) * 10;
					}
				}
				System.out.println("���������� " + monthadditionalClass + "���� �Դϴ�.");

				int monthAdditionalPay = 0;
				for (int i = 1; i < academy.classRoomService.classRooms.size(); i++) {
					sum = 0;
					for (Student student : academy.classRoomService.classRooms.get(i).students) {
						ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
						if (scholarshipCalculation(student, reportCard) > 0) { // �ݿ� �ִ� �л����� �ֱټ���ǥ���� ���б� �������ִ��� ī����
							sum += scholarshipCalculation(student, reportCard) * 3;
						}
					}
					monthAdditionalPay += academy.teacherService.teachers.get(i - 1).basicPay + sum;

				}
				System.out.println("����޿� " + monthAdditionalPay + "���� �Դϴ�.");
				System.out.println("��������" + (monthIncome - monthAdditionalPay) + "���� �Դϴ�.");
				break;

			case COMMAND_NON_PAIEMENT:
				index = 1;
				sum = 0;
				int membershipFee=0;
				System.out.printf("%10s \t %-5s %-5s\n", "�� �̸�","| �л���","| �̳��ݾ�");
				System.out.println("-------------------------------");
				for (Student student : academy.studentService.students) {
					if (!student.payment) {// payment�� false�ΰ�� ��� ���
						ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
						int scholarship = (academy.receiptService.scholarshipCalculation(student, reportCard)) * 10;
						int additionalClass = (academy.receiptService.additionalClassCalculation(student, reportCard))
								* 10;
						membershipFee = 200 - scholarship + additionalClass;
						System.out.printf("%-3s %-10s %-5s %-5s \n", "(" + (index++) + ")", "|"+student.classRoom.className,
								"|"+student.name, "|"+membershipFee + "����");
						sum+=membershipFee;
					}
				}
				System.out.println((now.get(Calendar.MONTH) + 1) + "�� �̳� �ݾ�:" + sum + "����");
				break;

			case COMMAND_BREAK:
				isClosed = true;
				break;

			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");

			}
		}
	}

	public int scholarshipCalculation(Student student, ReportCard reportCard) { //���бݹ������ִ��� ī����
		int cnt = 0;
		if (reportCard.korean == 1 || reportCard.korean + 2 <= student.bestkorean) 
			cnt++;
		if (reportCard.math == 1 || reportCard.math + 2 <= student.bestmath)
			cnt++;
		if (reportCard.english == 1 || reportCard.english + 2 <= student.bestenglish)
			cnt++;
		if (reportCard.add1 == 1 || reportCard.add1 + 2 <= student.bestadd1)
			cnt++;
		if (reportCard.add2 == 1 || reportCard.add2 + 2 <= student.bestadd2)
			cnt++;
		return cnt;
	}

	public void scholarshipCalculationPrint(Student student, ReportCard reportCard) { //���б� ���� ����Ʈ
		if (scholarshipCalculation(student, reportCard) > 0) {
			if (reportCard.korean == 1 || reportCard.korean + 2 <= student.bestkorean) {
				if (reportCard.korean == 1) {
					System.out.printf("%-7s", "���� 1��� ");
				} else {
					System.out.printf("%-7s", "���� ��� ");
				}
			}
			if (reportCard.math == 1 || reportCard.math + 2 <= student.bestmath) {

				if (reportCard.math == 1) {
					System.out.printf("%-7s", "���� 1��� ");
				} else {
					System.out.printf("%-7s", "���� ��� ");
				}
			}
			if (reportCard.english == 1 || reportCard.english + 2 <= student.bestenglish) {

				if (reportCard.english == 1) {
					System.out.printf("%-7s", "���� 1��� ");
				} else {
					System.out.printf("%-7s", "���� ���");
				}
			}
			if (reportCard.add1 == 1 || reportCard.add1 + 2 <= student.bestadd1) {

				if (reportCard.add1 == 1) {
					System.out.printf("%-7s", "Ž��1 1��� ");
				} else {
					System.out.printf("%-7s", "Ž��1 ��� ");
				}
			}
			if (reportCard.add2 == 1 || reportCard.add2 + 2 <= student.bestadd2) {
				if (reportCard.add2 == 1) {
					System.out.printf("%-10s", "Ž��2 1��� ");
				} else {
					System.out.printf("%-10s", "Ž��2 ��� ");
				}
			}
		} else {
			System.out.printf("%-7s", "�ش���� ����");
		}
	}

	public int additionalClassCalculation(Student student, ReportCard reportCard) {
		int cnt = 0;
		if (reportCard.korean >= 3) {
			cnt++;
		}
		if (reportCard.math >= 3) {
			cnt++;
		}
		if (reportCard.english >= 3) {
			cnt++;
		}
		if (reportCard.add1 >= 3) {
			cnt++;
		}
		if (reportCard.add2 >= 3) {
			cnt++;
		}
		return cnt;
	}

	public void additionalClassCalculationPrint(Student student, ReportCard reportCard) {
		if (additionalClassCalculation(student, reportCard) > 0) {
			if (reportCard.korean >= 3) {
				System.out.printf("%-7s", "���� " + reportCard.korean + "���"); 
			}
			if (reportCard.math >= 3) {
				System.out.printf("%-7s", "���� " + reportCard.math + "���");
			}
			if (reportCard.english >= 3) {
				System.out.printf("%-7s", "���� " + reportCard.english + "���");
			}
			if (reportCard.add1 >= 3) {
				System.out.printf("%-8s", "Ž��1 " + reportCard.add1 + "���");
			}
			if (reportCard.add2 >= 3) {
				System.out.printf("%-8s", "Ž��2 " + reportCard.add2 + "���");
			}
		} else {
			System.out.printf("%-7s", "�ش���� ����");
		}
	}

}


