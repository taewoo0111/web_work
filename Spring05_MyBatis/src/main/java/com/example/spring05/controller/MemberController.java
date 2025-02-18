package com.example.spring05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring05.dto.MemberDto;
import com.example.spring05.repository.MemberDao;

@Controller
public class MemberController {
	
	@Autowired MemberDao dao;
	
	@GetMapping("/member/edit")
	public String editForm(int num) {
		return "member/edit";
	}
	
	@PostMapping("/member/update")
	public String update(MemberDto dto) {
		dao.update(dto);
		return "member/update";
	}
	
	@GetMapping("/member/delete")
	public String delete(int num) {
		dao.delete(num);
		return "member/delete";
	}
	
	@PostMapping("/member/insert")
	public String insert(MemberDto dto) {
		dao.insert(dto);
		return "member/insert";
	}
	
	@GetMapping("/member/new")
	public String newForm() {
		return "member/new";
	}
	
	@GetMapping("/member/list")
	public String list(Model model) {
		
		List<MemberDto> list = dao.getList();
		model.addAttribute("list", list);
		
		return "member/list";
	}
	
}
