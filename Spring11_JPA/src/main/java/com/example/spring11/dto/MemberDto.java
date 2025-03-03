package com.example.spring11.dto;

import com.example.spring11.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {
	private Integer num;
	private String name;
	private String addr;
	
	// entity 를 dto 로 변환하는 static 메소드 
	public static MemberDto toDto(Member entity) {
		return MemberDto.builder().num(entity.getNum()).name(entity.getName()).addr(entity.getAddr()).build();
	}
}
