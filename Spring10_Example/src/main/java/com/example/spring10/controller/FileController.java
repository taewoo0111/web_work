package com.example.spring10.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;
import com.example.spring10.service.FileService;

@Controller
public class FileController {
	
	@Value("${file.location}")
	private String fileLocation;
	@Autowired private FileService service;
	
	@GetMapping("file/download")
	public ResponseEntity<InputStreamResource> download(long num) {
		FileDto dto = service.getByNum(num);
		try {
			// 다운로드 시켜줄 원본 파일명
			String encodedName = URLEncoder.encode(dto.getOrgFileName(), "utf-8");
			// 파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
			encodedName = encodedName.replaceAll("\\+", " ");
			// 응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
			HttpHeaders headers = new HttpHeaders();
			// 파일을 다운로드 시켜 주겠다는 정보
			headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
			// 파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodedName);
			// 파일의 크기 정보도 담아준다.
			headers.setContentLength(dto.getFileSize());

			// 읽어들일 파일의 경로 구성
			String filePath = fileLocation + File.separator + dto.getSaveFileName();
			
			// 파일에서 읽어들일 스트림 객체
			InputStream is = new FileInputStream(filePath);
			InputStreamResource isr = new InputStreamResource(is);
			ResponseEntity<InputStreamResource> resEntity = ResponseEntity.ok().headers(headers).body(isr);
			
			return resEntity;
		}catch (Exception e) {
			throw new RuntimeException("파일을 다운로드 하는 중에 오류 발생!");
		}
	}
	
	@GetMapping("/file/delete")
	public String delete(long num) {
		service.deleteFile(num);
		return "file/delete";
	}
	
	@PostMapping("/file/save")
	public String save(FileDto dto, Model model, RedirectAttributes ra) {
		MultipartFile myFile = dto.getMyFile();
		if(myFile.isEmpty()) {
			throw new RuntimeException("파일이 업로드되지 않았습니다!");
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
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		service.uploadFile(dto);
		ra.addFlashAttribute("saveMessage", "파일을 성공적으로 업로드!");
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/new")
	public String newForm() {
		return "file/new";
	}
	
	@GetMapping("/file/list")
	public String list(@RequestParam(defaultValue = "1") int pageNum, FileDto search, Model model) {
		FileListDto dto = service.getFiles(pageNum, search);
		model.addAttribute("dto", dto);
		return "file/list";
	}
}
