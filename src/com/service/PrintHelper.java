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
	
	
	static void searchPrint(Student student) { // 학생 정보 출력용
		System.out.printf("%-10s", "이름: " + student.name + " /");
		System.out.println(student.classRoom.className);
		System.out.println("학생전화: " + student.phone);
		System.out.println("졸업학교: " + student.school);
		System.out.println("학부모명: " + student.parentName);
		System.out.println("학부모전화: " + student.parentPhone);
	}

	static void print(ReportCard reportCard) { // 학생명 + 월 조합의 성적표 1개 출력용
		if (reportCard.month == 0) {
			System.out.println("'" + reportCard.student.name + "' 학생의 수능 성적입니다.");
		} else {
			System.out.println("'" + reportCard.student.name + "' 학생의 " + reportCard.month + "월 성적입니다.");
		}
		System.out.println("국어(" + reportCard.korean + ") 수학(" + reportCard.math + ") 영어(" + reportCard.english
				+ ") 탐구1(" + reportCard.add1 + ") 탐구2(" + reportCard.add2 + ")");
	}

	public static void print(List<ReportCard> reportCards) { // 학생명 + 월 조합의 성적표들 출력용
		for (ReportCard reportCard : reportCards) {

			if (reportCard.month == 0) {
				System.out.println("'" + reportCard.student.name + "' 학생의 수능 성적입니다.");
			} else {
				System.out.println("'" + reportCard.student.name + "' 학생의 " + reportCard.month + "월 성적입니다.");
			}
			System.out.println("국어(" + reportCard.korean + ") 수학(" + reportCard.math + ") 영어(" + reportCard.english
					+ ") 탐구1(" + reportCard.add1 + ") 탐구2(" + reportCard.add2 + ")");
		}

	}

	public interface GetScore {
		public int getScore(ReportCard reportCard);
	}

	public static void printGraph(String title, List<ReportCard> reportCards, GetScore getScore) {
		for (int i = 1; i <= 9; i++) {
			System.out.print(i + "등급|");
			for (ReportCard reportCard : reportCards) { //역순출력이 되어야함
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

		System.out.printf("%2s %2s %2s %2s %2s %2s %2s %2s %2s %2s %2s %2s\n", title, "수능", "1월", "2월", "3월", "4월",
				"5월", "6월", "7월", "8월", "9월", "10월");
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
			message ="유지";
		} else {
			message=(preAvg-newAvg>0)?(preAvg*10-newAvg*10)/10+" 상승":(newAvg*10-preAvg*10)/10+" 하락";
		}
				
		System.out.println(title +" 평균: " +preAvg+" -> "+ newAvg+" ("+message+")");
	
	}
}