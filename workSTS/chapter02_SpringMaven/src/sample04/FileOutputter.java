package sample04;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class FileOutputter implements Outputter {
	private String filePath="C:/Spring/";
	private String fileName="test.txt";

	@Override
	public void output(String message) {
		System.out.println("output 메소드");
		try {
			FileWriter fileWriter = new FileWriter(filePath+fileName);
			fileWriter.write(message);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
