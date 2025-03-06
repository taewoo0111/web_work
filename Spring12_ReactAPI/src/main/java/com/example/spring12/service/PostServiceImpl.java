package com.example.spring12.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring12.dto.PostDto;
import com.example.spring12.entity.Post;
import com.example.spring12.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired private PostRepository repo;
	
	@Override
	public PostDto save(PostDto dto) {
		Post post = repo.save(Post.toEntity(dto));
		return PostDto.toDto(post);
	}

	@Override
	public List<PostDto> findAll() {
		return repo.findAll().stream().map(PostDto::toDto).toList();
	}

	@Override
	public PostDto delete(long id) {
		Post post = repo.findById(id).orElseThrow(() -> new IllegalStateException("없는 번호입니다!"));
		repo.deleteById(id);
		return PostDto.toDto(post);
	}

	@Override
	public PostDto updateAll(PostDto dto) {
		Post post = repo.save(Post.toEntity(dto));
		return PostDto.toDto(post);
	}
	
	/*
	 *  JPA 에서 동일한 Transaction 내에서 특정 Entity 를 find 한 다음
	 *  해당 Entity 의 setter 메소드를 이용해서 특정 필드를 수정하면 
	 *  Transaction 이 종료될 때 Entity 가 변경되었는지를 확인해서
	 *  자동으로 DB에 변경된 내용만 수정 반영한다. 
	 */
	@Transactional // update() 메소드를 단일 트랜잭션으로 관리
	@Override
	public PostDto update(PostDto dto) {
		Post post = repo.findById(dto.getId()).orElseThrow(() -> new IllegalStateException("없는 번호입니다!"));
//		if(dto.getTitle()!=null) {
//			post.setTitle(dto.getTitle());
//		}
		Optional.ofNullable(dto.getTitle()).ifPresent(post::setTitle);
		
//		if(dto.getAuthor()!=null) {
//			post.setAuthor(dto.getAuthor());
//		}
		Optional.ofNullable(dto.getAuthor()).ifPresent(post::setAuthor);
		return PostDto.toDto(post);
	}

	@Override
	public PostDto find(long id) {
		Post post = repo.findById(id).orElseThrow(() -> new IllegalStateException("없는 번호입니다!"));
		return PostDto.toDto(post);
	}

}
