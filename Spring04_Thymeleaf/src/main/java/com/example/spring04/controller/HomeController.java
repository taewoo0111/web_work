package com.example.spring04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		
		// 오늘의 인물
		String personToday = "최유진";
		model.addAttribute("personToday", personToday);
		
		// GET 방식 파라미터 출력 테스트를 위해서 필요한 sample 데이터도 Model 에 담기
		model.addAttribute("id", 99);
		model.addAttribute("category", "foods");
		
		/*
		 *  아무런 설정 없이 여기에서 "home" 이라는 문자열을 리턴하면
		 *  "/templates/" + "home" + ".html" 로 연결이 되어서 결국
		 *  /templates/home.html view page 로 응답하겠다는 의미가 된다.
		 *  
		 *  /static/ 폴더의 html 페이지는 내용 그대로 응답되지만
		 *  /templates/ 폴더의 html 페이지는 view engine(Thymeleaf) 가 해석을 하고 해석된 결과가 응답한다.
		 */
		return "home";
	}
}
