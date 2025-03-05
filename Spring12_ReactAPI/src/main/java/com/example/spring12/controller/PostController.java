package com.example.spring12.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring12.dto.PostDto;
import com.example.spring12.entity.Post;
import com.example.spring12.repository.PostRepository;

@RequestMapping("/v1")
@RestController
public class PostController {
	
	@Autowired private PostRepository repo;
	
	/*
	 * 보통 API 서버에서는 클라이언트가 json 문자열을 전송한다.
	 * 해당 json 문자열에서 데이터를 추출하기 위해서는 @RequestBody 어노테이션이 필요하다.
	 */
	@PostMapping("/posts")
	public PostDto insert(@RequestBody PostDto dto){
		Post post = repo.save(Post.toEntity(dto));
		return PostDto.toDto(post);
	}
	
	@GetMapping("/posts")
	public List<PostDto> list(){
		return repo.findAll().stream().map(PostDto::toDto).toList();
	}
	
	@DeleteMapping("/posts/{id}")
	public PostDto delete(@PathVariable("id") long id) {
		// 삭제할 post 읽어오기
		Post post = repo.findById(id).get();
		
		repo.deleteById(id);
		
		// 이미 삭제한 데이터를 응답해 준다.
		return PostDto.toDto(post);
	}
	
	@PutMapping("/posts/{id}")
	public PostDto update(@PathVariable("id") long id, @RequestBody PostDto dto) {
		dto.setId(id);
		// Entity id 가 null 이 아니여서 update 된다. insert 아님!
		repo.save(Post.toEntity(dto));
		return dto;
	}
}
