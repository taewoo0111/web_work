package com.example.spring05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring05.dto.TodoDto;
import com.example.spring05.repository.TodoDao;

@Controller
public class TodoController {
	
	@Autowired TodoDao dao;
	
	@GetMapping("/todo/delete")
	public String delete(int id) {
		dao.delete(id);
		return "todo/delete";
	}
	
	@PostMapping("/todo/update")
	public String update(TodoDto dto) {
		dao.update(dto);
		return "todo/update";
	}
	
	@GetMapping("/todo/edit")
	public String edit(@RequestParam int id, Model model) {
		TodoDto dto = dao.getData(id);
		model.addAttribute("dto", dto);
		return "todo/edit";
	}
	
	@PostMapping("/todo/insert")
	public String insert(TodoDto dto) {
		dao.insert(dto);
		return "todo/insert";
	}
	
	@GetMapping("/todo/new")
	public String newForm() {
		return "todo/new";
	}
	
	@GetMapping("/todo/list")
	public String list(Model model) {
		List<TodoDto> list = dao.getList();
		model.addAttribute("list", list);
		return "todo/list";
	}
}
