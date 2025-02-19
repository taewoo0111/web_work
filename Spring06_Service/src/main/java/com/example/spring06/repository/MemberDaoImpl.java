package com.example.spring06.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring06.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired 
	private SqlSession session;
	
	@Override
	public List<MemberDto> getList() {
		/*
		 * SqlSession 객체를 이용해서 회원 목록을 읽어온다.
		 */
		List<MemberDto> list = session.selectList("member.getList");
		return list;
	}

	@Override
	public void insert(MemberDto dto) {
		session.insert("member.insert", dto);
	}

	@Override
	public int update(MemberDto dto) {
		return session.update("member.update", dto);
	}

	@Override
	public int delete(int num) {
		return session.delete("member.delete", num);
	}

	@Override
	public MemberDto getData(int num) {
		
		MemberDto dto = session.selectOne("member.getData", num);
		return dto;
	}

}
