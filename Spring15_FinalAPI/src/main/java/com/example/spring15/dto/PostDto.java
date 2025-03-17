package com.example.spring15.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("postDto")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {
	private long num;
	private String writer;
	private String title;
	private String content;
	private int viewCount;
	private String createdAt;
	private String updatedAt;
	private int startRowNum;
	private int endRowNum;
	
	private String condition; // 검색 조건
	private String keyword; // 검색 키워드
	private long prevNum; // 이전글의 글번호
	private long nextNum; // 다음글의 글번호
}
