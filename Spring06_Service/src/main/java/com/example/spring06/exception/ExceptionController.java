package com.example.spring06.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDataAccessException(DataAccessException e, Model model) {
		model.addAttribute("message", "DB 관련 작업 도중에 예외가 발생했습니다.");
		return "error/data-access";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "error/runtime";
	}
}
