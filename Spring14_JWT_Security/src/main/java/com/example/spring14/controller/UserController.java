package com.example.spring14.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring14.dto.UserDto;
import com.example.spring14.util.JwtUtil;

@Controller
public class UserController {
	
	@Autowired JwtUtil jwtUtil;
	@Autowired AuthenticationManager authManager;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/secured/ping")
	@ResponseBody
	public String securedPing() {
		return "pong! pong!";
	}
	
	@GetMapping("/api/ping") //white list 에 등록 되지 않은 요청은 token 이 있어야 요청 가능하다 
 	@ResponseBody
 	public String ping() {
 		
 		return "pong";
 	}
 	
 	@PostMapping("/api/auth")
 	@ResponseBody
 	public ResponseEntity<String> auth(@RequestBody UserDto dto) throws Exception{
 		Authentication authentication = null;
 		try {
 			UsernamePasswordAuthenticationToken authToken=
 					new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
 			//인증 메니저 객체를 이용해서 인증을 진행한다.
 			authentication = authManager.authenticate(authToken);
 		}catch(Exception e) {
 			//예외가 발생하면 인증실패(아이디 혹은 비밀번호 틀림 등등...)
 			e.printStackTrace();
 			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패!");
 		}
 		
 		// Authentication 객체에는 인증된 사용자 정보가 들어 있다. userName, role 등등
 		// 현재는 role 을 하나만 부여하기 때문에 0번 방에 있는 데이터만 불러오면 된다.
 		GrantedAuthority authority = authentication.getAuthorities().stream().toList().get(0);
 		String role = authority.getAuthority();
 		Map<String, Object> claims = Map.of("role", role);
 		
 		//예외가 발생하지 않고 여기까지 실행 된다면 인증을 통과 한 것이다. 토큰을 발급해서 응답한다.
 		String token=jwtUtil.generateToken(dto.getUserName(), claims);
 		return ResponseEntity.ok("Bearer " + token);
 	}
	
	// 세션 허용갯수 초과시
	@GetMapping("/user/expired")
	public String userExpired() {
		return "user/expired";
	}

	// 권한 부족시 or 403 인 경우
	@RequestMapping("/user/denied") // GET, POST 등 모두 가능
	public String userDenied() {
		return "user/denied";
	}

	// ROLL_STAFF , ROLL_ADMIN 만 요청 가능
	@GetMapping("/staff/user/list")
	public String userList() {
		return "user/list";
	}

	// ROLL_ADMIN 만 요청 가능
	@GetMapping("/admin/user/manage")
	public String userManage() {
		return "user/manage";
	}

	@RequestMapping("/user/loginform")
	public String loginform() {
		// templates/user/loginform.html 페이지로 forward 이동해서 응답
		return "user/loginform";
	}

	// 로그인이 필요한 요청경로를 로그인 하지 않은 상태로 요청하면 리다일렉트 되는 요청경로
	@GetMapping("/user/required-loginform")
	public String required_loginform() {
		return "user/required-loginform";
	}

	// POST 방식 /user/login 요청후 로그인 성공인경우 forward 이동될 url
	@PostMapping("/user/login-success")
	public String loginSuccess() {
		return "user/login-success";
	}

	// 로그인 폼을 제출(post) 한 로그인 프로세즈 중에 forward 되는 경로이기 때문에 @PostMapping 임에 주의!
	@PostMapping("/user/login-fail")
	public String loginFail() {
		// 로그인 실패임을 알릴 페이지
		return "user/login-fail";
	}
}
