package sample05;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope("prototype")
public class SungJukDTO2 implements Comparable<SungJukDTO2> {
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private double avg;

	@Override
	public String toString() {
		return name + "\t" + kor + "\t" + eng + "\t" + math + "\t" + tot + "\t" + String.format("%.3f", avg);
	}

	@Override
	public int compareTo(SungJukDTO2 o) {
		if (this.getTot() > o.getTot()) {
			return -1;
		} else if (this.getTot() < o.getTot()) {
			return 1;
		}
		return 0;
	}
}
