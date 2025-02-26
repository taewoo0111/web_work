package com.example.spring10.repository;

import java.util.List;

import com.example.spring10.dto.PostDto;

public interface PostDao {
	public List<PostDto> getList(PostDto dto);
	public int insert(PostDto dto);
	public int delete(long num);
	public int update(PostDto dto);
	public int getCount(PostDto dto);
	public PostDto getData(long num);
	public PostDto getDetail(PostDto dto);
	public int insertReaded(long num, String sessionId);
	public boolean isReaded(long num, String sessionId);
	public int addViewCount(long num);
	public int deleteReaded(long num);
	
	public long getSequence();
}
