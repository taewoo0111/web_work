package com.example.spring05.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring05.dto.TodoDto;

@Repository
public class TodoDaoImpl implements TodoDao{
	
	@Autowired SqlSession session;
	
	@Override
	public List<TodoDto> getList() {
		List<TodoDto> list = session.selectList("todo.getList");
		return list;
	}

	@Override
	public void insert(TodoDto dto) {
		session.insert("todo.insert", dto);
	}

	@Override
	public void delete(int id) {
		session.delete("todo.insert", id);
	}

	@Override
	public void update(TodoDto dto) {
		session.update("todo.update", dto);
	}

	@Override
	public TodoDto getData(int id) {
		TodoDto dto = session.selectOne("todo.getData", id);
		return dto;
	}

}
