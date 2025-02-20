package com.example.spring07.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {
	
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping("/gallery/new")
	public String newForm() {
		return "gallery/new";
	}
}
