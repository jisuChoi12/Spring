package sample05;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SungJukModify implements SungJuk {
	@Autowired
	private ArrayList<SungJukDTO2> list;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("수정할 이름 입력: ");
		String name = scan.next();

		int sw = 0;
		for (SungJukDTO2 sungJukDTO2 : list) {
			if (sungJukDTO2.getName().equals(name)) {
				System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
				System.out.println(sungJukDTO2);

				System.out.println(name + "님의 데이터 수정을 시작합니다.");
				System.out.print("국어 : ");
				sungJukDTO2.setKor(scan.nextInt());
				System.out.print("영어 : ");
				sungJukDTO2.setEng(scan.nextInt());
				System.out.print("수학 : ");
				sungJukDTO2.setMath(scan.nextInt());
				sungJukDTO2.setTot(sungJukDTO2.getKor() + sungJukDTO2.getEng() + sungJukDTO2.getMath());
				sungJukDTO2.setAvg(sungJukDTO2.getTot() / 3.0);
				sw = 1;
			}
		}
		if (sw == 0) {
			System.out.println("찾고자 하는 이름이 없습니다.");
		} else {
			System.out.println(name + "님의 데이터를 수정하였습니다.");
		}
	}

}
