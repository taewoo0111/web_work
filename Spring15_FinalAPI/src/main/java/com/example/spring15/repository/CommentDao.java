package com.example.spring15.repository;

import java.util.List;

import com.example.spring15.dto.CommentDto;

public interface CommentDao {
	public int insert(CommentDto dto);
	public int update(CommentDto dto);
	public int delete(long num);
	public long getSequence();
	public CommentDto getData(long num);
	public List<CommentDto> getList(CommentDto dto);
	public int getCount(long postNum);
}
