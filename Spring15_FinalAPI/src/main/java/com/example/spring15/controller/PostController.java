package com.example.spring15.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring15.dto.CommentDto;
import com.example.spring15.dto.CommentListRequest;
import com.example.spring15.dto.PostDto;
import com.example.spring15.dto.PostListDto;
import com.example.spring15.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
public class PostController {
	
	@Autowired private PostService service;
	
	@PatchMapping("/posts/{postNum}/comments/{num}")
	public Map<String, Boolean> commentUpdate(@PathVariable long postNum, @PathVariable long num, @RequestBody CommentDto dto){
		service.updateComment(dto);
		return Map.of("isSuccess", true);
	}
	
	@DeleteMapping("/posts/{postNum}/comments/{num}")
	public Map<String, Boolean> deleteComment(@PathVariable long postNum, @PathVariable long num){
		service.deleteComment(num);
		return Map.of("isSuccess", true);
	}
	
	@GetMapping("/posts/{num}/comments")
	public Map<String, Object> commentList(@PathVariable("num") long num, int pageNum){
 		//CommentListRequest 에 필요한 정보를 담고
 		CommentListRequest clr=new CommentListRequest();
 		clr.setPostNum(num);
 		clr.setPageNum(pageNum);
 		//서비스를 이용해서 댓글 목록 정보를 얻어내서 응답한다.
 		return service.getComments(clr);
 	}
	
	// 댓글 저장 요청 처리
	@PostMapping("/posts/{num}/comments")
 	public CommentDto saveComment(@PathVariable(value="num") long num , 
 			@RequestBody CommentDto dto) {
 		//dto 에 원글의 글번호 담기
 		dto.setPostNum(num);
 		//서비스를 이용해서 댓글 저장
 		service.createComment(dto);
 		return dto;
 	}
	
	@DeleteMapping("/posts/{num}")
	public String delete(@PathVariable("num")long num) {
		service.deletePost(num);
		return "post/delete";
	}
	
	@PatchMapping("/posts/{num}")
	public PostDto update(@PathVariable("num") long num, @RequestBody PostDto dto) {
		dto.setNum(num);
		service.updatePost(dto);
		return dto;
	}
	
	@GetMapping("/post/edit")
	public String edit(long num, Model model) {
		PostDto dto = service.getByNum(num);
		model.addAttribute("dto", dto);
		return "post/edit";
	}
	
	@GetMapping("/posts/{num}")
	public PostDto view(@PathVariable(name = "num") long num, PostDto dto) {
		dto.setNum(num);
		PostDto resultDto = service.getDetail(dto);
		return resultDto;
	}
	
	@PostMapping("/posts")
	public Map<String, Object> save(@RequestBody PostDto dto) {
		long num = service.createPost(dto);
		return Map.of("num", num);
	}
	
	@GetMapping("/post/new")
	public String newForm() {
		return "post/new";
	}
	
	/*
	 * pageNum 이 파리미터로 넘어오지 않으면 pageNum 의 default 값은 1로 설정
	 * 검색 키워드도 파라미터로 넘어오면 PostDto 에는 condition, keyword 가 null 이 아니다.
	 * 검색 키워드가 넘어오지 않으면 PostDto 의 condition, keyword 는 null 이다.
	 */
	@GetMapping("/posts")
	public PostListDto list(@RequestParam(defaultValue = "1") int pageNum, PostDto search) {
		return service.getPosts(pageNum, search);
	}
}
