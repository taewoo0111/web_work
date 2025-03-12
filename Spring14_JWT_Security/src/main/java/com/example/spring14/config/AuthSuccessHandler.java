package com.example.spring14.config;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.CookieRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.example.spring14.util.JwtUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 로그인 성공 후에 호출될 메소드를 가지고 있는 클래스
 */

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	// Jwt 토큰 유틸
	@Autowired
	private JwtUtil jwtUtil;
	
	// 쿠키에 저장된 요청 경로를 자동으로 읽어오는 객체를 생성해서 필드에 담둔다.
	private CookieRequestCache requestCache = new CookieRequestCache();

	// jwt 를 쿠키로 저장할때 쿠키의 이름
	@Value("${jwt.name}")
	private String jwtName;
	// 쿠키 유지시간
	@Value("${jwt.cookie.expiration}")
	private int cookieExpiration;
	
	// 입력한 username 과 password 가 일치해서 로그인이 성공하면 이 메소드가 호출된다.
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		// Authentication 객체에는 인증된 사용자 정보가 들어 있다. userName, role 등등
		// 현재 role 을 하나만 부여하기 때문에 0번 방에 있는 데이터만 불러오면 된다.
		GrantedAuthority authority = authentication.getAuthorities().stream().toList().get(0);
		// ROLE_XXX 형식
		String role = authority.getAuthority();
		Map<String, Object> claims = Map.of("role", role);
		
		
		String jwtToken = jwtUtil.generateToken(authentication.getName(), claims);
		
		// 공백문자 때문에 인코딩을 해서 저장해야 함
		String encoded = URLEncoder.encode("Bearer " + jwtToken, StandardCharsets.UTF_8);
		
		Cookie cookie = new Cookie(jwtName, encoded);
		cookie.setMaxAge(cookieExpiration); // 쿠키 유지 시간 초 단위로 설정
		cookie.setHttpOnly(true); // 웹브라우저에서 JavaScript에서 접근 불가 하도록 설정
		cookie.setPath("/"); // 모든 경로에서 쿠키를 사용할수 있도록 설정
		response.addCookie(cookie);
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(savedRequest==null) {
			// 로그인 환영 페이지로 foward 이동 (POST 방식 요청임에 주의!)
			RequestDispatcher rd = request.getRequestDispatcher("/user/login-success");
			rd.forward(request, response);
		}else {
			String targetUrl = savedRequest.getRedirectUrl();
			response.sendRedirect(targetUrl);
		}
	}
}
