package com.example.spring11.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity 
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
	@Id
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hiredate;
	private Double sal;
	private Double comm;
	// 부서번호는 하나만 있어도 되기때문에 주석 처리
	//private Integer deptno;
	
	@ManyToOne
	@JoinColumn(name = "deptno", referencedColumnName = "deptno")
	// name = "deptno" -> 테이블의 칼럼명을 결정! 
	// referencedColumnName = "deptno" -> Dept의 테이블의 어떤 칼럼을 참조할지 결정!(생략 시 자동으로 @Id 칼럼 참조)
	private Dept dept;
}
