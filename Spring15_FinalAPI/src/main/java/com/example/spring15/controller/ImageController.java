package com.example.spring15.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImageController {
	
	@Value("${file.location}")
	private String fileLocation;
	
	/*
	 * "/upload/xxx.jpg"
	 * "/upload/xxx.png"
	 * "/upload/xxx.gif"
	 * 패턴의 요청이 오면 실제 해당 이미지를 읽어서 실제 이미지 데이터를 응답하는 컨트롤러 메소드 만들기
	 */
	@GetMapping("/upload/{imageName}")
	public ResponseEntity<InputStreamResource> image(@PathVariable("imageName") String name) throws IOException{
		
		String filePath = fileLocation + File.separator + name;
		File file = new File(filePath);
		if(!file.exists()) {
			throw new RuntimeException("file not found!");
		}
		
		// mime type 얻어내기 -> mime type = 파일의 종류나 데이터의 형식을 나타내는 표준 (text, image, audio, ...)
		String mimeType = Files.probeContentType(file.toPath());
		InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).contentLength(file.length()).body(isr);
	}
}
