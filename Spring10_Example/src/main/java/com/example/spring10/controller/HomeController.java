package com.example.spring10.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<String> noticeList = List.of("Spring Boot 프로젝트 시작입니다.", "열심히 해봐요", "어쩌구...", "저쩌구...");
		model.addAttribute("noticeList", noticeList);
		
		return "home";
	}
}
