package com.example.spring10.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring10.dto.UserDto;
import com.example.spring10.repository.UserDao;

/*
 * Spring Security 가 로그인 처리시 호출하는 메소드를 가지고 있는 서비스 클래스 정의하기
 */

@Service
public class CustomUserDetailsService implements UserDetailsService{	
	
	@Autowired private UserDao dao;
	
	// username 을 전달하면 해당 user 의 자세한 정보를 리턴하는 메소드
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		UserDto dto = dao.getData(userName);
		if(dto==null) {
			throw new UsernameNotFoundException("존재하지 않는 사용자 입니다.");
		}
		
		
		// 권한 목록을 List 에 담아서 (지금은 1개 이지만)
		List<GrantedAuthority> authList = new ArrayList<>();
		authList.add(new SimpleGrantedAuthority(dto.getRole()));

		// UserDetails 객체를 생성해서
		UserDetails ud = new User(dto.getUserName(), dto.getPassword(), authList);
		// 리턴해준다.
		return ud;
	}
	
}
