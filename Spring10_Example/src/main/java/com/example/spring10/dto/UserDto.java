package com.example.spring10.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long num;
	private String userName;
	private String password;
	private String newPassword;
	private String email;
	private String role;
	private String profileImage;
	private String createdAt;
	private String updatedAt;
	private MultipartFile profileFile;
}
