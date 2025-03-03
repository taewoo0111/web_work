package com.example.spring11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spring11.entity.Member;

/*
 *  아래와 같이 선언만해도 MemberRepository 인터페이스를 구현한 클래스로 생성된 객체가
 *  bean 으로 관릴된다.
 */

// extends JpaRepository<Entity type, Entity 에서 아이디 역할을 하는 필드의 type>
public interface MemberRepository extends JpaRepository<Member, Integer>{
	
	public List<Member> findAllByOrderByNumDesc();
	
	@Query(value = "select m from MEMBER m order by m.num desc")
	public List<Member> getList();
}
