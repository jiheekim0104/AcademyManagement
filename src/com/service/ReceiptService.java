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
			System.out.println("메인>>학원경엉-------------------");
			System.out.println("1.장학금 2.보충수강료 3.강사급여관리 4.월수입 5.미납회비 0.이전메뉴로");
			System.out.println("-----------------------------");
			System.out.print("선택>");
			command = academy.getInteger();
			switch (command) {
			case COMMAND_SCHOLARSHIP:
				System.out.println((now.get(Calendar.MONTH) + 1) + "월 장학생 명단 입니다.");
				int index = 1;
				int sum = 0;
				
				System.out.format("%20s\t%14s\t %s\t %10s\n", "반 이름","| 학생명","| 장학금 |","지급사유");
				System.out.println("---------------------------------------------------------------------------------------------------");
				for (Student student : academy.studentService.students) {
					ReportCard reportCard = academy.reportCardService.getReportCard(student, null); // 최신성적을 받아옴
					if (scholarshipCalculation(student, reportCard) > 0) { // 장학금을 받을수있는지 카운팅
						System.out.format("%-5s %-17s\t %s \t %s\t", "(" + (index++) + ")","|"+student.classRoom.className,
								"|"+student.name, "|"+scholarshipCalculation(student, reportCard) * 10 + "만원  |");
				
						scholarshipCalculationPrint(student, reportCard); // 장학금 사유를 출력함
						System.out.println();
						sum += scholarshipCalculation(student, reportCard) * 10;
					}
				}
				System.out.println("총 " + sum + "만원 입니다.");

				break;

			case COMMAND_ADDITIONAL_CLASS_FEE:
				System.out.println((now.get(Calendar.MONTH) + 1) + "월 보충수강생 명단 입니다.");
				index = 1;
				sum = 0;
				System.out.format("%20s\t%14s\t %s\t %10s\n", "반 이름","| 학생명","|보충수강료|","수강사유");
				System.out.println("---------------------------------------------------------------------------------------------------");
				
				for (Student student : academy.studentService.students) {
					ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
					if (additionalClassCalculation(student, reportCard) > 0) {
						System.out.printf("%-5s %-17s\t %s \t %s\t", "(" + (index++) + ")", "|"+student.classRoom.className,
								"|"+student.name, "|"+additionalClassCalculation(student, reportCard) * 10 + "만원   |");
						additionalClassCalculationPrint(student, reportCard);
						System.out.println();
						sum += additionalClassCalculation(student, reportCard) * 10;
					}
				}
				System.out.println("총 " + sum + "만원 입니다.");
				break;

			case COMMAND_TEACHER_PAY_MANAGE:
				System.out.println((now.get(Calendar.MONTH) + 1) + "월 강사급여 입니다.");
				System.out.printf("%10s \t %-5s %-5s \t %-5s \n", "반 이름","| 담임","| 성과금","| 이달급여");
				System.out.println("--------------------------------------------");
				
				for (int i = 1; i < academy.classRoomService.classRooms.size(); i++) {
					sum = 0;
					System.out.printf("%-10s \t %-5s", academy.classRoomService.classRooms.get(i).className,
							"|"+academy.teacherService.teachers.get(i - 1).name);

					for (Student student : academy.classRoomService.classRooms.get(i).students) { // 반에 있는 학생들의 최근성적표에서
						ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
						if (scholarshipCalculation(student, reportCard) > 0) { // 장학금 받을수있는지 카운팅
							sum += scholarshipCalculation(student, reportCard) * 3;
						}
					}
					System.out.printf(" %-5s \t %-5s \n", "|"+sum+"만원",
							"|"+(academy.teacherService.teachers.get(i - 1).basicPay + sum + "만원")); // 담임은 0번 인덱스부터 시작이라 -1

				}
				break;

			case COMMAND_MONTH_INCOME:
				int monthIncome = 0;
				for (Student student : academy.studentService.students) {
					if (student.payment) { // payment가 true인 학생이라면
						ReportCard reportCard = academy.reportCardService.getReportCard(student, null); // 최근성적표를 가지고
						int scholarship = (academy.receiptService.scholarshipCalculation(student, reportCard)) * 10; // 장학금과
						int additionalClass = (academy.receiptService.additionalClassCalculation(student, reportCard))
								* 10; // 보충수강료 계산함
						monthIncome += 200 - scholarship + additionalClass;
					}
				}
				System.out.println((now.get(Calendar.MONTH) + 1) + "월 총 납부된 수강료는 " + monthIncome + "만원 입니다.");

				int monthscholarship = 0;
				for (Student student : academy.studentService.students) {
					ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
					if (scholarshipCalculation(student, reportCard) > 0) {
						monthscholarship += scholarshipCalculation(student, reportCard) * 10;
					}
				}
				System.out.println("장학금은 " + monthscholarship + "만원 입니다.");

				int monthadditionalClass = 0;
				for (Student student : academy.studentService.students) {
					ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
					if (additionalClassCalculation(student, reportCard) > 0) {
						monthadditionalClass += additionalClassCalculation(student, reportCard) * 10;
					}
				}
				System.out.println("보충수강료는 " + monthadditionalClass + "만원 입니다.");

				int monthAdditionalPay = 0;
				for (int i = 1; i < academy.classRoomService.classRooms.size(); i++) {
					sum = 0;
					for (Student student : academy.classRoomService.classRooms.get(i).students) {
						ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
						if (scholarshipCalculation(student, reportCard) > 0) { // 반에 있는 학생들의 최근성적표에서 장학금 받을수있는지 카운팅
							sum += scholarshipCalculation(student, reportCard) * 3;
						}
					}
					monthAdditionalPay += academy.teacherService.teachers.get(i - 1).basicPay + sum;

				}
				System.out.println("강사급여 " + monthAdditionalPay + "만원 입니다.");
				System.out.println("순수익은" + (monthIncome - monthAdditionalPay) + "만원 입니다.");
				break;

			case COMMAND_NON_PAIEMENT:
				index = 1;
				sum = 0;
				int membershipFee=0;
				System.out.printf("%10s \t %-5s %-5s\n", "반 이름","| 학생명","| 미납금액");
				System.out.println("-------------------------------");
				for (Student student : academy.studentService.students) {
					if (!student.payment) {// payment가 false인경우 명단 출력
						ReportCard reportCard = academy.reportCardService.getReportCard(student, null);
						int scholarship = (academy.receiptService.scholarshipCalculation(student, reportCard)) * 10;
						int additionalClass = (academy.receiptService.additionalClassCalculation(student, reportCard))
								* 10;
						membershipFee = 200 - scholarship + additionalClass;
						System.out.printf("%-3s %-10s %-5s %-5s \n", "(" + (index++) + ")", "|"+student.classRoom.className,
								"|"+student.name, "|"+membershipFee + "만원");
						sum+=membershipFee;
					}
				}
				System.out.println((now.get(Calendar.MONTH) + 1) + "월 미납 금액:" + sum + "만원");
				break;

			case COMMAND_BREAK:
				isClosed = true;
				break;

			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");

			}
		}
	}

	public int scholarshipCalculation(Student student, ReportCard reportCard) { //장학금받을수있는지 카운팅
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

	public void scholarshipCalculationPrint(Student student, ReportCard reportCard) { //장학금 사유 프린트
		if (scholarshipCalculation(student, reportCard) > 0) {
			if (reportCard.korean == 1 || reportCard.korean + 2 <= student.bestkorean) {
				if (reportCard.korean == 1) {
					System.out.printf("%-7s", "국어 1등급 ");
				} else {
					System.out.printf("%-7s", "국어 상승 ");
				}
			}
			if (reportCard.math == 1 || reportCard.math + 2 <= student.bestmath) {

				if (reportCard.math == 1) {
					System.out.printf("%-7s", "수학 1등급 ");
				} else {
					System.out.printf("%-7s", "수학 상승 ");
				}
			}
			if (reportCard.english == 1 || reportCard.english + 2 <= student.bestenglish) {

				if (reportCard.english == 1) {
					System.out.printf("%-7s", "영어 1등급 ");
				} else {
					System.out.printf("%-7s", "영어 상승");
				}
			}
			if (reportCard.add1 == 1 || reportCard.add1 + 2 <= student.bestadd1) {

				if (reportCard.add1 == 1) {
					System.out.printf("%-7s", "탐구1 1등급 ");
				} else {
					System.out.printf("%-7s", "탐구1 상승 ");
				}
			}
			if (reportCard.add2 == 1 || reportCard.add2 + 2 <= student.bestadd2) {
				if (reportCard.add2 == 1) {
					System.out.printf("%-10s", "탐구2 1등급 ");
				} else {
					System.out.printf("%-10s", "탐구2 상승 ");
				}
			}
		} else {
			System.out.printf("%-7s", "해당사항 없음");
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
				System.out.printf("%-7s", "국어 " + reportCard.korean + "등급"); 
			}
			if (reportCard.math >= 3) {
				System.out.printf("%-7s", "수학 " + reportCard.math + "등급");
			}
			if (reportCard.english >= 3) {
				System.out.printf("%-7s", "영어 " + reportCard.english + "등급");
			}
			if (reportCard.add1 >= 3) {
				System.out.printf("%-8s", "탐구1 " + reportCard.add1 + "등급");
			}
			if (reportCard.add2 >= 3) {
				System.out.printf("%-8s", "탐구2 " + reportCard.add2 + "등급");
			}
		} else {
			System.out.printf("%-7s", "해당사항 없음");
		}
	}

}


