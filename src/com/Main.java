package com;

public class Main {
	public static void main(String[] args) {
		Academy academy = new Academy("이젠 재수학원", "031-1234-5678"); // 이름과 번호 바꾸기 가능

		int loginIndex = academy.login();
		if (loginIndex == 1) {
			academy.init();
			academy.run();
		} else if (loginIndex == -1) {
			System.out.println("유효하지않은 ID입니다. 프로그램을 종료합니다.");
		} else if (loginIndex == -2) {
			System.out.println("비밀번호가 틀렸습니다. 프로그램을 종료합니다.");
		}

		academy.close();
	}
}
