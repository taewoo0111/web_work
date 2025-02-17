package com.example.spring03.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring03.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		// 공지 사항
		List<String> notice = List.of("Spring Boot 시작입니다", "어쩌구...", "저쩌구...");
		model.addAttribute("notice", notice);
		
		// view page 의 위치(응답을 위임할 jsp 페이지의 위치)를 리턴해 준다.
		return "home";
	}
	
	@GetMapping("/fortune")
	public String fortune(HttpServletRequest request) {
		// 오늘의 운세를 알려주는 비즈니스 로직 수행
		String fortuneToday = "동쪽으로 가면 귀인을 만나요!";
		// 오늘의 운세를 request scope 에 담고
		request.setAttribute("fortuneToday", fortuneToday);
		// view page 로 forward 이동해서 응답하기
		return "fortune";
	}
	
	@GetMapping("/fortune2")
	public String fortune2(Model model) {
		String fortuneToday = "동쪽으로 가면 귀인을 만나요!";
		
		// Model 객체에 응답에 필요한 데이터를 담으면 자동으로 request 영역에 담긴다.
		model.addAttribute("fortuneToday", fortuneToday);
		
		return "fortune";
	}
	
	@GetMapping("/member")
	public String member(Model model) {
		MemberDto dto = MemberDto.builder().num(1).name("김구라").addr("노량진").build();
		model.addAttribute("dto", dto);
		return "member/info";
	}
}
