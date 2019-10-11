package sample01;

public interface MessageBean {
	public void showPrintBefore(); // 조인 포인트 | 포인트 컷
	public void viewPrintBefore(); // 조인 포인트 | 포인트 컷
	
	public void showPrintAfter(); // 조인 포인트 | 포인트 컷
	public void viewPrintAfter(); // 조인 포인트 | 포인트 컷
	
	public String showPrint(); // 조인 포인트 | 포인트 컷
	public void viewPrint(); // 조인 포인트 | 포인트 컷
	
	public void display(); // 조인 포인트
	
}
