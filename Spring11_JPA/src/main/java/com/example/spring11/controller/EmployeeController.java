package com.example.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring11.dto.EmpDeptDto;
import com.example.spring11.dto.EmpDto;
import com.example.spring11.repository.EmpRepository;
import com.example.spring11.service.EmpService;

@Controller
public class EmployeeController {
	
	@Autowired private EmpRepository empRepo;
	@Autowired private EmpService empService;
	
	@GetMapping("/emp/view")
	public String getEmp(EmpDto dto ,Model model) {
		EmpDto dto1 = empService.getEmp(dto.getEmpno());
		model.addAttribute("dto", dto1);
		return "emp/view";
	}
	
	@GetMapping("/emp/list2")
	public String list2(Model model) {
		List<EmpDeptDto> list = empRepo.findAll().stream().map(EmpDeptDto::toDto).toList();
		model.addAttribute("list", list);
		return "emp/list2";
	}
	
	@GetMapping("/emp/list")
	public String list(Model model) {
		// @Id(empno) 칼럼에 대해서 오름차순 정렬된 결과
		//List<EmpDto> list = empRepo.findAll().stream().map(EmpDto::toDto).toList();
		
		// ename 칼럼에 대해서 오름차순 정렬된 결과
		List<EmpDto> list = empRepo.findAllByOrderByEnameAsc().stream().map(EmpDto::toDto).toList();
		model.addAttribute("list", list);
		return "emp/list";
	}
}
