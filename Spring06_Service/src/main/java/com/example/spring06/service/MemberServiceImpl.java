package com.example.spring06.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring06.dto.MemberDto;
import com.example.spring06.repository.MemberDao;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired 
	private MemberDao dao;
	
	@Override
	public List<MemberDto> findAll() {
		List<MemberDto> list = dao.getList();
		return list;
	}

	@Override
	public MemberDto findById(int num) {
		MemberDto dto = dao.getData(num);
		return dto;
	}

	@Override
	public void save(MemberDto dto) {
		dao.insert(dto);
	}

	@Override
	public void update(MemberDto dto) {

		int rowCount = dao.update(dto);
		if (rowCount == 0) {
			throw new RuntimeException("수정할 회원 정보가 없습니다.");
		}

	}

	@Override
	public void deleteById(int num) {

		int rowCount = dao.delete(num);
		if (rowCount == 0) {
			throw new RuntimeException("삭제할 회원 정보가 없습니다.");

		}
	}
}
