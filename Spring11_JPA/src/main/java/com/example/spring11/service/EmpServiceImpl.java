package com.example.spring11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring11.dto.EmpDto;
import com.example.spring11.entity.Emp;
import com.example.spring11.repository.EmpRepository;

@Service
public class EmpServiceImpl implements EmpService{
	
	
	@Autowired private EmpRepository empRepo;
	
	@Override
	public EmpDto getEmp(int empno) {
		Emp emp = empRepo.findById(empno).orElseThrow(() -> new IllegalStateException("Member not found"));
		return EmpDto.toDto(emp);
	}

}
