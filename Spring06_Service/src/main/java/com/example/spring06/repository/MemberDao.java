package com.example.spring06.repository;

import java.util.List;
import com.example.spring06.dto.MemberDto;

public interface MemberDao {
	public List<MemberDto> getList();
	public void insert(MemberDto dto);
	public int update(MemberDto dto);
	public int delete(int num);
	public MemberDto getData(int num);
}
