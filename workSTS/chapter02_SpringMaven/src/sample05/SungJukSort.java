package sample05;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SungJukSort implements SungJuk {
	@Autowired
	@Qualifier("list")
	private List<SungJukDTO2> list;

	@Override
	public void execute() {
		Collections.sort(list);
		for (SungJukDTO2 sungJukDTO2 : list) {
			System.out.println(sungJukDTO2);
		}

	}

}
