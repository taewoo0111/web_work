package com.example.spring11.dto;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.spring11.entity.Emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmpDeptDto {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private double sal;
	private double comm;
	
	// Emp, Dept 에 같이 있는 정보
	private int deptno;
	
	// Dept Entity 에만 있는 정보
	private String dname;
	private String loc;
	
	public static EmpDeptDto toDto(Emp emp) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		String hiredate = sdf.format(emp.getHiredate());
		
		return EmpDeptDto.builder().empno(emp.getEmpno()).ename(emp.getEname()).job(emp.getJob())
				.mgr(emp.getMgr()==null ? 0 : emp.getMgr()).hiredate(hiredate)
				.sal(emp.getSal()).comm(emp.getComm()==null ? 0 : emp.getComm()).deptno(emp.getDept().getDeptno())
				.dname(emp.getDept().getDname()).loc(emp.getDept().getLoc()).build();
	}
}
