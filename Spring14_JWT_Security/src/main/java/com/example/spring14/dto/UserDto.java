package com.example.spring14.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
	private long num;
	private String userName;
	private String password;
	private String email;
	private String role;
	private String profileImage;
	private String createdAt;
	private String updatedAt;
}
