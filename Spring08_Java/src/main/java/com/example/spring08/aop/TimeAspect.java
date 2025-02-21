package com.example.spring08.aop;

import java.util.Date;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * 횡단 관심사를 따로 클래스를 만들어서 작성한다.
 */

@Aspect
@Component
public class TimeAspect {
	
	@Before("execution(void write*())")
	public void start() {
		Date start = new Date();
		long startTime = start.getTime();
		System.out.println("시작 시간:" + startTime);
	}
	
	@After("execution(void write*())")
	public void end() {
		Date end = new Date();
		long endTime = end.getTime();
		System.out.println("종료 시간:" + endTime);
	}
}
