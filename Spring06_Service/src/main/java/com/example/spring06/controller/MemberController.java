package com.example.spring06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring06.dto.MemberDto;
import com.example.spring06.service.MemberService;

@Controller
public class MemberController {
	
	// 서비스 객체는 Controller 에서 활용되는 유틸리티 라고 생각하면 된다.
	@Autowired
	private MemberService service;
	
	@GetMapping("/member/edit")
	public String editForm(int num) {
		return "member/edit";
	}
	
	@PostMapping("/member/update")
	public String update(MemberDto dto) {
		service.update(dto);
		return "member/update";
	}
	
	@GetMapping("/member/delete")
	public String delete(int num) {
		service.deleteById(num);
		return "member/delete";
	}
	
	@PostMapping("/member/insert")
	public String insert(MemberDto dto) {
		service.save(dto);
		return "member/insert";
	}
	
	@GetMapping("/member/new")
	public String newForm() {
		return "member/new";
	}
	
	@GetMapping("/member/list")
	public String list(Model model) {
		List<MemberDto> list = service.findAll();
		model.addAttribute("list", list);
		return "member/list";
	}
	
}
