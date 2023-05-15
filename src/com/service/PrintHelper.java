package com.service;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.Academy;
import com.element.ClassRoom;
import com.element.ReportCard;
import com.element.Student;

public class PrintHelper {

	public Academy academy;

	public PrintHelper(Academy academy) {
		this.academy = academy;

	}
	
	
	static void searchPrint(Student student) { // �л� ���� ��¿�
		System.out.printf("%-10s", "�̸�: " + student.name + " /");
		System.out.println(student.classRoom.className);
		System.out.println("�л���ȭ: " + student.phone);
		System.out.println("�����б�: " + student.school);
		System.out.println("�кθ��: " + student.parentName);
		System.out.println("�кθ���ȭ: " + student.parentPhone);
	}

	static void print(ReportCard reportCard) { // �л��� + �� ������ ����ǥ 1�� ��¿�
		if (reportCard.month == 0) {
			System.out.println("'" + reportCard.student.name + "' �л��� ���� �����Դϴ�.");
		} else {
			System.out.println("'" + reportCard.student.name + "' �л��� " + reportCard.month + "�� �����Դϴ�.");
		}
		System.out.println("����(" + reportCard.korean + ") ����(" + reportCard.math + ") ����(" + reportCard.english
				+ ") Ž��1(" + reportCard.add1 + ") Ž��2(" + reportCard.add2 + ")");
	}

	public static void print(List<ReportCard> reportCards) { // �л��� + �� ������ ����ǥ�� ��¿�
		for (ReportCard reportCard : reportCards) {

			if (reportCard.month == 0) {
				System.out.println("'" + reportCard.student.name + "' �л��� ���� �����Դϴ�.");
			} else {
				System.out.println("'" + reportCard.student.name + "' �л��� " + reportCard.month + "�� �����Դϴ�.");
			}
			System.out.println("����(" + reportCard.korean + ") ����(" + reportCard.math + ") ����(" + reportCard.english
					+ ") Ž��1(" + reportCard.add1 + ") Ž��2(" + reportCard.add2 + ")");
		}

	}

	public interface GetScore {
		public int getScore(ReportCard reportCard);
	}

	public static void printGraph(String title, List<ReportCard> reportCards, GetScore getScore) {
		for (int i = 1; i <= 9; i++) {
			System.out.print(i + "���|");
			for (ReportCard reportCard : reportCards) { //��������� �Ǿ����
				if (getScore.getScore(reportCard) <= i) {
					System.out.print(" * ");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println();
		}
		System.out.printf("%5s", "+");
		System.out.println("---------------------------------");

		System.out.printf("%2s %2s %2s %2s %2s %2s %2s %2s %2s %2s %2s %2s\n", title, "����", "1��", "2��", "3��", "4��",
				"5��", "6��", "7��", "8��", "9��", "10��");
	}

	
	public interface GetAvg {
		public int getAvg(ReportCard reportCard);
	}


	
	public static void printClass(String title, List<ReportCard> preReportCards, List<ReportCard> newReportCards , GetAvg getAvg) {
		int preSum = 0;
		for (ReportCard reportCard : preReportCards) {
			preSum += getAvg.getAvg(reportCard);
		}
		int newSum = 0;
		for (ReportCard reportCard : newReportCards) {
			newSum += getAvg.getAvg(reportCard);
		}
		double preAvg = (double) preSum/ preReportCards.size();
		double newAvg = (double) newSum/ newReportCards.size();
		String message = null;
		if(preAvg-newAvg==0) {
			message ="����";
		} else {
			message=(preAvg-newAvg>0)?(preAvg*10-newAvg*10)/10+" ���":(newAvg*10-preAvg*10)/10+" �϶�";
		}
				
		System.out.println(title +" ���: " +preAvg+" -> "+ newAvg+" ("+message+")");
	
	}
}