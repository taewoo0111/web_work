package com.example.spring08.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessengerAspect {
	/*
	 * 메소드의 return type -> String
	 * com.example.spring08.util 패키지에 속해있는 모든 클래스 중에서
	 * get 으로 시작하는 메소드
	 * 메소드의 매개변수는 비어있는 메소드
	 */
	@Around("execution(String com.example.spring08.util.*.get*())")
	public Object checkReturn(ProceedingJoinPoint joinPoint) throws Throwable{
		Object obj = joinPoint.proceed();
		String returnValue = (String)obj;
		System.out.println("apsect 가 적용된 메소드가 리턴한 문자열:" + returnValue);
		
		// 리턴 값이 있는 메소드에 aspect 를 적용하면 반드시 해당 데이터를 리턴해야 한다.
		//return obj;
		return "공부하지 마!";
	}
	
	// .. 은 매개변수의 모양을 상관하지 않는다는 뜻
	@Around("execution(void send*(..))")
	public void checkGreeting(ProceedingJoinPoint joinPoint) throws Throwable { // joinPoint -> 해당 aop 가 실행되는 그 지점
		
		Object[] args = joinPoint.getArgs();
		for(Object tmp : args) {
			if(tmp instanceof String) {
				String msg = (String)tmp;
				System.out.println("aspect 에서 읽어낸 내용:" +msg);
				if(msg.contains("바보")) {
					System.out.println("바보는 금지된 단어 입니다!");
					return;
				}
			}
		}
		// 여기서 실제로 aspect 가 적용된 메소드가 수행한다.(호출하지 않으면 수행 안됨!)
		joinPoint.proceed();
	}
}
