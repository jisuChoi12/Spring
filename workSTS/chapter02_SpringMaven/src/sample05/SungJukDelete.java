package sample05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SungJukDelete implements SungJuk {
	@Autowired
	private ArrayList<SungJukDTO2> list;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 이름 입력: ");
		String name = scan.next();

		int sw = 0;
		Iterator<SungJukDTO2> it = list.iterator();
		while(it.hasNext()) {
			if (it.next().getName().equals(name)) {
				it.remove();
				sw = 1;
			}
		}
		if (sw == 0) {
			System.out.println("찾고자 하는 이름이 없습니다.");
		} else {
			System.out.println(name + "님의 데이터를 삭제하였습니다.");
		}
	}

}
