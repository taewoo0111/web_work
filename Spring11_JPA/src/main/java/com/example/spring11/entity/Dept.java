package com.example.spring11.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 이름을 따로 지정하지 않으면 만들어지는 테이블은 class 명과 동일?
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
	@Id
	private Integer deptno;
	private String dname;
	private String loc;
}
