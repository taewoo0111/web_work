package com.example.spring11.entity;

import com.example.spring11.dto.MemberDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "MEMBER_INFO")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer num;
	private String name;
	private String addr;
	
	public static Member toEntity(MemberDto dto) {
		return Member.builder().num(dto.getNum()).name(dto.getName()).addr(dto.getAddr()).build();
	}
}
