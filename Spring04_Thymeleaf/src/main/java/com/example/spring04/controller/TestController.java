package com.example.spring04.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring04.dto.MemberDto;

@Controller
public class TestController {
	
	@GetMapping("/sequence")
	public String sequence() {
		
		return "test/sequence";
	}
	
	@GetMapping("/member/list")
	public String memberList(Model model) {
 		MemberDto dto1 = MemberDto.builder().num(1).name("김구라").addr("노량진").build();
		MemberDto dto2 = MemberDto.builder().num(2).name("해골").addr("행신동").build();
		MemberDto dto3 = MemberDto.builder().num(3).name("원숭이").addr("동물원").build();
		List<MemberDto> list = List.of(dto1, dto2, dto3);
		model.addAttribute("list", list);
		return "member/list";
	}
	
	@GetMapping("/notice")
	public String notice(Model model) {
		List<String> noticeList = List.of("Thymeleaf view engine 을 배워 보아요!", "어쩌구...", "저쩌구...", "하나", "두울");
		model.addAttribute("noticeList", noticeList);
		return "post/notice";
	}
	
	@GetMapping("/member")
	public String member(Model model) {
		MemberDto dto = MemberDto.builder().num(1).name("김구라").addr("노량진").build();
		model.addAttribute("dto", dto);
		return "member/info";
	}
}
