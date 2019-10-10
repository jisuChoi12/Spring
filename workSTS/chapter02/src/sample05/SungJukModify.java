package sample05;

import java.util.List;
import java.util.Scanner;

import lombok.Setter;

@Setter
public class SungJukModify implements SungJuk {
	private List<SungJukDTO> list;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("수정할 이름 입력: ");
		String name = scan.next();

		int sw = 0;
		for (SungJukDTO sungJukDTO : list) {
			if (sungJukDTO.getName().equals(name)) {
				System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
				System.out.println(sungJukDTO);

				System.out.println(name + "님의 데이터 수정을 시작합니다.");
				System.out.print("국어 : ");
				sungJukDTO.setKor(scan.nextInt());
				System.out.print("영어 : ");
				sungJukDTO.setEng(scan.nextInt());
				System.out.print("수학 : ");
				sungJukDTO.setMath(scan.nextInt());
				sungJukDTO.setTot(sungJukDTO.getKor() + sungJukDTO.getEng() + sungJukDTO.getMath());
				sungJukDTO.setAvg(sungJukDTO.getTot() / 3.0);
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
