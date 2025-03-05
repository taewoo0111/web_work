package com.example.spring12.entity;

import com.example.spring12.dto.PostDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;
	
	public static Post toEntity(PostDto dto) {
		return Post.builder().id(dto.getId()==0 ? null : dto.getId())
				.title(dto.getTitle()).author(dto.getAuthor()).build();
	}
}
