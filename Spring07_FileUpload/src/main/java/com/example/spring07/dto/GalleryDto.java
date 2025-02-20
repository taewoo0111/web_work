package com.example.spring07.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class GalleryDto {
	private long id;
	private String uploader;
	private String caption;
	private String uploadedAt;
	private MultipartFile image;
}
