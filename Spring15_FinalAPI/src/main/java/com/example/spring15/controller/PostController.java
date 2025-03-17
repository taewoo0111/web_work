package com.example.spring15.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/post/update-comment")
	public Map<String, Boolean> commentUpdate(CommentDto dto){
		service.updateComment(dto);
		return Map.of("isSuccess", true);
	}
	
	@GetMapping("/post/delete-comment")
	public Map<String, Boolean> deleteComment(long num){
		service.deleteComment(num);
		return Map.of("isSuccess", true);
	}
	
	@GetMapping("/post/comment-list")
	public Map<String, Object> commentList(CommentListRequest clr){
		return service.getComments(clr);
	}
	
	// 댓글 저장 요청 처리
	@PostMapping("/post/save-comment")
	public CommentDto saveComment(CommentDto dto) {
		service.createComment(dto);
		return dto;
	}
	
	@GetMapping("/post/delete")
	public String delete(long num) {
		service.deletePost(num);
		return "post/delete";
	}
	
	@PostMapping("/post/update")
	public String update(PostDto dto, RedirectAttributes ra) {
		service.updatePost(dto);
		ra.addFlashAttribute("updateMessage", dto.getNum()+ " 번 글을 수정했습니다.");
		return "redirect:/post/view?num=" + dto.getNum();
	}
	
	@GetMapping("/post/edit")
	public String edit(long num, Model model) {
		PostDto dto = service.getByNum(num);
		model.addAttribute("dto", dto);
		return "post/edit";
	}
	
	@GetMapping("/post/view")
	public String view(@ModelAttribute PostDto dto, Model model, HttpSession session) {
		PostDto resultDto = service.getDetail(dto);
		model.addAttribute("postDto", resultDto);
		
		if(model.getAttribute("saveMessage") == null) {
			String sessionId = session.getId();
			service.manageViewCount(dto.getNum(), sessionId);
		}
		
		return "post/view";
	}
	
	@PostMapping("/post/save")
	public String save(PostDto dto, RedirectAttributes ra) {
		long num = service.createPost(dto);
		ra.addFlashAttribute("saveMessage", "글을 성공적으로 저장했습니다.");
		return "redirect:/post/view?num=" + num;
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
