package com.example.spring07.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring07.dto.GalleryDto;

@Controller
public class GalleryController {
	
	@Value("${file.location}")
	private String fileLocation;
	
	@PostMapping("/gallery/upload")
	public String upload(GalleryDto dto, Model model) {
		
		MultipartFile image = dto.getImage();
		if(image.isEmpty()) {
			throw new RuntimeException("이미지가 업로드 되지 않았습니다.");
		}
		
		String orgFileName = image.getOriginalFilename();
		String saveFileName = UUID.randomUUID().toString() + orgFileName;
		String filePath = fileLocation + File.separator + saveFileName;
		try {
			File saveFile = new File(filePath);
			image.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("caption", dto.getCaption());
		model.addAttribute("saveFileName", saveFileName);
		
		return "gallery/upload";
	}
	
	@GetMapping("/gallery/new")
	public String newForm() {
		return "gallery/new";
	}
}
