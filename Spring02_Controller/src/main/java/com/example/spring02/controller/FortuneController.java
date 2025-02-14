package com.example.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FortuneController {
	@ResponseBody
	@GetMapping("/fortune")
	public String fortune() {
		return "오늘의 운세는 꽝입니다!";
	}
}
