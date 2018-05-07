package cn.aopdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class CarLog {
	public void beforeGo(JoinPoint joinPoint){
//		System.out.println(joinPoint.getArgs());
		System.out.println("beforeGo");
	}
	public void afterGo(){
		System.out.println("afterGo");
	}
	public void aroundGo(ProceedingJoinPoint proceedingJoinPoint ){
		System.out.println("Car Going");
		try {
			proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Car Gone");
	}
}	
