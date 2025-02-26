package com.example.spring10.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring10.dto.PostDto;

@Repository
public class PostDaoImpl implements PostDao{

	@Autowired private SqlSession session;
	
	@Override
	public List<PostDto> getList(PostDto dto) {
		return session.selectList("post.getList", dto);
	}

	@Override
	public int insert(PostDto dto) {
		return session.insert("post.insert", dto);
	}

	@Override
	public int delete(long num) {
		return session.delete("post.delete", num);
	}

	@Override
	public int update(PostDto dto) {
		return session.update("post.update", dto);
	}

	@Override
	public int getCount(PostDto dto) {
		return session.selectOne("post.getCount", dto);
	}

	@Override
	public long getSequence() {
		return session.selectOne("post.getSequence");
	}

	@Override
	public PostDto getData(long num) {
		return session.selectOne("post.getData", num);
	}

	@Override
	public PostDto getDetail(PostDto dto) {
		return session.selectOne("post.getDetail", dto);
	}

	@Override
	public int insertReaded(long num, String sessionId) {
		Map<String, Object> map = Map.of("num", num, "sessionId", sessionId);
		return session.insert("post.insertReaded", map);
	}

	@Override
	public boolean isReaded(long num, String sessionId) {
		Map<String, Object> map = Map.of("num", num, "sessionId", sessionId);
		Map<String, Object> resultMap = session.selectOne("post.isReaded", map);
		
		return resultMap != null;
	}

	@Override
	public int addViewCount(long num) {
		return session.update("post.addViewCount", num);
	}

	@Override
	public int deleteReaded(long num) {
		return session.delete("post.deleteReaded", num);
	}

}
