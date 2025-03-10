package com.example.spring13;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.example.spring13.util.JwtUtil;

import jakarta.annotation.PostConstruct;

@PropertySource(value = "classpath:custom.properties")
@SpringBootApplication
public class Spring13JwtApplication {
	
	@Autowired private JwtUtil util;
	
	@PostConstruct
	public void generateToken() {
		// 토큰에 담을 추가 정보(디코딩하면 알아낼 수 있다.)
		Map<String, Object> claims = Map.of("role", "USER", "email", "aaa@naver.com");
		// userName 을 전달해서 토큰을 발급 받는다.
		String token = util.generateToken("kimgura", claims);
		// 토큰을 콘솔 창에 출력해보기
		System.out.println(token);
	}

	public static void main(String[] args) {
		SpringApplication.run(Spring13JwtApplication.class, args);
	}

}
