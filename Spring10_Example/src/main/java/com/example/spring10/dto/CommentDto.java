package com.example.spring10.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("commentDto")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {
	private long num;
	private String writer;
	private String content;
	private String targetWriter;
	private long postNum;
	private long parentNum;
	private String deleted;
	private String createdAt;
	private String profileImage;
	private int startRowNum;
	private int endRowNum;
	private int totalPageCount;
}
