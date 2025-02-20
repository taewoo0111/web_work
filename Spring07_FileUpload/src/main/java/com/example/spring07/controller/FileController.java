package com.example.spring07.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring07.dto.FileDto;

@Controller
public class FileController {
	
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(String orgFileName, String saveFileName, long fileSize){
		// 원래는 DB 에서 읽어와야 하지만 지금은 다운로드해줄 파일의 정보가 요청 파라미터로 전달된다.
		try {
			// 다운로드 시켜줄 원본 파일명
			String encodedName = URLEncoder.encode(orgFileName, "utf-8");
			// 파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
			encodedName = encodedName.replaceAll("\\+", " ");
			// 응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
			HttpHeaders headers = new HttpHeaders();
			// 파일을 다운로드 시켜 주겠다는 정보
			headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
			// 파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodedName);
			// 파일의 크기 정보도 담아준다.
			headers.setContentLength(fileSize);

			// 읽어들일 파일의 경로 구성
			String filePath = fileLocation + File.separator + saveFileName;
			
			// 파일에서 읽어들일 스트림 객체
			InputStream is = new FileInputStream(filePath);
			InputStreamResource isr = new InputStreamResource(is);
			ResponseEntity<InputStreamResource> resEntity = ResponseEntity.ok().headers(headers).body(isr);
			
			return resEntity;
		}catch (Exception e) {
			throw new RuntimeException("파일을 다운로드 하는 중에 오류 발생!");
		}
	}
	
	/*
	 * <input type="file" name="myFile"> 은 MultipartFile myFile 로 해서 업로드된 파일의 정보를 얻어내야 한다. 
	 */
	
	@PostMapping("/file/upload2")
	public String upload2(FileDto dto, Model model) {
		
		// @Data 가 오버라이드한 toString() 메소드를 확인하기 위해
		System.out.println(dto); // 원래는 dto의 hash 값이 출력되어야 하는데 필드를 확인할 수 있는 문자열이 출력된다.
		
		MultipartFile myFile = dto.getMyFile();
		
		if(myFile.isEmpty()) {
			throw new RuntimeException("파일이 업로드 되지 않았습니다.");
		}
		
		String orgFileName = myFile.getOriginalFilename();
		long fileSize = myFile.getSize();
		String saveFileName = UUID.randomUUID().toString();
		String filePath = fileLocation + File.separator + saveFileName;
		try {
			File saveFile = new File(filePath);
			myFile.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("title", dto.getTitle());
		model.addAttribute("orgFileName", orgFileName);
		model.addAttribute("fileSize", fileSize);
		model.addAttribute("saveFileName", saveFileName);
		return "file/upload";
	}
	
	@PostMapping("/file/upload")
	public String upload(String title, MultipartFile myFile, Model model) {
		
		if(myFile.isEmpty()) {
			throw new RuntimeException("파일이 업로드 되지 않았습니다.");
		}
		
		String orgFileName = myFile.getOriginalFilename();
		long fileSize = myFile.getSize();
		String saveFileName = UUID.randomUUID().toString();
		String filePath = fileLocation + File.separator + saveFileName;
		try {
			File saveFile = new File(filePath);
			myFile.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("title", title);
		model.addAttribute("orgFileName", orgFileName);
		model.addAttribute("fileSize", fileSize);
		model.addAttribute("saveFileName", saveFileName);
		return "file/upload";
	}
	
	@GetMapping("/file/new")
	public String newForm() {
		return "file/new";
	}
}
