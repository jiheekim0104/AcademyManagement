package com;

public class Main {
	public static void main(String[] args) {
		Academy academy = new Academy("���� ����п�", "031-1234-5678"); // �̸��� ��ȣ �ٲٱ� ����

		int loginIndex = academy.login();
		if (loginIndex == 1) {
			academy.init();
			academy.run();
		} else if (loginIndex == -1) {
			System.out.println("��ȿ�������� ID�Դϴ�. ���α׷��� �����մϴ�.");
		} else if (loginIndex == -2) {
			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�. ���α׷��� �����մϴ�.");
		}

		academy.close();
	}
}
