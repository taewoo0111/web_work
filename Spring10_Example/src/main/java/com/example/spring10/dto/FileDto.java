package com.example.spring10.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("fileDto")
public class FileDto {
	private long num;
	private String uploader;
	private String title;
	private String orgFileName;
	private String saveFileName;
	private long fileSize;
	private String uploadedAt;
	private MultipartFile myFile;
	
	private int startRowNum;
	private int endRowNum;
	
	private String condition; // 검색 조건
	private String keyword; // 검색 키워드
	private long prevNum; // 이전글의 글번호
	private long nextNum; // 다음글의 글번호
}
