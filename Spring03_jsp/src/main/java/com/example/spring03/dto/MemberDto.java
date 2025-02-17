package com.example.spring03.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
