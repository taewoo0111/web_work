package com.example.spring12.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring12.dto.PostDto;
import com.example.spring12.service.PostService;

@RequestMapping("/v2")
@RestController
public class PostController2 {
	
	@Autowired private PostService service;
	
	@PostMapping("/posts")
	public PostDto insert(@RequestBody PostDto dto){
		return service.save(dto);
	}
	
	@GetMapping("/posts")
	public List<PostDto> list(){
		return service.findAll();
	}
	
	@DeleteMapping("/posts/{id}")
	public PostDto delete(@PathVariable("id") long id) {
		return service.delete(id);
	}
	
	@PutMapping("/posts/{id}")
	public PostDto updateAll(@PathVariable("id") long id, @RequestBody PostDto dto) {
		dto.setId(id);
		return service.updateAll(dto);
	}
	
	@PatchMapping("/posts/{id}")
	public PostDto update(@PathVariable("id") long id, @RequestBody PostDto dto) {
		dto.setId(id);
		return service.update(dto);
	}
	
	@GetMapping("/posts/{id}")
	public PostDto findPost(@PathVariable("id") long id) {
		return service.find(id);
	}
}
