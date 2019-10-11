package sample01;

import lombok.Setter;

// Target - 모든 추상 메소드 들을 오버라이딩 하고 있으니까
public class MessageBeanImpl implements MessageBean {
	@Setter
	private String str;

	@Override
	public void showPrintBefore() {
		System.out.println("showPrintBefore 메세지 : " + str); // 핵심관심사항

	}

	@Override
	public void viewPrintBefore() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("viewPrintBefore 메세지 : "+str); // 핵심관심사항
		
	}


	@Override
	public void showPrintAfter() {
		System.out.println("showPrintAfter 메세지 : " + str); // 핵심관심사항
		
	}

	@Override
	public void viewPrintAfter() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("viewPrintAfter 메세지 : "+str); // 핵심관심사항
		
	}

	@Override
	public String showPrint() {
		System.out.println("showPrint 메세지 : " + str); // 핵심관심사항
		return ":D";
	}

	@Override
	public void viewPrint() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("viewPrint 메세지 : "+str); // 핵심관심사항
		
	} 
	
	@Override
	public void display() {
		System.out.println("display 메세지 : " + str); // 핵심관심사항
	}

}
