package com.example.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SendController {
	
	/*
	 * Controller 메소드 안에서 HttpServletRequest, HttpServletResponse, HttpSession 등의 객체가 필요하면
	 * 매개변수에 선언하면 된다.
	 * 
	 * 선언만하면 spring 프레임워크가 알아서 해당 객체의 참조값을 매개변수에 전달해준다.
	 */
	
	@ResponseBody
	@PostMapping("/send")
	public String send(HttpServletRequest request) {
		
		String msg = request.getParameter("msg");
		System.out.println(msg);
		
		return "/send okay";
	}
	
	// 전송되는 파라미터명과 동일하게 매개 변수를 선언하면 자동으로 추출되어서 매개 변수에 전달된다.
	@ResponseBody
	@PostMapping("/send2")
	public String send2(String msg) {
		
		System.out.println(msg);
		return "/send2 okay!";
	}
}
