package cn.aopdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class FirstAspect {
	@Pointcut("execution(* cn.aopdemo.Car.go(..))")
	public void pointCut(){}
	@After("pointCut()")
	public void after(){
		System.out.println("after!!!!");
	}
	@Before("pointCut()")
	public void before(JoinPoint joinPoint){
		System.out.println("before........");
		Object[] args = joinPoint.getArgs();
		System.out.println("........");
		for(Object obj : args)
			System.out.println(obj);
		System.out.println("........");
		String className = joinPoint.getTarget().getClass().getName();
	}
}
