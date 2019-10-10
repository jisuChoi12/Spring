package sample05;

import java.util.List;
import java.util.Scanner;
import lombok.Setter;

@Setter
public class SungJukInput implements SungJuk {
	private List<SungJukDTO> list;
	private SungJukDTO sungJukDTO;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("국어 : ");
		int kor = scan.nextInt();
		System.out.print("영어 : ");
		int eng = scan.nextInt();
		System.out.print("수학 : ");
		int math = scan.nextInt();

		int tot = kor + eng + math;
		double avg = tot / 3.0;

		sungJukDTO.setName(name);
		sungJukDTO.setKor(kor);
		sungJukDTO.setEng(eng);
		sungJukDTO.setMath(math);
		sungJukDTO.setTot(tot);
		sungJukDTO.setAvg(avg);

		list.add(sungJukDTO);
		System.out.println("총 " + list.size() + "개의 데이터가 저장되어 있습니다.");

	}
}
