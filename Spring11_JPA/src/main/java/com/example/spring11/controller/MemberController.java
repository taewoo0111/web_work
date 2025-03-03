package com.example.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring11.dto.MemberDto;
import com.example.spring11.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired private MemberService service;
	
	@PostMapping("/member/update")
	public String update(MemberDto dto, RedirectAttributes re) {
		service.updateMember(dto);
		re.addFlashAttribute("updateMessage", dto.getNum() + "번 님의 정보를 수정 완료!");
		return "redirect:/member/list";
	}
	
	@GetMapping("/member/update-form")
	public String updateForm(int num, Model model) {
		MemberDto dto = service.getMember(num);
		model.addAttribute("dto", dto);
		return "member/update-form";
	}
	
	@GetMapping("/member/delete")
	public String deleteMember(int num, RedirectAttributes re) {
		service.deleteMember(num);
		re.addFlashAttribute("deleteMessage", num + "번 님의 정보를 삭제 완료!");
		return "redirect:/member/list";
	}
	
	@PostMapping("/member/save")
	public String save(MemberDto dto, RedirectAttributes re) {
		service.saveMember(dto);
		re.addFlashAttribute("saveMessage", "회원 1명 저장 완료!");
		return "redirect:/member/list";
	}
	
	@GetMapping("/member/new")
	public String memberNew() {
		return "member/new";
	}
	
	@GetMapping("/member/list")
	public String list(Model model) {
		List<MemberDto> list = service.getAll();
		model.addAttribute("list", list);
		return "member/list";
	}
}
