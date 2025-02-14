package com.example.spring01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.spring01.service.HomeService;
import com.example.spring01.service.HomeServiceImpl;

import jakarta.annotation.PostConstruct;

/*
 *  Spring 3가지 규칙
 *  1. 핵심 의존 객체를 직접 생성하지 않는다.
 *  2. 필요한 핵심 객체가 있다면 spring 프레임워크로부터 주입받아 사용한다.
 *  3. 의존 객체는 주로 interface type 으로 받아서 사용한다.
 */

/*
 * 	@SpringBootApplication 어노테이션이 많은 기능을 하는데 그 중 하나는
 * 	이 클래스(Spring01Application)로 객체를 생성해서 관리해 준다.
 */

@SpringBootApplication
public class Spring01HelloApplication {
	
	// Spring01HelloApplication 객체 안에서 필요한 객체가 있다면 필드를 선언하고
	// @Autowired 어노테이션을 붙여놓으면 해당 객체가 주입(DI)된다.
	
	// 2번 방법 -> dao 주입 받아서 사용할 때 많이 쓰인다!
	@Autowired
	HomeService service; // 주입을 해줘서 null 이 아니다.
	
	// 이 클래스로 객체가 생성된 이후에 호출하고 싶은 메소드에 @PostConstruct 어노테이션을 붙여 놓으면 된다.
	@PostConstruct
	public void exec() {
		System.out.println("exe() 메소드가 호출됨");
		service.clean("홍석님");
		
		// 홍석님의 빨래를 하고 싶으면
		service.wash("홍석님");
		
		// 구멍 뚫기
		service.hole("바닥");
	}
	
	public static void main(String[] args) {
		// run() 메소드가 리턴하는 객체(Spring Bean Container 라고 생각하면 된다.)
		ApplicationContext context = SpringApplication.run(Spring01HelloApplication.class, args);
		
		// Spring 이 관리하는 객체 중에 HomeService type 객체를 얻어낸다.
		// 1번 방법
		HomeService service = context.getBean(HomeService.class);
		service.clean("원숭이");
	}

}
