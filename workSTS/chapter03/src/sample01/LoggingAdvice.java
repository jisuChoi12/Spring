package sample01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

// 공통모듈(Aspect)..?
public class LoggingAdvice {
	public void beforeTrace() {
		System.out.println("before trace...");
	}

	public void afterTrace() {
		System.out.println("after trace...");
	}

	public void trace(ProceedingJoinPoint joinPoint) throws Throwable {
		// before
		String methodName = joinPoint.getSignature().toShortString(); // 어떤 메소드가 나를 호출했는지 
		System.out.println("methodName : " + methodName);

		StopWatch sw = new StopWatch();
		sw.start(methodName);
		System.out.println("호출 전 : " + methodName);

		// 핵심사항
		Object ob = joinPoint.proceed(); 
		System.out.println("ob : " + ob);

		// after
		sw.stop();
		System.out.println("호출 후 : " + methodName);
		System.out.println("처리 시간 : " + sw.getTotalTimeMillis() / 1000 + "초");
	}
}

// proceed();
// 핵심 메소드를 원하는 시점에 호출